import 'dart:convert';

import 'package:eom_fe/screens/challenge/challenge_detail.dart';
import 'package:eom_fe/screens/challenge/challenge_mainscreen.dart';
import 'package:eom_fe/screens/challenge/make_challenge.dart';
import 'package:eom_fe/screens/entire_plan.dart';
import 'package:eom_fe/screens/home_screen.dart';
import 'package:eom_fe/screens/intro_screen1.dart';
import 'package:eom_fe/screens/intro_screen2.dart';
import 'package:eom_fe/screens/login_screen.dart';
import 'package:eom_fe/screens/plan_add_screen.dart';
import 'package:eom_fe/screens/plan_delete/plan_delete1.dart';
import 'package:eom_fe/screens/plan_delete/plan_delete2.dart';
import 'package:eom_fe/screens/plan_finish/plan_finish1.dart';
import 'package:eom_fe/screens/plan_finish/plan_finish2.dart';
import 'package:eom_fe/screens/plan_progressing_screen.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff1.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff2.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff3.dart';
import 'package:eom_fe/screens/planning_finish.dart';
import 'package:eom_fe/screens/planning_sleeptime.dart';
import 'package:eom_fe/screens/planning_wakeuptime.dart';
import 'package:eom_fe/screens/show_plan_screen.dart';
import 'package:eom_fe/screens/sleep_time_screen.dart';
import 'package:eom_fe/screens/start_plan.dart';
import 'package:eom_fe/screens/user_profile_screen.dart';
import 'package:eom_fe/screens/wakeup/wakeup_alarm.dart';
import 'package:eom_fe/screens/wakeup/wakeup_mission.dart';
import 'package:eom_fe/screens/wakeup/wakeup_preview_plan.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:permission_handler/permission_handler.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  // GlobalKey를 생성하십시오.
  static final navigatorKey = GlobalKey<NavigatorState>();
  static const platform = MethodChannel('samples.flutter.dev/battery');

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  void initState() {
    super.initState();

    setExtraScreenHandler();
    permission();
    //addPlan();
  }

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: Size(MediaQuery.of(context).size.width,
          MediaQuery.of(context).size.height),
      minTextAdapt: true,
      splitScreenMode: true,
      builder: (context, child) {
        return GetMaterialApp(
          theme: ThemeData(
            primaryColor: Color(0xFF5A7FFF),
            dialogBackgroundColor: Color(0xFFDFE4FF),
            shadowColor: Color(0xFFBCBCBC),
            fontFamily: 'Nanum_SQUARE',
          ),
          //home: LoginScreen(),
          initialRoute: '/login',
          getPages: [
            GetPage(name: '/', page: () => HomeScreen()),
            GetPage(name: '/intro1', page: () => IntroScreen1()),
            GetPage(name: '/intro2', page: () => IntroScreen2()),
            GetPage(
              name: '/login',
              page: () => LoginScreen(),
            ),
            GetPage(
              name: '/profile',
              page: () => UserProfileScreen(),
            ),
            GetPage(name: '/plan/tomorrow', page: () => SleepTimeScreen()),
            GetPage(
              name: '/plan/finish',
              page: () => PlanningFinish(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: '/plan/sleep',
              page: () => PlanningSleepTime(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: '/plan/wakeup',
              page: () => PlanningWakeupTime(),
              transition: Transition.rightToLeft,
            ),
            GetPage(name: '/plan', page: () => EntirePlan()),
            GetPage(name: "/plan/start", page: () => StartPlan()),
            GetPage(name: "/plan/putoff1", page: () => PlanPutoff1()),
            GetPage(
              name: "/plan/putoff2",
              page: () => PlanPutoff2(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/plan/putoff3",
              page: () => PlanPutoff3(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/plan/delete1",
              page: () => PlanDelete1(),
            ),
            GetPage(
              name: "/plan/delete2",
              page: () => PlanDelete2(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/plan/show",
              page: () => ShowPlanScreen(),
              transition: Transition.rightToLeftWithFade,
            ),
            GetPage(
              name: "/plan/add",
              page: () => PlanAddScreen(),
              transition: Transition.rightToLeftWithFade,
            ),
            GetPage(
              name: "/plan/progressing",
              page: () => PlanProgressingScreen(),
              transition: Transition.rightToLeftWithFade,
            ),
            GetPage(
              name: "/plan/finish1",
              page: () => PlanFinish1(),
            ),
            GetPage(
              name: "/plan/finish2",
              page: () => PlanFinish2(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/wakeup",
              page: () => WakeupAlarm(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/wakeup/mission",
              page: () => WakeupMission(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/wakeup/preview",
              page: () => WakeupPreviewPlan(),
              transition: Transition.rightToLeft,
            ),
            GetPage(
              name: "/challenge",
              page: () => ChallengeMainScreen(),
            ),
            GetPage(
              name: "/challenge/make",
              page: () => MakeChallenge(),
            ),
            GetPage(
              name: "/challenge/detail",
              page: () => ChallengeDetail(),
            ),
          ],
        );
      },
    );
  }

  // 코틀린 -> 플러터 화면 띄우려고 핸들러 설정
  void setExtraScreenHandler() {
    MyApp.platform.setMethodCallHandler((call) async {
      print("setExtraScreenHandler");
      if (call.method == 'showExtraScreen') {
        print("showExtraScreen");
        _showExtraScreen();
      }
    });
  }

  // Future<String> _kakaologin() async {
  Future<void> _getBatteryLevel2() async {
    try {
      await MyApp.platform.invokeMethod('showAlarmList');
    } on PlatformException catch (e) {
      // batteryLevel = "Failed to get battery level: '${e.message}'.";
    }
  }

  Future<void> _showExtraScreen() async {
    try {
      Navigator.pushNamed(context, '/extraScreen'); // Navigate to ExtraScreen
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  Future<void> _testData() async {
    try {
      final result =
          await MyApp.platform.invokeMethod('testData', ["Raon", "29"]);
      print("flutter : $result");

      // await platform.invokeMethod('testData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  Future<bool> permission() async {
    Map<Permission, PermissionStatus> status =
        await [Permission.systemAlertWindow].request(); // [] 권한배열에 권한을 작성

    if (await Permission.systemAlertWindow.isGranted) {
      return Future.value(true);
    } else {
      return Future.value(false);
    }
  }

  Map<String, dynamic> alarmData = {
    "startTime": "2023-08-15 15:24:00",
    "alarmType": 0,
    "alarmRepeat": 10
  };

  Future<void> _setWakeAlarm(Map<String, dynamic> json) async {
    try {
      final result = await MyApp.platform.invokeMethod(
        'setWakeAlarm',
        jsonEncode(json),
      );
      print("alarm: $result");

      // await platform.invokeMethod('testData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }
}

/*
Future<void> _callFlutterFunction(String method, Map<String, dynamic> arguments) async {
  try {
    if (method == 'show_extra_screen') {
      final result = await MyApp.navigatorKey.currentState?.push(MaterialPageRoute(builder: (context) => const ExtraScreen()));
      print('Kotlin에서 전달된 데이터: $result');
    }
  } on PlatformException catch (e) {
    print('Error: ${e.message}');
  }
}
*/
