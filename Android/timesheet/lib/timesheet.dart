import 'package:flutter/material.dart';
import 'package:timesheet/filterform.dart';

class Timesheet extends StatefulWidget {
  @override
  State<Timesheet> createState() => _TimesheetState();
}

class _TimesheetState extends State<Timesheet> {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Timesheet'),
        actions: [
          Padding(
              padding: EdgeInsets.only(right: 20.0),
              child: GestureDetector(
                onTap: ToFilter,
                child: Icon(
                  Icons.filter_alt,
                  size: 26.0,
                ),
              )
          ),
        ],
      ),
      body: Center(
        child: Text('Data'),
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
          child: Text('Add Timesheet'),
        ),
      ),
    );
  }

  void ToFilter() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => FilterForm()),
    );
  }
}
