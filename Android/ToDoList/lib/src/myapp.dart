import 'package:flutter/material.dart';
import 'form.dart';
import 'package:todolist/models/todo_model.dart';
import 'db.dart';
import 'dart:async';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'To Do List',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const TDL(title: 'Things To Do'),
    );
  }
}

class TDL extends StatefulWidget {
  const TDL({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<TDL> createState() => _TDLState();
}

class _TDLState extends State<TDL> {
  late List<Task> todoList = [];
  Task task = new Task.empty();

  @override
  void initState() {
    super.initState();

    refreshtodolist();
  }

  Future refreshtodolist() async {
    this.todoList = await ToDoListDatabase.instance.readAllToDo();
    setState(() => {});
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
        actions: [
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 16),
            child: Icon(Icons.search),
          ),
          Icon(Icons.sort),
        ],
      ),
      body: ListView.builder(
        itemCount: todoList.length,
        itemBuilder: (context, index) {
          return GestureDetector(
            onLongPress: () => navigateToDetail(index),
            child: Card(
              child: Container (
                child: Row (
                  children: [
                    Expanded(
                      flex: 100,
                      child: Column(
                        children: <Widget>[
                          Row(children: [
                            Text('${todoList[index].title}',
                                style: TextStyle(fontWeight: FontWeight.bold,
                                  fontSize: 20,)
                            ),
                          ]),
                          Row(
                            children: [
                              Padding(padding: EdgeInsets.fromLTRB(16,0,0,0),
                                  child : Text('${todoList[index].desc}',
                                    style: TextStyle(fontSize: 16 ),)
                              )
                            ],
                          ),
                          Row(
                            children: [
                              Padding(padding: EdgeInsets.fromLTRB(16,0,0,0),
                                child: Text('${todoList[index].date}',
                                  style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
                              ),
                            ],
                          ),
                          Row(
                            children: [
                              Padding(padding: EdgeInsets.fromLTRB(16,0,0,0),
                                child: Text('${todoList[index].time}',
                                    style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16)),
                              ),
                            ],
                          ),
                          Align(
                            alignment: Alignment.centerRight,
                            child: IconButton(
                              icon: Icon(
                                Icons.delete,
                                color: Colors.red,
                              ),
                              onPressed: () {
                                delTask(todoList[index].id);
                              },
                            ),
                          )
                        ],
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
        onPressed: createForm,
        tooltip: 'Add',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }

  void createForm() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => FormTDL(todoList,null)),
    ).then(refresh);
  }

  void navigateToDetail(int index) async {
    await Navigator.push(context, MaterialPageRoute(builder: (context) {
      return FormTDL(todoList, index);
    })).then(refresh);

  }

  FutureOr refresh(dynamic value) {
    setState(() {
      refreshtodolist();
    });
  }

  Future updateToDoList(Task task) async {
    await ToDoListDatabase.instance.update(task);
  }

  Future delTask(int id) async{
    await ToDoListDatabase.instance.delete(id).then(refresh);
  }
}