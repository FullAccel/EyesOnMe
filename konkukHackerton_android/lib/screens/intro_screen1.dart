import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';

class IntroScreen1 extends StatelessWidget {
  const IntroScreen1({super.key});

  @override
  Widget build(BuildContext context) {
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
