import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:linear_timer/linear_timer.dart';

class WakeupMission extends StatefulWidget {
  const WakeupMission({super.key});

  @override
  State<WakeupMission> createState() => _WakeupMissionState();
}

class _WakeupMissionState extends State<WakeupMission>
    with TickerProviderStateMixin {
  final textController = TextEditingController();
  String sentence = "수없이 패배해도 부단히 일어나 달리는 당신의 땀방울은 그 어떤 진주보다도 가치있다.";
  //late LinearTimerController timerController2 = LinearTimerController(this);
  bool isCorrect = false;

  @override
  void dispose() {
    // TODO: implement dispose
    textController.dispose();
    super.dispose();
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //timerController2.start();
  }

  @override
  Widget build(BuildContext context) {
    final maxLines = 4;
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppBar(
        toolbarHeight: 0.08.sh,
        title: Text(
          "기상 미션",
          style: TextStyle(
            fontWeight: FontWeight.w600,
            fontSize: 20.sp,
          ),
        ),
        centerTitle: true,
        elevation: 1,
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
      ),
      backgroundColor: Color(0xFFF5F5F5).withOpacity(0.95),
      body: Center(
        child: Column(
          children: [
            Container(
              padding: EdgeInsets.symmetric(vertical: 0.025.sh),
              child: Text(
                "문장을 따라 쓰세요.",
                style: TextStyle(
                  fontWeight: FontWeight.w600,
                  fontSize: 18.sp,
                ),
              ),
            ),
            Card(
              child: Container(
                padding: EdgeInsets.symmetric(vertical: 20.sp),
                width: 0.8.sw,
                child: Column(
                  children: [
                    SizedBox(
                      width: 0.6.sw,
                      child: Text(
                        sentence,
                        style: TextStyle(
                          //letterSpacing: 1,
                          fontSize: 18.sp,
                          fontWeight: FontWeight.w600,
                          color: Color(0xFFBCBCBC),
                          height: 1.5,
                        ),
                      ),
                    ),
                    SizedBox(height: 0.03.sh),
                    Container(
                      width: 0.7.sw,
                      clipBehavior: Clip.hardEdge,
                      decoration: BoxDecoration(
                          borderRadius: BorderRadius.circular(50)),
                      child: LinearTimer(
                        minHeight: 10.sp,
                        duration: const Duration(seconds: 60),
                        color: Color(0xFF3BDE7C),
                        backgroundColor: Color(0xFFDADADA),
                        //controller: timerController2,
                        forward: false,
                        onTimerEnd: () {
                          //showSnackBar(context, "Timer 2 ended");
                          print("timer ended");
                        },
                      ),
                    ),
                  ],
                ),
              ),
            ),
            SizedBox(height: 0.02.sh),
            Expanded(
              child: Align(
                alignment: Alignment.bottomCenter,
                child: Container(
                  height: 0.55.sh,
                  decoration: BoxDecoration(
                    color: Colors.white,
                    borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(40),
                      topRight: Radius.circular(40),
                    ),
                  ),
                  child: TextField(
                    inputFormatters: [
                      FilteringTextInputFormatter(
                        RegExp('[a-z A-Z ㄱ-ㅎ|가-힣|·|：|.]'),
                        allow: true,
                      )
                    ],
                    controller: textController,
                    onChanged: (text) {
                      print(text);
                      if (text == sentence) {
                        setState(() {
                          isCorrect = true;
                        });
                      }
                    },
                    maxLines: maxLines,
                    keyboardType: TextInputType.multiline,
                    decoration: InputDecoration(
                      filled: false,
                      hintText: '문장을 입력하세요.',
                      contentPadding:
                          EdgeInsets.symmetric(vertical: 40, horizontal: 40),
                      focusedBorder: InputBorder.none,
                      enabledBorder: InputBorder.none,
                    ),
                    style: TextStyle(color: Color(0xFF3BDE7C)),
                  ),
                ),
              ),
            ),
            Container(
              color: Colors.white,
              padding:
                  EdgeInsets.symmetric(vertical: 0.05.sh, horizontal: 0.1.sw),
              width: 1.sw,
              height: 0.15.sh,
              child: ElevatedButton(
                onPressed: isCorrect
                    ? () {
                        Get.offAllNamed("/plan/preview");
                      }
                    : null,
                child: Text(
                  "작성 완료",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 20.sp,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  backgroundColor: Color(0xFF3BDE7C),
                  minimumSize: Size(0.83.sw, 0.03.sh),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30),
                  ),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
