import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class ExtraScreen extends StatelessWidget {
  const ExtraScreen({Key? key}) : super(key: key);

  static const platform = MethodChannel('samples.flutter.dev/accomplish');

  Future<void> accomplish() async {
    try {
      await platform.invokeMethod('missionComplete');

    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('새로운 플러터 화면'),
      ),
      body: Center(
        // child: Text('이것은 코틀린에서 호출한 플러터 화면입니다.'),
        child: Column(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Text('이것은 코틀린에서 호출한 플러터 화면입니다.'),
            ElevatedButton(
              onPressed: accomplish,
              child: const Text('mission complete'),
            ),
          ],
        )
      ),
    );
  }
}
