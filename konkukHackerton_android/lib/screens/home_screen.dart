import 'dart:convert';

import 'package:eom_fe/services/ui_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

import '../models/plan_model.dart';
import '../services/api_service.dart';
import '../services/datetime_service.dart';
import '../services/setplan_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  List<PlanModel> todaysPlan = [];
  final DateTime today = DateTime.now();

  Future<List<PlanModel>> _getTodaysPlan(String yyyymmdd) async {
    print("_getTodaysPlan called");
    String s = "";
    List<PlanModel> ret = [];
    try {
      s = await platform.invokeMethod('getAllDailyPlansByDate', yyyymmdd);
      print("raw value : $s");
      ret = (jsonDecode(s) as List).map((e) => PlanModel.fromJson(e)).toList();
      ret = ApiService.sortDailyPlans(ret);
      return ret;
    } on PlatformException catch (e) {
      throw Exception("Failed to get todays plan");
    }
  }

  Future<void> getPlan() async {
    String day = DateFormat("yyyyMMdd").format(today);
    List<PlanModel> ret = [];
    try {
      ret = await _getTodaysPlan(day);
    } catch (e) {
      print("Error: getPlan()");
    }

    setState(() {
      todaysPlan = ret;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getPlan();
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
        alignment: Alignment.center,
        children: [
          Positioned(
            top: -20,
            left: 0,
            child: Container(
              height: 150,
              width: 300,
              decoration: BoxDecoration(
                color: Color(0xFF17D864),
                borderRadius: BorderRadius.all(
                  Radius.elliptical(200, 100),
                ),
              ),
              child: Container(
                margin: EdgeInsets.only(
                  left: 0.1.sw,
                  top: 0.05.sh,
                ),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "${today.year}년",
                      style: TextStyle(
                        fontSize: 18.sp,
                        color: Colors.white.withOpacity(0.7),
                        fontWeight: FontWeight.w500,
                      ),
                    ),
                    Text(
                      "${today.month}월  ${today.day}일 ${DateTimeService.weekdayKO[today.weekday]}",
                      style: TextStyle(
                        fontSize: 24.sp,
                        color: Colors.white,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
          Positioned(
            top: 100,
            child: Column(
              children: [
                Container(
                  width: 0.9.sw,
                  height: 0.4.sh,
                  child: ListView.builder(
                    itemCount: todaysPlan.length,
                    itemBuilder: (context, index) {
                      //print(todaysPlan[index].alarmEndTime);
                      return Card(
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(15)),
                        clipBehavior: Clip.hardEdge,
                        child: Container(
                          padding: EdgeInsets.symmetric(vertical: 0.01.sh),
                          width: 0.9.sw,
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.spaceBetween,
                            children: [
                              Transform.scale(
                                scale: 1.2,
                                child: Transform.translate(
                                  offset: Offset(-10, 0),
                                  child: ElevatedButton(
                                    onPressed: () {},
                                    child: Image.asset(
                                      "assets/images/icon_x.png",
                                      scale: 0.7,
                                    ),
                                    style: ElevatedButton.styleFrom(
                                      backgroundColor: Color(0xFFF0F0F0),
                                      shape: CircleBorder(),
                                      padding: EdgeInsets.all(16.0),
                                    ),
                                  ),
                                ),
                              ),
                              Column(
                                children: [
                                  Text(
                                    SetPlanService.codeToCategory[
                                        todaysPlan[index].categoryCode]!,
                                    style: TextStyle(
                                      fontSize: 18.sp,
                                      fontWeight: FontWeight.w600,
                                      color: Color(0xFF20884A),
                                    ),
                                  ),
                                  Text(
                                    todaysPlan[index].title,
                                    style: TextStyle(
                                      fontSize: 28.sp,
                                      fontWeight: FontWeight.w600,
                                    ),
                                  ),
                                  Text(
                                    "${ApiService.DateTimeTo12(todaysPlan[index].alarmStartTime)}~${ApiService.DateTimeTo12(todaysPlan[index].alarmEndTime)}",
                                    style: TextStyle(
                                      fontSize: 16.sp,
                                      fontWeight: FontWeight.w600,
                                      color: Color(0xFF3BDE7C),
                                    ),
                                  ),
                                ],
                              ),
                              Transform.scale(
                                scale: 1.2,
                                child: Transform.translate(
                                  offset: Offset(10, 0),
                                  child: ElevatedButton(
                                    onPressed: () {},
                                    child: Image.asset(
                                      "assets/images/icon_check.png",
                                      scale: 0.7,
                                    ),
                                    style: ElevatedButton.styleFrom(
                                      backgroundColor: Color(0xFF3BDE7C),
                                      shape: CircleBorder(),
                                      padding: EdgeInsets.all(16.0),
                                    ),
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      );
                    },
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
      bottomNavigationBar: BottomNavigationBar(
        onTap: (int index) {
          switch (index) {
            case 0:
              UIService.curMenu = index;
              Get.offAllNamed("/");
              break;
            case 1:
              UIService.curMenu = index;
              Get.offAllNamed("/plan");
              break;
            case 2:
              UIService.curMenu = index;
              Get.offAllNamed("/challenge");
              break;
            case 3:
              UIService.curMenu = index;
              Get.offAllNamed("/profile");
              break;
          }
        },
        items: [
          BottomNavigationBarItem(
            icon: Container(
              margin: EdgeInsets.only(top: 15.sp, right: 12.sp),
              child: ImageIcon(
                AssetImage("assets/images/menu1.png"),
                color: UIService.curMenu == 0
                    ? Color(0xFF3BDE7C)
                    : Color(0xFFBCBCBC),
                size: 32,
              ),
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Container(
              margin: EdgeInsets.only(right: 48.sp),
              child: ImageIcon(
                AssetImage("assets/images/menu2.png"),
                color: Color(0xFFBCBCBC),
                size: 32,
              ),
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Container(
              margin: EdgeInsets.only(right: 28.sp),
              child: ImageIcon(
                AssetImage("assets/images/menu3.png"),
                color: Color(0xFFBCBCBC),
                size: 32,
              ),
            ),
            label: "",
          ),
          BottomNavigationBarItem(
            icon: Container(
              margin: EdgeInsets.only(right: 18),
              child: ImageIcon(
                AssetImage("assets/images/menu4.png"),
                color: Color(0xFFBCBCBC),
                size: 32,
              ),
            ),
            label: "",
          ),
        ],
      ),
    );
  }
}
