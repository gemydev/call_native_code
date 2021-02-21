import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Call Native Code Demo',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: MyHomePage(),
    );
  }
}

class MyHomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const channel = MethodChannel("CallNativeCode/Kotlin");

  playMusic() async {
    try {
      await channel.invokeMethod("playMusic");
    } on PlatformException catch (ex) {
      print(ex.message);
    }
  }

  stopMusic() async {
    try {
      await channel.invokeMethod("stopMusic");
    } on PlatformException catch (ex) {
      print(ex.message);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Call Native Code"),
        centerTitle: true,
      ),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: <Widget>[
          Text(
            'Music Player',
          ),
          Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: [
              RaisedButton(child: Text("Play"), onPressed: playMusic),
              RaisedButton(child: Text("Stop"), onPressed: stopMusic),
            ],
          ),
        ],
      ),
    );
  }
}
