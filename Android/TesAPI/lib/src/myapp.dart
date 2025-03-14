import 'dart:convert' as convert;
import 'package:http/http.dart' as http;
import 'package:flutter/material.dart';

class apiUrl {
  String url = '';
  apiUrl.empty(){
    this.url = '';
  }
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'API',
      theme: ThemeData(
        primarySwatch: Colors.green,
      ),
      home: const MyHomePage(title: 'API'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {

  final _formKey = GlobalKey<FormState>();
  apiUrl data = new apiUrl.empty();

  final TextEditingController urlController = TextEditingController();
  final TextEditingController dataController = TextEditingController();

  clearTextInput(){
    urlController.clear();
    setState(() {
      apidata = 'Kosong';
    });;
  }

  Future _GetAPI(var url) async{
    var response = await http.read(Uri.parse(url));
    // var jsonResponse = convert.jsonDecode(response);
    return response;
  }

  var apidata = 'Welcome';

  @override
  void dispose(){
    urlController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: [
          IconButton(
            onPressed: (){
              clearTextInput();
            },
            icon: Icon(Icons.refresh),
            padding: const EdgeInsets.only(right: 8)
          ),
        ],
      ),
      body: Form(
        key: _formKey,
        child: SingleChildScrollView(
          child: Column(
            children: [
              Container(
                padding: EdgeInsets.fromLTRB(16, 0, 16, 0),
                child : TextFormField(
                  decoration: InputDecoration(
                    labelText: 'URL: https://jsonplaceholder.typicode.com/posts',
                  ),
                  controller: urlController,
                ),
              ),
              OutlinedButton(
                style: OutlinedButton.styleFrom(
                  primary: Colors.white,
                  backgroundColor: Colors.blueGrey,
                ),
                onPressed: () async{
                  if(_formKey.currentState!.validate()){
                    data.url = urlController.text;
                    print(data.url);
                    apidata = await _GetAPI(data.url);
                    print('response: $apidata');
                    setState(() {
                      apidata = apidata;
                    });

                  }
                },
                child: Text('Execute'),
              ),
              Container(
                padding: EdgeInsets.fromLTRB(16, 0, 16, 0),
                width: 350,
                height: 510,
                decoration: BoxDecoration(
                    border: Border.all()
                ),
                child : SingleChildScrollView(
                  child: Text('$apidata'),
                ),
              ),
            ],
          ),
        )
      ),

    );
  }
}
