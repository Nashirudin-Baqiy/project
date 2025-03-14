import 'package:flutter/material.dart';
import 'package:flutter/widgets.dart';

final String tableToDo = 'todo';

class ToDoFields{
  static final List<String> values = [
    /// Add all fields
    id,
    title,
    desc,
    date,
    time,
    isFinalised,
    color,
    alarm,
  ];

  static final String id = '_id';
  static final String title = 'title';
  static final String desc = 'desc';
  static final String date = 'date';
  static final String time = 'time';
  static final String isFinalised = 'isFinalised';
  static final String alarm = 'alarm';
  static final String color = 'color';
}

class ToDo {
  static int number = 0;
  var id;
  var title;
  var desc;
  var date;
  var time;
  var isFinalised;
  var color;
  var alarm;

  ToDo({
    this.id,
    required this.title,
    required this.desc,
    required this.date,
    required this.time,
    required this.isFinalised,
    required this.alarm,
    required this.color, });


  ToDo copy({
    int? id,
    String? title,
    String? desc,
    String? date,
    String? time,
    bool? isFinalised,
    bool? alarm,
    Color? color
  }) =>
      ToDo(
       id: id ?? this.id,
       title: title ?? this.title,
       desc: desc ?? this.desc,
       date: date ?? this.date,
       time: time ?? this.time,
       isFinalised: isFinalised ?? this.isFinalised,
        alarm: alarm ?? this.alarm,
       color: color ?? this.color,
      );


  ToDo.empty(){
    this.title = '';
    this.desc = '';
    this.date = '';
    this.time = '';
    this.isFinalised = false;
    this.alarm = false;
    this.color = Colors.white;
  }

  Map<String, Object?> toJson() => {
    ToDoFields.id: this.id,
    ToDoFields.title: this.title,
    ToDoFields.desc: this.desc,
    ToDoFields.date: this.date,
    ToDoFields.time: this.time,
    ToDoFields.isFinalised: this.isFinalised? 1 : 0,
    ToDoFields.alarm: this.alarm? 1 : 0,
    ToDoFields.color: color.toString().substring(6,16),
  };

  static ToDo fromJson(Map<String, Object?> json){
    int color;
    color = int.parse(json[ToDoFields.color] as String);
    return ToDo(
      id: json[ToDoFields.id] as int?,
      title: json[ToDoFields.title] as String,
      desc: json[ToDoFields.desc] as String,
      date: json[ToDoFields.date] as String,
      time: json[ToDoFields.time] as String,
      isFinalised: json[ToDoFields.isFinalised] == 1,
      alarm: json[ToDoFields.alarm] == 1,
      color: Color(color),
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id' : id,
      'title': title,
      'desc': desc,
      'date': date,
      'time' : time,
      'isFinalised' : isFinalised,
      'alarm' : alarm,
      'Color' : color,
    };
  }

  set setTitle(String title){
    this.title = title;
  }
  set setDesc(String desc){
    this.desc = desc;
  }
  set setDate(String date){
    this.date = date;
  }
  set setTime(String time){
    this.time = time;
  }
  set setFinal(bool finalised){
    this.isFinalised = finalised;
  }
  set setAlarm(bool alarm){
    this.alarm = alarm;
  }
  set setColor(Color color){
    this.color = color;
  }

  String get getTitle{
    return title;
  }
  String get getDesc{
    return desc;
  }
  String get getDate{
    return date;
  }
  String get getTime{
    return time;
  }
  bool get getIsFinalised{
    return isFinalised;
  }
  bool get getAlarm{
    return alarm;
  }
  Color get getColor{
    return color;
  }

  int get getId{
    return id;
  }

  @override
  String toString() {
    return 'ToDo{id: $id, title: $title, desc: $desc,date: $date, time : $time, isFinalised : $isFinalised, alarm : $alarm, Color : $color}';
  }
}
