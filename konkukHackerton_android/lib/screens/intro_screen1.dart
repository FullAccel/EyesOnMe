import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';

class IntroScreen1 extends StatelessWidget {
  const IntroScreen1({super.key});

  void _seconds() {
    Future.delayed(Duration(milliseconds: 500), () {});
    Get.offAllNamed('/');
  }

  @override
  Widget build(BuildContext context) {
    //_seconds();

    return Scaffold(
      body: Stack(
        children: [
          OvalWidget(),
          Center(
            child: Container(
              padding: EdgeInsets.only(bottom: 49),
              child: Image.asset(
                "assets/images/logo_en.png",
                scale: 1.1,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
