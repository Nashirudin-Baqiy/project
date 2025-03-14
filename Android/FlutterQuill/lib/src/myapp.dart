import 'package:flutter_quill/flutter_quill.dart' as quill;
import 'package:flutter/material.dart';

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Quill Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const MyHomePage(title: 'Quill Demo'),
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

  quill.QuillController _controller = quill.QuillController.basic();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: SingleChildScrollView(
            child: Column(
              children: [
                quill.QuillToolbar.basic(controller: _controller),
                Expanded(
                  child: Container(
                    child: quill.QuillEditor.basic(
                      controller: _controller,
                      readOnly: false, // true for view only mode
                    ),
                  ),
                )
              ],
            )
        )
    );
  }
}
