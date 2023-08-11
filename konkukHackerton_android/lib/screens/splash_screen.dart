import 'dart:async';

import 'package:eom_fe/screens/intro_screen1.dart';
import 'package:eom_fe/screens/login_screen.dart';
import 'package:flutter/material.dart';

class SplashScreen extends StatefulWidget {
  final onPressedKakao, onPressedGoogle;

  SplashScreen({
    super.key,
    required this.onPressedKakao,
    required this.onPressedGoogle,
  });

  @override
  State<SplashScreen> createState() => _SplashScreenState();
}

class _SplashScreenState extends State<SplashScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    Timer(
      Duration(milliseconds: 1500),
      () {
        Navigator.pushReplacement(
          context,
          MaterialPageRoute(
            builder: (context) => LoginScreen(
                onPressedKakao: widget.onPressedKakao,
                onPressedGoogle: widget.onPressedGoogle),
          ),
        );
      },
    );
  }

  // _navigatetohome() async {
  //   await Future.delayed(Duration(milliseconds: 1500), () {});
  //   Navigator.pushReplacement(
  //     context,
  //     MaterialPageRoute(
  //       builder: (context) => LoginScreen(
  //           onPressedKakao: onPressedKakao, onPressedGoogle: onPressedGoogle),
  //     ),
  //   );
  // }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: IntroScreen1(),
    );
  }
}
