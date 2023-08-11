import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class LoginScreen extends StatefulWidget {
  final onPressedKakao, onPressedGoogle;

  const LoginScreen({
    super.key,
    required this.onPressedKakao,
    required this.onPressedGoogle,
  });

  @override
  State<LoginScreen> createState() => _LoginScreenState();
}

class _LoginScreenState extends State<LoginScreen> {
  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          OvalWidget(),
          Column(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              Center(
                child: Container(
                  child: Image.asset(
                    "assets/images/logo_ko.png",
                    scale: 1.1,
                  ),
                ),
              ),
              SizedBox(
                height: 0.27.sh,
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Container(
                    decoration: BoxDecoration(
                      color: Colors.white,
                      borderRadius: BorderRadius.circular(5),
                    ),
                    width: 0.8.sw,
                    child: TextButton(
                      onPressed: widget.onPressedGoogle,
                      child: Row(
                        children: [
                          Image(
                            width: 30,
                            image: AssetImage("assets/images/logo_google.png"),
                          ),
                          SizedBox(width: 0.24.sw),
                          Text(
                            "구글 로그인",
                            style: TextStyle(
                              color: Colors.black,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                  const SizedBox(height: 10),
                  Container(
                    decoration: BoxDecoration(
                      color: Color(0xFFFEE500),
                      borderRadius: BorderRadius.circular(5),
                    ),
                    width: 0.8.sw,
                    child: TextButton(
                      onPressed: widget.onPressedKakao,
                      child: Row(
                        children: [
                          Image(
                            width: 30,
                            image: AssetImage("assets/images/logo_kakao.png"),
                          ),
                          SizedBox(width: 0.24.sw),
                          Text(
                            "카카오 로그인",
                            style: TextStyle(
                              color: Colors.black,
                            ),
                          ),
                        ],
                      ),
                    ),
                  ),
                ],
              ),
              SizedBox(
                height: 0.1.sh,
              ),
            ],
          ),
        ],
      ),
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
