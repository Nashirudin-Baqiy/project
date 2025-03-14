import 'package:flutter/material.dart';
import 'package:timesheet/timesheet.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Timesheet',
      home: const MyHomePage(title: 'Demo Timesheet'),
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

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            OutlinedButton(
                onPressed: ToTimesheet,
                child: Text('Timesheet', style: TextStyle(color: Colors.black)),
                style: OutlinedButton.styleFrom(
                  padding: EdgeInsets.symmetric(horizontal: 50, vertical: 20),
                  elevation: 10,
                  backgroundColor: Colors.orange,
                ),
            ),
          ],
        ),
      ),
    );
  }

  void ToTimesheet() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => Timesheet()),
    );
  }
}
