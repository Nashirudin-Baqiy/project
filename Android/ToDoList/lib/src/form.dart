import 'package:flutter/material.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import '../models/todo_model.dart';
import 'db.dart';

class FormTDL extends StatefulWidget {
  int? index;
  List<Task> todolist;
  FormTDL (this.todolist, this.index);

  @override
  State<StatefulWidget> createState() {
    return _FormTDLState();
  }
}

class _FormTDLState extends State<FormTDL> {
  final _formKey = GlobalKey<FormState>();
  Task tempTask = new Task.empty();
  final TextEditingController titleController = TextEditingController();
  final TextEditingController descController = TextEditingController();
  final TextEditingController dateController = TextEditingController();
  final TextEditingController timeController = TextEditingController();
  bool isSwitched = false;
  // DateTime selectedDate = DateTime.now();
  // TimeOfDay selectedTime = TimeOfDay.now();
  String pickedDate = 'Pick Date';// DateTime.now().toString().substring(0, 10);
  String pickedTime = 'Pick Time';// TimeOfDay.now().toString().substring(10,15);

  @override
  void dispose(){
    titleController.dispose();
    descController.dispose();
    dateController.dispose();
    timeController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if(widget.index != null){
      titleController.text = widget.todolist[widget.index!].getTitle;
      descController.text = widget.todolist[widget.index!].getDesc;
      dateController.text = widget.todolist[widget.index!].getDate;
      timeController.text = widget.todolist[widget.index!].getTime;
    }
    return Scaffold(
      appBar: AppBar(
        title: Text("New"),
      ),
      body: Form(
        key: _formKey,
        child: Column(
            children: <Widget>[
              Container(
                padding: EdgeInsets.fromLTRB(16, 0, 0, 0),
                child : TextFormField(
                  decoration: InputDecoration(
                    labelText: 'Title',
                  ),
                  controller: titleController,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: [
                  OutlinedButton(
                      onPressed: () {
                        DatePicker.showDatePicker(context,
                            showTitleActions: true,
                            minTime: DateTime.now(),
                            maxTime: DateTime.now().add(Duration (days: 365)), onChanged: (date) {
                              print('change $date');
                              setState(() {
                                String temp;
                                temp = date.toString();
                                pickedDate = temp.substring(0, 10);
                                tempTask.setDate = pickedDate;
                              });
                            }, onConfirm: (date) {
                              // _confirmDate(date);
                              print('confirm $date');
                            }, currentTime: DateTime.now(), locale: LocaleType.id);
                      },
                      child: Text(
                        // "${selectedDate}".split(' ')[0],
                        '$pickedDate',
                        style: TextStyle(color: Colors.blue),
                      )
                  ),
                  OutlinedButton(
                      onPressed: () {
                        DatePicker.showTimePicker(context,
                            showTitleActions: true,
                            onChanged: (time) {
                              print('change $time');
                              setState(() {
                                String temp;
                                temp = time.toString();
                                pickedTime = temp.substring(11,16);
                                tempTask.setTime = pickedTime;
                              });
                            }, onConfirm: (time) {
                              // _confirmTime(time);
                              print('confirm $time');
                            }, currentTime: DateTime.now(), locale: LocaleType.id);
                      },
                      child: Text(
                        // "${selectedTime.format(context)}",
                        '$pickedTime',
                        style: TextStyle(color: Colors.blue),
                      )
                  ),
                ],
              ),
              Container(
                  padding: EdgeInsets.fromLTRB(16, 0, 0, 0),
                  child: TextFormField(
                    decoration: InputDecoration(
                      labelText: 'Description',
                    ),
                    controller: descController,
                  )
              ),
            ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          if(widget.index != null){
            tempTask.setTitle = titleController.text;
            tempTask.setDesc = descController.text;

            widget.todolist[widget.index!].title = tempTask.title;
            widget.todolist[widget.index!].desc = tempTask.desc;
            widget.todolist[widget.index!].date = tempTask.date;
            widget.todolist[widget.index!].time = tempTask.time;
            print(tempTask.desc);
            print(tempTask.title);
            print(tempTask.date);
            print(tempTask.time);
            print(tempTask);
            updateValueTask(widget.todolist[widget.index!]);
            print(widget.todolist[widget.index!]);
            Navigator.pop(context);

          } else if (_formKey.currentState!.validate()) {
            tempTask.setTitle = titleController.text;
            tempTask.setDesc = descController.text;

            print(tempTask.desc);
            print(tempTask.title);
            print(tempTask.date);
            print(tempTask.time);
            addTask(tempTask);
            widget.todolist.add(tempTask);
            print(widget.todolist);
            print(widget.todolist.length);
            Navigator.pop(context);
          }
        },
        child: const Icon(Icons.check),
        backgroundColor: Colors.deepOrange,
      ),
    );
  }

  Future addTask(Task task) async {
    await ToDoListDatabase.instance.create(task);
  }
  Future updateValueTask(Task task) async {
    await ToDoListDatabase.instance.update(task);
  }
}