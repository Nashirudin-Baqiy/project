import 'package:flutter/material.dart';
import 'package:intl/date_symbol_data_local.dart';
import 'package:table_calendar/table_calendar.dart';
import 'utils.dart';

void main() {
  initializeDateFormatting().then((_) => runApp(const MyApp()));
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TableCalendar Example',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: StartPage(),
    );
  }
}

class StartPage extends StatefulWidget {
  @override
  _StartPageState createState() => _StartPageState();
}

class _StartPageState extends State<StartPage> {
  late final ValueNotifier<List<Event>> _selectedEvents;
  CalendarFormat _calendarFormat = CalendarFormat.month;
  RangeSelectionMode _rangeSelectionMode = RangeSelectionMode
      .toggledOff; // Can be toggled on/off by longpressing a date
  DateTime _focusedDay = DateTime.now();
  DateTime? _selectedDay;
  DateTime? _rangeStart;
  DateTime? _rangeEnd;

  @override
  void initState() {
    super.initState();

    _selectedDay = _focusedDay;
    _selectedEvents = ValueNotifier(_getEventsForDay(_selectedDay!));
  }

  @override
  void dispose() {
    _selectedEvents.dispose();
    super.dispose();
  }

  List<Event> _getEventsForDay(DateTime day) {
    // Implementation example
    return kEvents[day] ?? [];
  }

  List<Event> _getEventsForRange(DateTime start, DateTime end) {
    // Implementation example
    final days = daysInRange(start, end);

    return [
      for (final d in days) ..._getEventsForDay(d),
    ];
  }

  void _onDaySelected(DateTime selectedDay, DateTime focusedDay) {
    if (!isSameDay(_selectedDay, selectedDay)) {
      setState(() {
        _selectedDay = selectedDay;
        _focusedDay = focusedDay;
        _rangeStart = null; // Important to clean those
        _rangeEnd = null;
        _rangeSelectionMode = RangeSelectionMode.toggledOff;
      });

      _selectedEvents.value = _getEventsForDay(selectedDay);
    }
  }

  void _onRangeSelected(DateTime? start, DateTime? end, DateTime focusedDay) {
    setState(() {
      _selectedDay = null;
      _focusedDay = focusedDay;
      _rangeStart = start;
      _rangeEnd = end;
      _rangeSelectionMode = RangeSelectionMode.toggledOn;
    });

    // `start` or `end` could be null
    if (start != null && end != null) {
      _selectedEvents.value = _getEventsForRange(start, end);
    } else if (start != null) {
      _selectedEvents.value = _getEventsForDay(start);
    } else if (end != null) {
      _selectedEvents.value = _getEventsForDay(end);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('TableCalendar Example'),
      ),
      body: Column(
        children: [
          TableCalendar<Event>(
            firstDay: kFirstDay,
            lastDay: kLastDay,
            focusedDay: _focusedDay,
            selectedDayPredicate: (day) => isSameDay(_selectedDay, day),
            rangeStartDay: _rangeStart,
            rangeEndDay: _rangeEnd,
            calendarFormat: _calendarFormat,
            rangeSelectionMode: _rangeSelectionMode,
            eventLoader: _getEventsForDay,
            startingDayOfWeek: StartingDayOfWeek.monday,
            calendarStyle: const CalendarStyle(
              todayDecoration: BoxDecoration(
                  color: Colors.red,
                  shape: BoxShape.rectangle,
              ),
              markersAlignment: Alignment.bottomRight,
            ),
            calendarBuilders: CalendarBuilders(
              markerBuilder: (context,date,events){
                if(events.isNotEmpty){
                  return Container(
                    decoration: const BoxDecoration(
                      shape: BoxShape.rectangle,
                      color: Colors.blue,
                    ),
                    width: 15,
                    height: 15,
                    child: Center(
                      child: Text(
                        '${events.length}',
                        style: const TextStyle().copyWith(
                          color: Colors.white,
                          fontSize: 12.0,
                        ),
                      ),
                    ),
                  );
                }
              },
              defaultBuilder: (context, date, focusedday){
                return Container(
                  color: Colors.white,
                  width: 53,
                  height: 53,
                  child: Center(
                    child: Text(date.day.toString(),
                        style: const TextStyle(fontSize: 16, color: Colors.black)),
                  ),
                );
              },
              selectedBuilder: (context, date, focusedday){
                return Container(
                  color: Colors.green,
                  width: 53,
                  height: 53,
                  child: Center(
                    child: Text(date.day.toString(),
                        style: const TextStyle(fontSize: 16, color: Colors.black)),
                  ),
                );
              },
              todayBuilder: (context, date, focusedday){
                return Container(
                  color: Colors.yellow,
                  width: 53,
                  height: 53,
                  child: Center(
                    child: Text(date.day.toString(),
                        style: TextStyle(fontSize: 16, color: Colors.black)),
                  ),
                );
              }
            ),
            onDaySelected: _onDaySelected,
            onRangeSelected: _onRangeSelected,
            onFormatChanged: (format) {
              if (_calendarFormat != format) {
                setState(() {
                  _calendarFormat = format;
                });
              }
            },
            onPageChanged: (focusedDay) {
              _focusedDay = focusedDay;
            },
          ),
          const SizedBox(height: 8.0),
          Expanded(
            child: ValueListenableBuilder<List<Event>>(
              valueListenable: _selectedEvents,
              builder: (context, value, _) {
                return ListView.builder(
                  itemCount: value.length,
                  itemBuilder: (context, index) {
                    return Container(
                      margin: const EdgeInsets.symmetric(
                        horizontal: 12.0,
                        vertical: 4.0,
                      ),
                      decoration: BoxDecoration(
                        border: Border.all(),
                        borderRadius: BorderRadius.circular(12.0),
                      ),
                      child: ListTile(
                        onTap: (){
                          print('${value[index]}');
                          alert(context, value, index);
                          },
                        title: Text('${value[index]}'),
                      ),
                    );
                  },
                );
              },
            ),
          ),
        ],
        ),
    );
  }
}

void alert(BuildContext context, List value, int index){
  AlertDialog dialog = AlertDialog(
    title: Text('${value[index]}'),
    content: Text('${value[index]}'),
    actions: [
      TextButton(
        onPressed: () => Navigator.pop(context),
        child: Text('OK'),
      ),
    ],
  );

  showDialog(context: context, builder: (context) => dialog);
  return;
}