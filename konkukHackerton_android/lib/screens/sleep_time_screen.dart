import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

class SleepTimeScreen extends StatefulWidget {
  const SleepTimeScreen({super.key});

  @override
  State<SleepTimeScreen> createState() => _SleepTimeScreenState();
}

class _SleepTimeScreenState extends State<SleepTimeScreen> {
  DateTime now = DateTime.now();
  static const platform = MethodChannel('samples.flutter.dev/battery');

  Future<void> _delayWSAlarm(String yyyyMMdd) async {
    try {
      await platform.invokeMethod("delayWSAlarm", yyyyMMdd);
    } on PlatformException catch (e) {
      throw Exception("delayWSAlarm failed");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        alignment: Alignment.center,
        children: [
          OvalWidget(),
          Column(
            children: [
              SizedBox(height: 0.12.sh),
              Text(
                "취침 시간",
                style: TextStyle(
                    fontSize: 28.sp,
                    color: Colors.white,
                    fontWeight: FontWeight.w600),
              ),
              SizedBox(height: 0.06.sh),
              Text(
                "하루를 정리하고",
                style: TextStyle(
                  fontSize: 28.sp,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Text(
                    "내일을 계획",
                    style: TextStyle(
                      fontSize: 42.sp,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  Padding(
                    padding: const EdgeInsets.only(top: 20),
                    child: Text(
                      "해보아요!",
                      style: TextStyle(
                        fontSize: 24.sp,
                      ),
                    ),
                  ),
                ],
              ),
              SizedBox(height: 10),
              Text(
                "00:00 am",
                style: TextStyle(
                  color: Color(0xFF20884A),
                  fontSize: 42.sp,
                  fontWeight: FontWeight.w600,
                ),
              ),
              Image.asset(
                "assets/images/logo_note.png",
                color: Colors.white,
                scale: 0.8,
              ),
              ElevatedButton(
                onPressed: () => Get.toNamed(
                  '/plan/wakeup',
                ),
                child: Text(
                  "내일 플랜 작성하기",
                  style: TextStyle(fontSize: 24.sp),
                ),
                style: ElevatedButton.styleFrom(
                  shape: new RoundedRectangleBorder(
                    borderRadius: new BorderRadius.circular(90),
                  ),
                  backgroundColor: Color(0xFF3BDE7C),
                  padding: EdgeInsets.symmetric(vertical: 12, horizontal: 84),
                ),
              ),
              SizedBox(height: 12),
              ElevatedButton(
                onPressed: () {
                  _delayWSAlarm(DateTime(
                          now.year, now.month, now.day, now.hour, now.minute, 0)
                      .toString());
                  Get.offAllNamed("/");
                },
                child: Text(
                  "10분 뒤 다시 울림",
                  style: TextStyle(
                    fontSize: 18.sp,
                    color: Colors.grey,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  shape: new RoundedRectangleBorder(
                    borderRadius: new BorderRadius.circular(90),
                  ),
                  backgroundColor: Colors.white,
                  padding: EdgeInsets.symmetric(vertical: 6, horizontal: 42),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
