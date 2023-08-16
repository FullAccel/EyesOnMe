import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

class WakeupAlarm extends StatefulWidget {
  const WakeupAlarm({super.key});

  @override
  State<WakeupAlarm> createState() => _WakeupAlarmState();
}

class _WakeupAlarmState extends State<WakeupAlarm> {
  static const platform = MethodChannel("samples.flutter.dev/battery");
  int planId = -1;

  Future<void> _delayWSAlarm(String yyyymmdd) async {
    try {
      final result = await platform.invokeMethod('delayWSAlarm', yyyymmdd);
      print("delayWSAlarm in flutter : $result");

      // await platform.invokeMethod('testData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    planId = Get.arguments["planId"];
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
                "기상 시간",
                style: TextStyle(
                  fontSize: 28.sp,
                  color: Colors.white,
                  fontWeight: FontWeight.w600,
                ),
              ),
              SizedBox(height: 0.06.sh),
              Text(
                "오늘 하루도",
                style: TextStyle(
                  fontSize: 28.sp,
                  fontWeight: FontWeight.w600,
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(top: 12),
                    child: Text(
                      "힘차게 ",
                      style: TextStyle(
                        fontSize: 24.sp,
                      ),
                    ),
                  ),
                  Text(
                    "시작해 볼까요!",
                    style: TextStyle(
                      fontSize: 42.sp,
                      fontWeight: FontWeight.w800,
                    ),
                  ),
                ],
              ),
              SizedBox(height: 10),
              Text(
                "00:00 am",
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 42.sp,
                  fontWeight: FontWeight.w600,
                ),
              ),
              Container(
                margin: EdgeInsets.only(top: 0.05.sh, bottom: 0.1.sh),
                child: Image.asset(
                  "assets/images/wakeup_logo.png",
                  color: Color(0xFF0C612E),
                  scale: 1,
                ),
              ),
              ElevatedButton(
                onPressed: () => Get.offAllNamed(
                  '/wakeup/mission',
                ),
                child: Text(
                  "기상 미션 시작",
                  style:
                      TextStyle(fontSize: 24.sp, fontWeight: FontWeight.w600),
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
                  _delayWSAlarm(DateFormat("yyyymmdd").format(DateTime.now()));
                },
                child: Text(
                  "10분 뒤 다시 울림",
                  style: TextStyle(
                    fontSize: 16.sp,
                    color: Colors.grey,
                    fontWeight: FontWeight.w600,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  shape: new RoundedRectangleBorder(
                    borderRadius: new BorderRadius.circular(90),
                  ),
                  backgroundColor: Colors.white,
                  padding: EdgeInsets.symmetric(vertical: 12, horizontal: 56),
                ),
              ),
            ],
          ),
        ],
      ),
    );
  }
}
