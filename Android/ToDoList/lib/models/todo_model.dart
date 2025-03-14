final String tableTask = 'task';
class TaskFields{
  static final List<String> values = [
    /// Add all fields
    id,
    title,
    desc,
    date,
    time,
  ];

  static final String id = '_id';
  static final String title = 'title';
  static final String desc = 'desc';
  static final String date = 'date';
  static final String time = 'time';
}

class Task {
  static int number = 0;
  var id;
  var title;
  var desc;
  var date;
  var time;

  Task({
    this.id,
    required this.title,
    required this.desc,
    required this.date,
    required this.time});

  Task copy({
    int? id,
    String? title,
    String? desc,
    String? date,
    String? time}) =>
      Task(
        id: id ?? this.id,
        title: title ?? this.title,
        desc: desc ?? this.desc,
        date: date ?? this.date,
        time: time ?? this.time,
      );

  Task.empty(){
    this.title = '';
    this.desc = '';
    this.date = '';
    this.time = '';
  }

  Map<String, Object?> toJson() => {
    TaskFields.id: this.id,
    TaskFields.title: this.title,
    TaskFields.desc: this.desc,
    TaskFields.date: this.date,
    TaskFields.time: this.time,
  };

  static Task fromJson(Map<String, Object?> json){
    return Task(
      id: json[TaskFields.id] as int?,
      title: json[TaskFields.title] as String,
      desc: json[TaskFields.desc] as String,
      date: json[TaskFields.date] as String,
      time: json[TaskFields.time] as String,
    );
  }

  Map<String, dynamic> toMap() {
    return {
      'id' : id,
      'title': title,
      'desc': desc,
      'date': date,
      'time': time,
    };
  }
  //setter
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
  //getter
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

  @override
  String toString() {
    return 'Task{id: $id, title: $title, desc: $desc, date: $date, time: $time}';
  }
}