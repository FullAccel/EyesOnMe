import 'package:flutter/material.dart';

class ExtraScreen extends StatelessWidget {
  const ExtraScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('새로운 플러터 화면'),
      ),
      body: const Center(
        child: Text('이것은 코틀린에서 호출한 플러터 화면입니다.'),
      ),
    );
  }
}
