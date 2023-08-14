import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../models/plan_model.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  final MethodChannel platform = MethodChannel('samples.flutter.dev/battery');

  late List<PlanModel> todaysPlan = [];
  final DateTime today = DateTime.now();

  Future<void> _getTodaysPlan(String yyyymmdd) async {
    print("_getTodaysPlan called");
    String s = "";
    try {
      s = await platform.invokeMethod('getAllDailyPlansByDate', yyyymmdd);
      print("raw value : $s");
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
    setState(() {
      List<PlanModel> todaysPlan =
          (json.decode(s) as List).map((e) => PlanModel.fromJson(e)).toList();
    });
    //return MemberModel.fromJson(jsonDecode(s));
    print("todaysPlan : $todaysPlan");
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    // _getTodaysPlan("20230814");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(0.08.sh),
        child: AppBar(
          backgroundColor: Colors.white.withOpacity(1),
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Container(
                margin: EdgeInsets.only(top: 15.sp),
                child: Image.asset(
                  "assets/images/logo_home.png",
                  scale: 3.5,
                ),
              ),
              Container(
                margin: EdgeInsets.only(right: 10.sp, top: 15.sp),
                child: Icon(
                  Icons.notifications,
                  color: Color(0xFF8A8A8A),
                  size: 30.sp,
                ),
              ),
            ],
          ),
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.vertical(
              bottom: Radius.circular(45),
            ),
          ),
        ),
      ),
      body: Stack(
        children: [
          Positioned(
            top: -10,
            child: Container(
              height: 150,
              width: 300,
              decoration: BoxDecoration(
                color: Color(0xFF17D864),
                borderRadius: BorderRadius.all(
                  Radius.elliptical(200, 50),
                ),
              ),
              child: Text(""),
            ),
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
