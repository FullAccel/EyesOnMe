import 'dart:convert';

import 'package:eom_fe/models/member_model.dart';
import 'package:eom_fe/services/challenge_service.dart';
import 'package:eom_fe/services/datetime_service.dart';
import 'package:eom_fe/services/ui_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:percent_indicator/linear_percent_indicator.dart';

import '../models/challenge_model.dart';
import '../models/plan_model.dart';
import '../services/api_service.dart';
import '../services/setplan_service.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  List<PlanModel> todaysPlan = [];
  List<ChallengeModel> userChallenges = [];
  final DateTime today = DateTime.now();
  late MemberModel user;

  Future<void> _getAllChallenges() async {
    List<ChallengeModel> ret = [];

    try {
      String s = await platform.invokeMethod('getAllChallenges');
      List<dynamic> tmp = jsonDecode(s);

      for (var ch in tmp) {
        ret.add(ChallengeModel.fromJson(ch));
      }
    } on PlatformException catch (e) {
      throw Exception("Failed to get challenges");
    }

    setState(() {
      userChallenges = ret;
    });
  }

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

  Future<void> _getMemberData() async {
    String s = "";
    MemberModel ret;
    try {
      s = await platform.invokeMethod('getMemberData');
      ret = MemberModel.fromJson(jsonDecode(s));
    } on PlatformException catch (e) {
      throw Exception("Failed to get todays plan");
    }

    setState(() {
      user = ret;
    });
  }

  ScrollController scrollController = ScrollController(
    initialScrollOffset: 2, // or whatever offset you wish
    keepScrollOffset: true,
  );

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //Future.delayed(Duration(milliseconds: 500), () {});
    getPlan();
    _getAllChallenges();
    print(userChallenges);
    _getMemberData();
    print(today.weekday);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Color(0xFFE4E9FF),
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(0.08.sh),
        child: AppBar(
          backgroundColor: Colors.white.withOpacity(0.8),
          elevation: 0,
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Container(
                margin: EdgeInsets.only(top: 15.sp),
                child: Image.asset(
                  "assets/images/logo_home.png",
                  scale: 3.5,
                  color: Theme.of(context).primaryColor,
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
      extendBodyBehindAppBar: true,
      body: Stack(
        alignment: Alignment.center,
        children: [
          Positioned(
            top: 30,
            left: 0,
            child: Container(
              height: 0.7.sh,
              width: 1800,
              decoration: BoxDecoration(
                color: Theme.of(context).primaryColor,
                borderRadius: BorderRadius.all(
                  Radius.elliptical(1000, 600),
                ),
              ),
            ),
          ),
          Positioned(
            right: 0.1.sw,
            top: 0.12.sh,
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
                    "${today.month}월 ${today.day}일 ${DateTimeService.weekdayKO[today.weekday]}",
                    style: TextStyle(
                      fontSize: 24.sp,
                      color: Colors.white.withOpacity(0.8),
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                ],
              ),
            ),
          ),
          Positioned(
            bottom: 0.32.sh,
            left: 0.05.sw,
            child: Column(
              children: [
                Container(
                  width: 0.9.sw,
                  height: 0.35.sh,
                  child: ListView.builder(
                    controller: scrollController,
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
                                      scale: 1.2,
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
                                      color: Theme.of(context).primaryColor,
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
                                      color: Theme.of(context).primaryColor,
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
                                      scale: 1.2,
                                    ),
                                    style: ElevatedButton.styleFrom(
                                      backgroundColor:
                                          Theme.of(context).primaryColor,
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
          Positioned(
            bottom: 0,
            child: Container(
              height: 0.3.sh,
              width: 1.sw,
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topRight: Radius.circular(30),
                ),
              ),
              child: Container(
                margin: EdgeInsets.only(left: 0.03.sw, top: 0.03.sh),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                      "${user.name}님",
                      style: TextStyle(
                        fontSize: 18.sp,
                        color: Theme.of(context).primaryColor,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    Row(
                      crossAxisAlignment: CrossAxisAlignment.end,
                      children: [
                        Text(
                          "지금 진행 중인",
                          style: TextStyle(
                            fontWeight: FontWeight.w600,
                            fontSize: 16.sp,
                          ),
                        ),
                        Text(
                          " 챌린지",
                          style: TextStyle(
                            fontSize: 25.sp,
                            color: Theme.of(context).primaryColor,
                            fontWeight: FontWeight.w800,
                          ),
                        ),
                        Text(
                          "입니다!",
                          style: TextStyle(
                            fontWeight: FontWeight.w600,
                            fontSize: 16.sp,
                          ),
                        ),
                      ],
                    ),
                    Container(
                      margin: EdgeInsets.only(top: 15),
                      width: 0.95.sw,
                      height: 0.18.sh,
                      child: ListView.separated(
                          scrollDirection: Axis.horizontal,
                          itemBuilder: (context, index) {
                            return Card(
                              clipBehavior: Clip.hardEdge,
                              elevation: 3,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(15),
                              ),
                              child: Container(
                                width: 150,
                                decoration: BoxDecoration(
                                  gradient: LinearGradient(
                                    colors: [
                                      Colors.white,
                                      Color(0xFFF0F3FF),
                                      Color(0xFFCBD3FF),
                                    ],
                                    begin: Alignment.bottomLeft,
                                    end: Alignment.topRight,
                                  ),
                                ),
                                child: Column(
                                  crossAxisAlignment: CrossAxisAlignment.start,
                                  children: [
                                    Container(
                                      margin: EdgeInsets.symmetric(
                                        vertical: 10,
                                        horizontal: 10,
                                      ),
                                      padding: EdgeInsets.symmetric(
                                          vertical: 0.01.sh,
                                          horizontal: 0.03.sw),
                                      decoration: BoxDecoration(
                                        borderRadius: BorderRadius.circular(25),
                                        color: Colors.white,
                                      ),
                                      child: Text(
                                        "D${getDDay(userChallenges[index].deadline, today)}",
                                        style: TextStyle(
                                          fontSize: 14.sp,
                                          fontWeight: FontWeight.w600,
                                        ),
                                      ),
                                    ),
                                    Container(
                                      margin: EdgeInsets.only(
                                        top: 8,
                                        left: 10,
                                      ),
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Text(
                                            "${ChallengeService.getInterval(userChallenges[index].validationIntervalCode, userChallenges[index].validationCountPerInterval)}",
                                            style: TextStyle(
                                              fontWeight: FontWeight.w600,
                                            ),
                                          ),
                                          Text(
                                            "${userChallenges[index].title}",
                                            style: TextStyle(
                                              fontWeight: FontWeight.w600,
                                            ),
                                          ),
                                        ],
                                      ),
                                    ),
                                    SizedBox(height: 0.035.sh),
                                    LinearPercentIndicator(
                                      width: 150,
                                      animation: true,
                                      animationDuration: 100,
                                      lineHeight: 7,
                                      percent: 0.2,
                                      progressColor: Colors.black,
                                      barRadius: Radius.circular(10),
                                    ),
                                  ],
                                ),
                              ),
                            );
                          },
                          separatorBuilder: (BuildContext context, int index) =>
                              const Divider(),
                          itemCount: userChallenges.length),
                    )
                  ],
                ),
              ),
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
                    ? Theme.of(context).primaryColor
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

  int getDDay(String d1, DateTime d2) {
    Duration duration = d2.difference(DateFormat("yyyy-MM-dd").parse(d1));

    return duration.inDays;
  }
}
