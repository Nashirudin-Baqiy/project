import 'package:flutter/material.dart';

class FilterForm extends StatefulWidget {
  @override
  State<FilterForm> createState() => _FilterFormState();
}

class _FilterFormState extends State<FilterForm> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('My Timesheet'),
      ),
      body: Center(
        child: Text('Form'),
      ),
      bottomNavigationBar: Padding(
        padding: EdgeInsets.all(8.0),
        child: ElevatedButton(
          onPressed: () {},
          style: ElevatedButton.styleFrom(
            onPrimary: Colors.white,
            primary: Colors.deepPurple,
            minimumSize: Size(88, 50),
          ),
          child: Text('Search'),
        ),
      ),
    );
  }
}
