import 'package:flutter/material.dart';

class Test extends StatelessWidget {
  const Test({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Image.network(
          "https://hackerton.s3.ap-northeast-2.amazonaws.com/0afe91b3-25f2-4ee0-ba4b-822c7e95da09..png"),
    );
  }
}
