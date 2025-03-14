const { Client, LocalAuth } = require('whatsapp-web.js');
const express = require('express');
const socketio = require('socket.io');
const qrcode = require('qrcode');
const http = require('http');
const fs = require('fs');
const { phoneNumberFormatter } = require('./phoneformatter');
const { response } = require('express');
const db = require('./db_config');
const today = require('./today');
const { count } = require('console');

const app = express();
const server = http.createServer(app);
const io = socketio(server);

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

// const SESSION_FILE_PATH = './whatsapp-session.json';
// let sessionCfg;
// if (fs.existsSync(SESSION_FILE_PATH)) {
//     sessionCfg = require(SESSION_FILE_PATH);
// }

app.get('/', (req, res) => {
    res.sendFile('index.html', {root: __dirname})
});

const client = new Client({
    authStrategy: new LocalAuth()
});

client.on('qr', (qr) => {
    console.log('QR RECEIVED', qr);
});

// client.on('authenticated', (session) => {
//     console.log('AUTHENTICATED', session);
//     sessionCfg=session;
//     fs.writeFile(SESSION_FILE_PATH, JSON.stringify(session), function (err) {
//         if (err) {
//             console.error(err);
//         }
//     });
// });

client.on('message', msg => {
    if (msg.body == '!ping') {
        msg.reply('pong');
    }
});

client.initialize();

// SOCKET IO
io.on('connection', function(socket){
    socket.emit('message', 'Connecting...');

    client.on('qr', (qr) => {
        console.log('QR RECEIVED', qr);
        qrcode.toDataURL(qr, (err, url) => {
            socket.emit('qr', url);
            socket.emit('message', 'QR Code received, scan please!');
        });
    });

    client.on('ready', () => {
        socket.emit('message', 'Whatsapp is ready!');
    });
});

// Random number 6 digit
function between(min, max) {  
    return Math.floor(
      Math.random() * (max - min) + min
    )
}

// Send message API
app.post('/send-message', (req, res) => {
    const number = req.body.number;
    const message = req.body.message;

    client.sendMessage(number, message).then(response => {
        res.status(200).json({
            status: true,
            response: response
        })
    }).catch(err => {
        res.status(500).json({
            status: false,
            response: err
        })
    });
});

// Send message API
app.post('/rsnd-otp', (req, res) => {
    const otp = between(100000, 999999);
    // const id = between(100, 999) + '' + otp;
    const number = phoneNumberFormatter(req.body.number);
    let numdb = number.replace(/\D/g, '');
    const message = 'Kode OTP anda adalah ' + otp;

    db.query("SELECT * FROM otp WHERE number='"+numdb+"'", function (err,result){
        if (err) throw err;
        if (result.length > 0) {
            db.query("DELETE FROM otp WHERE number='"+numdb+"'", function (err,result){
                if (err) throw err;
            });
        }

        var sql = "INSERT INTO otp (number, otp, exp) VALUES ('"+numdb+"', '"+otp+"', '"+today+"')";
        db.query(sql, function (err, result) {
            if (err) throw err;
            client.sendMessage(number, message).then(response => {
                res.status(200).json({
                    status: true,
                    response: response
                })
            }).catch(err => {
                res.status(500).json({
                    status: false,
                    response: err
                })
            });
        });
    });

});

// Verifikasi OTP
app.post('/verif-otp', (req, res) => {
    const number = phoneNumberFormatter(req.body.number);
    let numdb = number.replace(/\D/g, '');
    const otp = req.body.otp;
    // const message = 'Berhasil verifikasi!';

    var sql ="SELECT * FROM otp WHERE number='"+numdb+"' and otp='"+otp+"'";
    db.query(sql, function (err, result) {
        if(err) throw err;
        if (result.length > 0) {
            db.query("SELECT exp FROM otp WHERE number='"+numdb+"' and otp='"+otp+"'", function (err, result) {
                if (err) throw err;
                var exp = result[0].exp;
                exp.setMinutes(exp.getMinutes()+5);

                if (Date.now()/1000 < exp.getTime()/1000){
                    var countdown = Math.floor(exp.getTime()/1000 - Date.now()/1000);
                    res.status(200).json({
                        status: true,
                        success: true,
                        userId: '1',
                        nohp: numdb,
                        // message: 'OTP Verified!'
                        message: Math.floor(countdown/60) + ' Minutes ' + countdown%60 + ' Seconds Left'
                    })
                } else {
                    res.status(500).json({
                        status: false,
                        success: false,
                        userId: '1',
                        nohp: numdb,
                        code: 3,
                        message: 'OTP Expired!'
                    })
                }
            });
        }else{
            res.status(500).json({
                status: false,
                code: 2,
                message: 'OTP Incorrect!'
            })
        }
    });
})

server.listen(7000, function(){
    console.log('App running on *:' + 7000);
});