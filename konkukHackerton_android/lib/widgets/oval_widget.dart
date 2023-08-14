import 'package:flutter/material.dart';

class OvalWidget extends StatelessWidget {
  const OvalWidget({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        ClipOval(
          clipper: MyUpperClipper(),
          child: Container(
            height: MediaQuery.of(context).size.height * 0.67,
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  const Color(0xFF3BDE7C),
                  const Color(0xFFB7E8CA),
                ],
              ),
            ),
          ),
        ),
        ClipOval(
          clipper: MyLowerClipper(),
          child: Container(
            height: MediaQuery.of(context).size.height * 0.33,
            decoration: BoxDecoration(
              gradient: LinearGradient(
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
                colors: [
                  const Color(0xFFB7E8CA),
                  Colors.white,
                ],
              ),
            ),
          ),
        ),
      ],
    );
  }
}

class MyUpperClipper extends CustomClipper<Rect> {
  @override
  Rect getClip(Size size) {
    return Rect.fromLTWH(0, 0, 800, size.height);
  }

  @override
  bool shouldReclip(covariant CustomClipper<Rect> oldClipper) {
    return false;
  }
}

class MyLowerClipper extends CustomClipper<Rect> {
  @override
  Rect getClip(Size size) {
    return Rect.fromLTWH(0, 0, 800, size.height);
  }

  @override
  bool shouldReclip(covariant CustomClipper<Rect> oldClipper) {
    return false;
  }
}
