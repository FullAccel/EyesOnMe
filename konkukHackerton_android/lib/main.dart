import 'package:eom_fe/screens/entire_plan.dart';
import 'package:eom_fe/screens/home_screen.dart';
import 'package:eom_fe/screens/intro_screen1.dart';
import 'package:eom_fe/screens/login_screen.dart';
import 'package:eom_fe/screens/plan_delete/plan_delete1.dart';
import 'package:eom_fe/screens/plan_delete/plan_delete2.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff1.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff2.dart';
import 'package:eom_fe/screens/plan_putoff/plan_putoff3.dart';
import 'package:eom_fe/screens/planning_finish.dart';
import 'package:eom_fe/screens/planning_sleeptime.dart';
import 'package:eom_fe/screens/planning_wakeuptime.dart';
import 'package:eom_fe/screens/sleep_time_screen.dart';
import 'package:eom_fe/screens/start_plan.dart';
import 'package:eom_fe/screens/user_profile_screen.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:get/get_navigation/src/routes/get_route.dart';
import 'package:get/get_navigation/src/routes/transitions_type.dart';
import 'package:permission_handler/permission_handler.dart';

import 'extra_screen.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // GlobalKey를 생성하십시오.
  static final navigatorKey = GlobalKey<NavigatorState>();

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      navigatorKey: navigatorKey, // navigatorKey를 MaterialApp에 추가
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
      routes: {
        '/extraScreen': (context) =>
            const ExtraScreen(), // Define route for ExtraScreen
      },
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  // Get battery level.
  String _batteryLevel = 'Unknown battery level.';

  @override
  void initState() {
    super.initState();

    setExtraScreenHandler();
  }

  // 코틀린 -> 플러터 화면 띄우려고 핸들러 설정
  void setExtraScreenHandler() {
    platform.setMethodCallHandler((call) async {
      print("setExtraScreenHandler");
      if (call.method == 'showExtraScreen') {
        print("showExtraScreen");
        _showExtraScreen();
      }
    });
  }

  Future<void> _kakaologin() async {
    try {
      await platform.invokeMethod('kakaoLogin');
    } on PlatformException catch (e) {
      // batteryLevel = "Failed to get battery level: '${e.message}'.";
    }
  }

  Future<void> _getBatteryLevel2() async {
    try {
      await platform.invokeMethod('showAlarmList');
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

  Future<bool> permission() async {
    Map<Permission, PermissionStatus> status =
        await [Permission.systemAlertWindow].request(); // [] 권한배열에 권한을 작성

    if (await Permission.systemAlertWindow.isGranted) {
      return Future.value(true);
    } else {
      return Future.value(false);
    }
  }

  @override
  Widget build(BuildContext context) {
    return ScreenUtilInit(
      designSize: Size(MediaQuery.of(context).size.width,
          MediaQuery.of(context).size.height),
      minTextAdapt: true,
      splitScreenMode: true,
      builder: (context, child) {
        return GetMaterialApp(
          debugShowCheckedModeBanner: false,
          // You can use the library anywhere in the app even in theme
          initialRoute: '/plan/start',
          getPages: [
            GetPage(name: '/', page: () => HomeScreen()),
            GetPage(name: '/intro1', page: () => IntroScreen1()),
            GetPage(name: '/intro2', page: () => HomeScreen()),
            GetPage(
              name: '/login',
              page: () => LoginScreen(
                  onPressedKakao: _kakaologin, onPressedGoogle: () {}),
            ),
            GetPage(name: '/profile', page: () => UserProfileScreen()),
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
          ],
        );
      },
      // home: LoginScreen(
      //   onPressedKakao: _kakaoLogin,
      // ),
      // home: Center(
      //   child: Column(
      //     mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      //     children: [
      //       ElevatedButton(
      //         onPressed: _kakaoLogin,
      //         child: const Text('kakao login'),
      //       ),
      //     ],
      //   ),
      // ),
    );
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
