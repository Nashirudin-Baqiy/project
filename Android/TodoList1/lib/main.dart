import 'package:flutter/material.dart';
import 'package:flutter_datetime_picker/flutter_datetime_picker.dart';
import 'dart:async';
import 'package:flutter/widgets.dart';
import 'package:todolist/db_todo.dart';
import 'toDo.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'ToDo List',
      home: MyHomePage(title: 'Things to Do'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}


class _MyHomePageState extends State<MyHomePage> {
  bool isChecked = false;
  ToDo toDo = new ToDo.empty();
  List<ToDo> toDos = [];
  List<ToDo> searchToDo = [];
  DateTime now = DateTime.now();
  List<String> dates = [];

  @override
  void initState() {
    super.initState();

    refreshToDos();
  }

  Future refreshToDos() async {
    this.toDos = await ToDoDatabase.instance.readAllToDo();
    setState(() => {});
  }

  @override
  Widget build(BuildContext context) {

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: [
          IconButton(onPressed: addAgenda, icon: Icon(Icons.search)),
          IconButton(onPressed: addAgenda, icon: Icon(Icons.filter_list_sharp))
        ],
      ),
      body: ListView.builder(
        itemCount: toDos.length,
        itemBuilder: (context, index) {
          return GestureDetector(
            onLongPress: () => navigateToDetail(index),
            child: Card(
              child: Container(
                child: Row(
                  children: [
                    Expanded(
                      flex: 98,
                      child: Column(
                        children: <Widget>[
                          Row(children: [
                            Checkbox(
                              checkColor: Colors.white,
                              activeColor: Colors.deepOrange,
                              value: toDos[index].isFinalised,
                              onChanged: (bool? value) {
                                setState(() {
                                  toDos[index].isFinalised = value;
                                  print(toDos[index].color);
                                  updateToDo(toDos[index]);
                                });
                              },
                            ),
                            Text('${toDos[index].title}',
                                style: toDos[index].isFinalised == false ?
                                TextStyle(fontWeight: FontWeight.bold,
                                  fontSize: 20,): TextStyle(decoration:
                                TextDecoration.lineThrough, fontWeight: FontWeight.bold,
                                  fontSize: 20,)
                            ),
                          ]),
                          Row(
                            children: [
                              Padding(padding: EdgeInsets.fromLTRB(16,0,0,0),
                                  child : Text('${toDos[index].desc}',
                                    style: TextStyle(fontSize: 16 ),)
                              )
                            ],
                          ),
                          Row(
                            children: [
                              Padding(padding: EdgeInsets.fromLTRB(16,0,0,0),
                                child : Text('${toDos[index].date} ${toDos[index].time}',
                                  style: TextStyle(fontWeight: FontWeight.bold),
                                ),
                              )
                            ],
                          ),
                          Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: <Widget>[
                              Text('Alarm'),
                              Switch(
                                value: toDos[index].alarm,
                                onChanged: (bool? value) {
                                  setState(() {
                                    toDos[index].alarm= value;
                                    updateToDo(toDos[index]);
                                  });
                                },
                                activeTrackColor: Colors.lightGreenAccent,
                                activeColor: Colors.green,
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                    Expanded(
                      flex: 2,
                      child: Container(
                        color: toDos[index].color, //Isi pake color class
                        height: 100,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: addAgenda,
        tooltip: 'Add',
        child: Icon(Icons.add),
        backgroundColor: Colors.deepOrange,
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  void addAgenda() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => AddTask(toDos, null)),

    ).then(onGoBack);
  }

  void navigateToDetail(int index) async {
    await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return AddTask(toDos, index);
    })).then(onGoBack);

  }

  FutureOr onGoBack(dynamic value) {
    setState(() {
      refreshToDos();
    });
  }

  Future updateToDo(ToDo toDo) async {
    await ToDoDatabase.instance.update(toDo);
  }

// void search(String searchText){
//   searchToDo.clear();
// }
}

class AddTask extends StatefulWidget {
  int? index;
  List<ToDo> toDos;
  AddTask(this.toDos, this.index);


  @override
  State<StatefulWidget> createState(){
    return _AddTaskState();
  }
}

class _AddTaskState extends State<AddTask> {
  final _formKey = GlobalKey<FormState>();
  ToDo tempToDo = new ToDo.empty();
  TextEditingController titleController = TextEditingController();
  TextEditingController dateController = TextEditingController();
  TextEditingController timeController = TextEditingController();
  TextEditingController descController = TextEditingController();
  String pickedDate = 'Pick the date';
  String pickedTime = 'Pick the time';
  Item? pickedColor;
  DateTime now = DateTime.now();
  bool isSwitched = false;

  List<Item> availableColors = <Item>[
    const Item('Green', Color(0xff4caf50)),
    const Item('Red', Color(0xfff44336)),
    const Item('Blue', Color(0xff2196f3)),
    const Item('Yellow', Color(0xffffeb3b)),
    const Item('Purple', Color(0xff9c27b0)),
  ];

  @override
  void dispose(){
    titleController.dispose();
    dateController.dispose();
    timeController.dispose();
    descController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    if(widget.index != null){
      titleController.text = widget.toDos[widget.index!].getTitle;
      descController.text = widget.toDos[widget.index!].getDesc;
      dateController.text = widget.toDos[widget.index!].getDate;
      timeController.text = widget.toDos[widget.index!].getTime;
    }
    return Scaffold(
      appBar: AppBar(
        title: Text('Add Task'),
      ),
      body: Form(
        key: _formKey,
        child: Column(
            children: [
              Row(
                children: [
                  Expanded(child: DropdownButton<Item>(
                    hint: Text('Pick Color'),
                    value: pickedColor,
                    onChanged: (value) {
                      setState(() {
                        pickedColor = value;
                        tempToDo.color = pickedColor!.color;
                      });
                    },
                    items: availableColors.map((Item user) {
                      return  DropdownMenuItem<Item>(
                        value: user,
                        child: Row(
                          children: <Widget>[
                            SizedBox(width: 200,
                              child: Container(
                                color: user.color,
                                height: 100,
                              ),
                            ),
                          ],
                        ),
                      );
                    }).toList(),
                  ),
                  ),
                ],
              ),
              Row(
                children: [
                  Expanded(
                    child : TextFormField(
                      decoration: InputDecoration(
                        labelText: 'Title',
                      ),
                      controller: titleController,
                    ),
                  ),
                ],
              ),
              Row(
                children: [
                  Expanded(
                    child: OutlinedButton(
                        onPressed: () {
                          DatePicker.showDatePicker(context,
                              showTitleActions: true,
                              minTime: DateTime(2018, 3, 5),
                              maxTime: DateTime(2099, 12, 31),
                              onChanged: (date) {
                                print('change $date');
                                setState(() {
                                  String temp;
                                  temp = date.toString();
                                  pickedDate = temp.substring(0, 10);
                                  tempToDo.setDate = pickedDate;
                                });
                              }, onConfirm: (date) {
                                print('confirm $date');
                              }, currentTime: DateTime.now(), locale: LocaleType.id);
                        },
                        style: OutlinedButton.styleFrom(
                          side: BorderSide(width: 1.0, color: Colors.grey),
                        ),
                        child: Text(
                          'Date : $pickedDate',
                          style: TextStyle(color: Colors.black),
                        )
                    ),
                  ),
                  Expanded(
                    child: OutlinedButton(
                      onPressed: () {
                        DatePicker.showTimePicker(context,
                            showTitleActions: true,
                            onChanged: (time) {
                              print('change $time');
                              setState(() {
                                String temp;
                                temp = time.toString();
                                pickedTime = temp.substring(11,16);
                                tempToDo.setTime = pickedTime;
                              });
                            }, onConfirm: (time) {
                              print('confirm $time');
                            }, currentTime: DateTime.now(), locale: LocaleType.id);
                      },
                      style: OutlinedButton.styleFrom(
                        side: BorderSide(width: 1.0, color: Colors.grey),
                      ),
                      child: Text(
                        'Time : $pickedTime',
                        style: TextStyle(color: Colors.black),
                      ),
                    ),
                  ),
                ],
              ),
              Row(
                children: [
                  Expanded(
                      child: TextFormField(
                        decoration: InputDecoration(
                          labelText: 'Description',
                        ),
                        controller: descController,
                      )
                  ),
                ],
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: <Widget>[
                  Text('Alarm'),
                  Switch(
                    value: isSwitched,
                    onChanged: (value){
                      setState(() {
                        tempToDo.setAlarm = value;
                        isSwitched=value;
                        print(isSwitched);
                      });
                    },
                    activeTrackColor: Colors.lightGreenAccent,
                    activeColor: Colors.green,
                  ),
                ],
              ),
            ]
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          if(widget.index != null){
            tempToDo.setTitle = titleController.text;
            tempToDo.setDesc = descController.text;

            widget.toDos[widget.index!].title = tempToDo.title;
            widget.toDos[widget.index!].desc = tempToDo.desc;
            widget.toDos[widget.index!].date = tempToDo.date;
            widget.toDos[widget.index!].time = tempToDo.time;
            widget.toDos[widget.index!].color = tempToDo.color;
            widget.toDos[widget.index!].alarm = tempToDo.alarm;
            print(tempToDo.desc);
            print(tempToDo.title);
            print(tempToDo.date);
            print(tempToDo.time);
            print(tempToDo.color);
            print(tempToDo.alarm);
            print(tempToDo);
            updateValueToDo(widget.toDos[widget.index!]);
            print(widget.toDos[widget.index!]);
            Navigator.pop(context);

          } else if (_formKey.currentState!.validate()) {
            tempToDo.setTitle = titleController.text;
            tempToDo.setDesc = descController.text;

            print(tempToDo.desc);
            print(tempToDo.title);
            print(tempToDo.date);
            print(tempToDo.time);
            print(tempToDo.color);
            print(tempToDo.alarm);
            addToDo(tempToDo);
            widget.toDos.add(tempToDo);
            print(widget.toDos);
            print(widget.toDos.length);
            Navigator.pop(context);
          }
        },
        child: const Icon(Icons.check),
        backgroundColor: Colors.deepOrange,
      ),
    );
  }
  Future addToDo(ToDo toDo) async {
    await ToDoDatabase.instance.create(toDo);
  }
  Future delToDo(int id) async{
    await ToDoDatabase.instance.delete(id);
  }

  Future updateValueToDo(ToDo toDo) async {
    await ToDoDatabase.instance.update(toDo);
  }
}

class Item {
  const Item(this.name,this.color);
  final String name;
  final Color color;
  Color get getColor{
    return color;
  }
}




