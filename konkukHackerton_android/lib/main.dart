import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:permission_handler/permission_handler.dart';

import 'extra_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // GlobalKey를 생성하십시오.
  static final navigatorKey = GlobalKey<NavigatorState>();

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: navigatorKey, // navigatorKey를 MaterialApp에 추가
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // TRY THIS: Try running your application with "flutter run". You'll see
        // the application has a blue toolbar. Then, without quitting the app,
        // try changing the seedColor in the colorScheme below to Colors.green
        // and then invoke "hot reload" (save your changes or press the "hot
        // reload" button in a Flutter-supported IDE, or press "r" if you used
        // the command line to start the app).
        //
        // Notice that the counter didn't reset back to zero; the application
        // state is not lost during the reload. To reset the state, use hot
        // restart instead.
        //
        // This works for code too, not just values: Most code changes can be
        // tested with just a hot reload.
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
      routes: {
        '/extraScreen': (context) => const ExtraScreen(), // Define route for ExtraScreen
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  // Get battery level.
  String _batteryLevel = 'Unknown battery level.';

  @override
  void initState() {
    super.initState();

    setExtraScreenHandler();
  }

  // 코틀린 -> 플러터 화면 띄우려고 핸들러 설정
  void setExtraScreenHandler() {
    platform.setMethodCallHandler((call) async {
      print("setExtraScreenHandler");
      if (call.method == 'showExtraScreen') {
        print("showExtraScreen");
        _showExtraScreen();
      }
    });
  }

  Future<void> _getBatteryLevel() async {
    try {
      await platform.invokeMethod('kakaoLogin');
    } on PlatformException catch (e) {
      // batteryLevel = "Failed to get battery level: '${e.message}'.";
    }
  }

  Future<void> _getBatteryLevel2() async {
    try {
      await platform.invokeMethod('showAlarmList');
    } on PlatformException catch (e) {
      // batteryLevel = "Failed to get battery level: '${e.message}'.";
    }
  }

  Future<void> _showExtraScreen() async {
    try {
      Navigator.pushNamed(context, '/extraScreen'); // Navigate to ExtraScreen

    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  Future<bool> permission() async {
    Map<Permission, PermissionStatus> status =
    await [Permission.systemAlertWindow].request(); // [] 권한배열에 권한을 작성

    if (await Permission.systemAlertWindow.isGranted) {
      return Future.value(true);
    } else {
      return Future.value(false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return Material(
      child: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            ElevatedButton(
              onPressed: _getBatteryLevel,
              child: const Text('Login w/ Kakao'),
            ),
            ElevatedButton(
              onPressed: permission,
              child: const Text('Get Permission'),
            ),
            ElevatedButton(
              onPressed: _getBatteryLevel2,
              child: const Text('AlarmListActivity'),
            ),
          ],
        ),
      ),
    );
  }
}

/*
Future<void> _callFlutterFunction(String method, Map<String, dynamic> arguments) async {
  try {
    if (method == 'show_extra_screen') {
      final result = await MyApp.navigatorKey.currentState?.push(MaterialPageRoute(builder: (context) => const ExtraScreen()));
      print('Kotlin에서 전달된 데이터: $result');
    }
  } on PlatformException catch (e) {
    print('Error: ${e.message}');
  }
}
*/