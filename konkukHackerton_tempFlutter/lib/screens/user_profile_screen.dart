import 'dart:convert';
import 'dart:math';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../models/member_model.dart';
import '../widgets/user_info_widget.dart';

class UserProfileScreen extends StatefulWidget {
  const UserProfileScreen({super.key});

  @override
  State<UserProfileScreen> createState() => _UserProfileScreenState();
}

class _UserProfileScreenState extends State<UserProfileScreen> {
  static const platform = MethodChannel("samples.flutter.dev/battery");
  String s = "";
  late MemberModel memberData;

  Future<void> _getMemberData() async {
    try {
      s = await platform.invokeMethod('getMemberData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
    setState(() {
      memberData = jsonDecode(s);
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _getMemberData();
  }

  @override
  Widget build(BuildContext context) {
    _getMemberData();
    return Scaffold(
      backgroundColor: Colors.white,
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(0.335.sh),
        child: Container(
          child: Column(
            children: [
              SizedBox(height: ScreenUtil().statusBarHeight),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(left: 24, top: 12),
                    child: Text(
                      "Profile",
                      style: TextStyle(
                        color: Color(0xFF3BDE7C),
                        fontSize: 22.sp,
                      ),
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 2, right: 2),
                    child: IconButton(
                      onPressed: () {},
                      icon: Row(
                        children: [
                          Icon(
                            Icons.notifications,
                            color: Colors.grey,
                            size: 24.sp,
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
              UserInfoWidget(
                memberData: memberData,
              ),
            ],
          ),
        ),
      ),
      body: Column(
        children: [
          Stack(
            children: [
              Container(
                alignment: Alignment.center,
                child: Column(
                  children: [
                    Text(
                      "플랜 누적 달성률",
                      style: TextStyle(
                        fontSize: 24.sp,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        SizedBox(width: 36),
                        Text(
                          "75",
                          style: TextStyle(
                            fontSize: 90.sp,
                            fontWeight: FontWeight.w600,
                            letterSpacing: -5,
                          ),
                        ),
                        Text(
                          "%",
                          style: TextStyle(
                            fontSize: 60.sp,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              ),
              CustomPaint(
                painter: ShadowDoughnut(),
              ),
              CustomPaint(
                painter: Doughnut1(),
              ),
              CustomPaint(
                painter: Doughnut2(),
              ),
            ],
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        onTap: (int index) {
          switch (index) {
            case 0:
              Get.offAllNamed("/");
              break;
            case 1:
              Get.offAllNamed("/plan");
              break;
            case 2:
              // TODO: challenge screen
              break;
            case 3:
              Get.offAllNamed("/profile");
              break;
          }
        },
        items: [
          BottomNavigationBarItem(
            icon: Icon(
              Icons.home,
              color: Color(0xFFBCBCBC),
              size: 32.sp,
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.calendar_month,
              color: Color(0xFFBCBCBC),
              size: 32.sp,
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.star,
              color: Color(0xFFBCBCBC),
              size: 32.sp,
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Icon(
              Icons.person_3_rounded,
              color: Color(0xFFBCBCBC),
              size: 32.sp,
            ),
            label: "",
          ),
        ],
      ),
    );
  }
}

class ShadowDoughnut extends CustomPainter {
  final Paint paintArc1 = Paint()
    ..isAntiAlias = true
    ..strokeWidth = 90
    ..style = PaintingStyle.stroke
    ..color = Color.fromRGBO(212, 212, 212, 0.8);

  @override
  void paint(Canvas canvas, Size size) {
    // TODO: implement paint
    canvas.drawArc(Rect.fromLTWH(14, -117, 380, 350), -pi / 9, 11 * pi / 9,
        false, paintArc1);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    // TODO: implement shouldRepaint
    return true;
  }
}

class Doughnut1 extends CustomPainter {
  final Paint paintArc1 = Paint()
    ..isAntiAlias = true
    ..strokeWidth = 90
    ..style = PaintingStyle.stroke
    ..color = Color(0xFFF0F0F0F0);

  @override
  void paint(Canvas canvas, Size size) {
    // TODO: implement paint
    canvas.drawArc(Rect.fromLTWH(14, -120, 380, 350), -pi / 9, 11 * pi / 9,
        false, paintArc1);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    // TODO: implement shouldRepaint
    return true;
  }
}

class Doughnut2 extends CustomPainter {
  @override
  void paint(Canvas canvas, Size size) {
    final rect = Rect.fromLTWH(14, -130, 380, 350);

    final gradient = LinearGradient(
      tileMode: TileMode.clamp,
      colors: [Color(0xFFD8FFE7), Color(0xFF3BDE7C)],
    );

    final double start = -pi / 9;

    final Paint paintArc2 = Paint()
      ..shader = gradient.createShader(rect)
      //..strokeCap = StrokeCap.round
      ..isAntiAlias = true
      ..strokeWidth = 90
      ..style = PaintingStyle.stroke
      ..color = Color(0xFF3BDE7C);

    // -20도(-pi/9) ~ 200도(11*pi/9)
    //

    canvas.drawArc(
        rect, pi / 3, 11 * pi / 9 - (pi / 3 - start), false, paintArc2);
  }

  @override
  bool shouldRepaint(covariant CustomPainter oldDelegate) {
    // TODO: implement shouldRepaint
    return true;
  }
}
