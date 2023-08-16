import 'dart:convert';
import 'dart:math';

import 'package:eom_fe/services/api_service.dart';
import 'package:eom_fe/services/challenge_service.dart';
import 'package:eom_fe/services/setplan_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

import '../../models/challenge_model.dart';
import '../../services/ui_service.dart';

class ChallengeMainScreen extends StatefulWidget {
  const ChallengeMainScreen({super.key});

  @override
  State<ChallengeMainScreen> createState() => _ChallengeMainScreenState();
}

class _ChallengeMainScreenState extends State<ChallengeMainScreen> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  List<Map<String, dynamic>> dummyChallenge = [
    {
      "id": 1,
      "title": "Challenge 1",
      "deadline": "2023-08-31",
      "totalProofNum": 6,
      "currentSuccessNum": 0,
      "achievementRate": 0,
      "challengeStatusCode": "P",
      "validationIntervalCode": "VI07",
      "validationCountPerInterval": 3,
      "categoryCode": "C001",
      "validatorList": ["김세연", "박세준", "서지명"]
    },
    {
      "id": 2,
      "title": "Challenge 2",
      "deadline": "2023-09-15",
      "totalProofNum": 6,
      "currentSuccessNum": 0,
      "achievementRate": 0,
      "challengeStatusCode": "P",
      "validationIntervalCode": "VI01",
      "validationCountPerInterval": 5,
      "categoryCode": "C002",
      "validatorList": []
    },
    // 나머지 ChallengeData 객체들
  ];

  Map<String, dynamic> dummyMakeChallenge = {
    "title": "Challenge 1",
    "deadline": "2023-08-31",
    "validationIntervalCode": "VI07",
    "validationCountPerInterval": 3,
    "categoryCode": "C001",
  };

  List<ChallengeModel> challengeList = [];
  DateTime now = DateTime.now();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _getAllChallenges();
    //_makeChallengeWithValidator(jsonEncode(dummyMakeChallenge));
    //_makeChallengeWithValidator(jsonEncode(dummyChallenge[1]));
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: PreferredSize(
        preferredSize: Size.fromHeight(0.08.sh),
        child: AppBar(
          backgroundColor: Colors.white.withOpacity(1),
          elevation: 1,
          title: Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Container(
                margin: EdgeInsets.only(top: 20.sp),
                child: Container(
                  margin: EdgeInsets.only(left: 0.04.sw),
                  child: Text(
                    "Challenge",
                    style: TextStyle(
                      color: Theme.of(context).primaryColor,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
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
      body: ListView.separated(
        itemBuilder: (context, index) {
          return GestureDetector(
            onTap: () {
              Get.toNamed("/challenge/detail",
                  arguments: challengeList[index].id);
            },
            child: Card(
              clipBehavior: Clip.hardEdge,
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(15),
              ),
              child: Container(
                padding: EdgeInsets.only(
                    left: 0.025.sw, top: 0.01.sh, bottom: 0.02.sh),
                decoration: BoxDecoration(
                  gradient: LinearGradient(
                    colors: [
                      Color(0xFFCCD7FF),
                      Colors.white,
                      Colors.white,
                    ],
                  ),
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  crossAxisAlignment: CrossAxisAlignment.end,
                  children: [
                    Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Container(
                          child: Container(
                            padding: EdgeInsets.symmetric(
                                vertical: 0.01.sh, horizontal: 0.03.sw),
                            decoration: BoxDecoration(
                              borderRadius: BorderRadius.circular(25),
                              color: Colors.white,
                            ),
                            child: Text(
                              "D${getDDay(challengeList[index].deadline, now)}",
                              style: TextStyle(
                                fontSize: 16.sp,
                                fontWeight: FontWeight.w600,
                              ),
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(top: 0.02.sh, left: 0.01.sw),
                          child: Text(
                            SetPlanService.codeToCategory[
                                challengeList[index].categoryCode]!,
                            style: TextStyle(
                              fontSize: 14.sp,
                              color: Theme.of(context).primaryColor,
                              fontWeight: FontWeight.w800,
                            ),
                          ),
                        ),
                        Container(
                          margin: EdgeInsets.only(left: 0.01.sw),
                          child: Text(
                            challengeList[index].title,
                            style: TextStyle(
                              fontSize: 24.sp,
                              fontWeight: FontWeight.w600,
                            ),
                          ),
                        ),
                      ],
                    ),
                    Container(
                      margin: EdgeInsets.only(left: 0.01.sw),
                      padding: EdgeInsets.symmetric(
                          vertical: 0.005.sh, horizontal: 0.03.sw),
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(20),
                        color: Theme.of(context).dialogBackgroundColor,
                      ),
                      child: Text(
                        ChallengeService.getInterval(
                            challengeList[index].validationIntervalCode,
                            challengeList[index].validationCountPerInterval),
                        style: TextStyle(
                          color: Theme.of(context).primaryColor,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                    CustomPaint(
                      painter: ArcPainter(challengeList[index].achievementRate),
                      child: Container(
                        width: 80,
                        height: 80,
                        decoration: BoxDecoration(
                          shape: BoxShape.circle,
                          border: Border.all(color: Color(0xFFF0F0F0)),
                          //color: Colors.red,
                        ),
                        child: Container(
                          margin: EdgeInsets.only(top: 0.025.sh),
                          child: Column(
                            children: [
                              Text(
                                "달성도",
                                style: TextStyle(
                                  color: Theme.of(context).primaryColor,
                                  fontWeight: FontWeight.w600,
                                  fontSize: 11.sp,
                                ),
                              ),
                              SizedBox(
                                height: 0.002.sh,
                              ),
                              Text(
                                "${challengeList[index].achievementRate}%",
                                style: TextStyle(
                                  color: Theme.of(context).primaryColor,
                                  fontWeight: FontWeight.w800,
                                  fontSize: 20.sp,
                                ),
                              ),
                            ],
                          ),
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
        separatorBuilder: (BuildContext context, int index) =>
            SizedBox(height: 0.01.sh),
        itemCount: challengeList.length,
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
      floatingActionButton: FloatingActionButton(
        backgroundColor: Theme.of(context).primaryColor,
        child: Icon(
          Icons.add,
          size: 40,
        ),
        onPressed: () {
          Get.toNamed("/challenge/make");
        },
      ),
    );
  }

  int getDDay(String d1, DateTime d2) {
    Duration duration = d2.difference(DateFormat("yyyy-MM-dd").parse(d1));

    return duration.inDays;
  }

  Future<String> _makeChallengeWithValidator(String jsonString) async {
    print(jsonString);
    String tmp = "";
    try {
      tmp =
          await platform.invokeMethod("makeChallengeWithValidator", jsonString);
      print("tmp $tmp");
      return tmp;
    } on PlatformException catch (e) {
      throw Exception("Exception: _makeChallengeWithValidator");
    }
  }

  Future<List<ChallengeModel>> _getAllChallenges() async {
    print("_getAllChallenges()");
    List<ChallengeModel> ret = [];
    String tmp = "";
    try {
      tmp = await platform.invokeMethod("getAllChallenges");
      print("invoke getAllChallenges: $tmp");
      List<ChallengeModel> list = jsonDecode(tmp);

      for (var ch in list) {
        ret.add(ChallengeModel.fromJson(ch as Map<String, dynamic>));
        print(ret);
      }

      setState(() {
        challengeList = ret;
      });

      return ApiService.sortChallenges(ret);
    } on PlatformException catch (e) {
      throw Exception("Exception: _getAllChallenges");
    }
  }

  Future<void> getChallenge() async {
    List<ChallengeModel> ret = [];
    try {
      ret = await _getAllChallenges();
      print("ret: $ret");
    } catch (e) {
      print("Error: getChallenge()");
    }

    setState(() {
      challengeList = ret;
    });
  }
}

class ArcPainter extends CustomPainter {
  final acheiveRate;
  ArcPainter(this.acheiveRate);
  final gradient = new SweepGradient(
    startAngle: 3 * pi / 2,
    endAngle: 7 * pi / 2,
    tileMode: TileMode.repeated,
    colors: [Colors.white, Color(0xFF5A7FFF), Color(0xFF4D69FF)],
  );

  @override
  void paint(Canvas canvas, Size size) {
    Rect rect = Rect.fromCircle(center: Offset(40, 40), radius: 40);
    double startAngle = 3 * pi / 2; // in radians
    double endAngle = (3 * pi / 2 + (2 * pi * acheiveRate / 100)); // in radians

    final Paint paint = Paint()
      ..shader = gradient.createShader(rect)
      ..color = Color(0xFF5A7FFF)
      ..style = PaintingStyle.stroke
      ..strokeWidth = 8.0;

    canvas.drawArc(rect, startAngle, endAngle - startAngle, false, paint);
  }

  @override
  bool shouldRepaint(CustomPainter oldDelegate) {
    return false;
  }
}
