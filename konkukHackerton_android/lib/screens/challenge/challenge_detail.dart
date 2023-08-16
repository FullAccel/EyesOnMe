import 'dart:convert';

import 'package:eom_fe/models/challenge_model.dart';
import 'package:eom_fe/models/proof_response_data_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';
import 'package:weekly_date_picker/weekly_date_picker.dart';

import '../../services/challenge_service.dart';
import '../../services/ui_service.dart';

class ChallengeDetail extends StatefulWidget {
  const ChallengeDetail({super.key});

  @override
  State<ChallengeDetail> createState() => _ChallengeDetailState();
}

class _ChallengeDetailState extends State<ChallengeDetail> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  DateTime now = DateTime.now();
  DateTime _selectedDay = DateTime.now();
  late ChallengeModel ch;
  late int id;
  List<dynamic> validators = [];
  List<ProofResponseDataModel> prooves = [];

  Future<void> _getAllValidators(String challengeId) async {
    String result =
        await platform.invokeMethod("getAllValidators", challengeId);
    Future.delayed(Duration(milliseconds: 400), () {});

    List<dynamic> data = jsonDecode(result);
    setState(() {
      validators = data;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    id = Get.arguments;
    _getSingleChallenge(id);
    _getAllProof(id);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Container(
            padding: EdgeInsets.only(top: 0.07.sh),
            width: 1.sw,
            decoration: BoxDecoration(
              color: const Color(0xFFE4E9FF),
              gradient: LinearGradient(
                colors: [
                  Color(0xFF406BFF),
                  Color(0xFFE4E9FF),
                ],
                begin: Alignment.topCenter,
                end: Alignment.bottomCenter,
              ),
            ),
            height: 0.3.sh,
            child: Column(
              children: [
                Text(
                  ch.title,
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 26.sp,
                    fontWeight: FontWeight.w800,
                  ),
                ),
                Container(
                  margin: EdgeInsets.symmetric(
                    vertical: 10,
                    horizontal: 10,
                  ),
                  padding: EdgeInsets.symmetric(
                      vertical: 0.01.sh, horizontal: 0.03.sw),
                  decoration: BoxDecoration(
                    borderRadius: BorderRadius.circular(25),
                    color: Color(0xFFDFE4FF),
                  ),
                  child: Text(
                    "${ChallengeService.getInterval(ch.validationIntervalCode, ch.validationCountPerInterval)}",
                    style: TextStyle(
                      color: const Color(0xFF4D69FF),
                      fontSize: 12.sp,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                ),
                Container(
                  margin: EdgeInsets.only(
                      left: 0.08.sw, right: 0.08.sw, top: 0.06.sh),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            "${now.month}월 ${now.day}일",
                            style: TextStyle(
                              color: Colors.white,
                              fontSize: 12.sp,
                            ),
                          ),
                          Row(
                            children: [
                              Text(
                                "챌린지 종료",
                                style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 16.sp,
                                ),
                              ),
                              Text(
                                "까지",
                                style: TextStyle(
                                  color: Colors.white,
                                  fontSize: 12.sp,
                                ),
                              ),
                            ],
                          ),
                          Text(
                            "D${getDDay(ch.deadline, now)}",
                            style: TextStyle(
                              color: Theme.of(context).primaryColor,
                              fontSize: 20.sp,
                              fontWeight: FontWeight.w800,
                            ),
                          )
                        ],
                      ),
                      Row(
                        children: [
                          Container(
                            width: 40,
                            height: 40,
                            child: Icon(
                              Icons.add,
                              color: Theme.of(context).primaryColor,
                              size: 20.sp,
                            ),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              shape: BoxShape.circle,
                            ),
                          ),
                          Container(
                            width: 40,
                            height: 40,
                            child: Icon(
                              Icons.add,
                              color: Theme.of(context).primaryColor,
                              size: 20.sp,
                            ),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              shape: BoxShape.circle,
                            ),
                          ),
                          Container(
                            width: 40,
                            height: 40,
                            child: Icon(
                              Icons.add,
                              color: Theme.of(context).primaryColor,
                              size: 20.sp,
                            ),
                            decoration: BoxDecoration(
                              color: Colors.white,
                              shape: BoxShape.circle,
                            ),
                          ),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          Container(
            clipBehavior: Clip.hardEdge,
            decoration: BoxDecoration(
              borderRadius: BorderRadius.vertical(
                top: Radius.circular(20),
              ),
            ),
            child: WeeklyDatePicker(
              selectedDay: _selectedDay, // DateTime
              changeDay: (value) => setState(() {
                _selectedDay = value;
              }),
              enableWeeknumberText: false,
              backgroundColor: Colors.white,
              weekdayTextColor: Colors.black,
              digitsColor: Colors.black,
              selectedBackgroundColor: Theme.of(context).primaryColor,
              weekdays: const ["MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"],
              daysInWeek: 7,
            ),
          ),
          Center(
            child: prooves.length == 0
                ? Container(
                    margin: EdgeInsets.symmetric(vertical: 0.2.sh),
                    child: Column(
                      children: [
                        Text("인증 내용이 없습니다.\n내용을 추가해주세요."),
                        GestureDetector(
                          onTap: () {},
                          child: Icon(
                            Icons.add_circle,
                            size: 80,
                            color: Theme.of(context).primaryColor,
                          ),
                        ),
                      ],
                    ),
                  )
                : Container(
                    child: ListView.separated(
                        itemBuilder: (context, index) {
                          if (DateTime.parse(prooves[index].date).day ==
                              now.day) {
                            return Card(
                              clipBehavior: Clip.hardEdge,
                              child: Column(
                                children: [
                                  Image.network(
                                      "${prooves[index].images[0]["accessUrl"]}"),
                                  Text(prooves[index].writing),
                                ],
                              ),
                            );
                          }
                        },
                        separatorBuilder: (BuildContext context, int index) =>
                            const Divider(),
                        itemCount: prooves.length),
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
                color: Color(0xFFBCBCBC),
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
                color: UIService.curMenu == 2
                    ? Theme.of(context).primaryColor
                    : Color(0xFFBCBCBC),
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

  Future<void> _getSingleChallenge(int id) async {
    // List<ProofResponseDataModel> ret;
    ChallengeModel ret2;
    try {
      String sc =
          await platform.invokeMethod('getSingleChallenge', id.toString());

      // String ap = await platform.invokeMethod("getAllProof", id.toString());

      Map<String, dynamic> data = jsonDecode(sc);
      // List<Map<String, dynamic>> apList = [];
      // ret = [];

      // for (var a in apList) {
      //   ret.add(ProofResponseDataModel.fromJson(a));
      // }
      ret2 = ChallengeModel.fromJson(data);
    } on PlatformException catch (e) {
      throw Exception("Exception at invokeMethod: getSingleChallenge");
    }

    setState(() {
      // prooves = ret;
      ch = ret2;
    });
  }

  Future<void> _getAllProof(int id) async {
    List<ProofResponseDataModel> ret;
    // ChallengeModel ret2;
    try {
      // String sc =
      //     await platform.invokeMethod('getSingleChallenge', id.toString());

      String ap = await platform.invokeMethod("getAllProof", id.toString());

      // Map<String, dynamic> data = jsonDecode(sc);
      List<Map<String, dynamic>> apList = [];
      ret = [];

      for (var a in apList) {
        ret.add(ProofResponseDataModel.fromJson(a));
      }
      // ret2 = ChallengeModel.fromJson(data);
    } on PlatformException catch (e) {
      throw Exception("Exception at invokeMethod: getSingleChallenge");
    }

    setState(() {
      prooves = ret;
      // ch = ret2;
    });
  }

  // Future<void> _getSingleChallenge(int id) async {
  //   try {
  //     String sc =
  //     await platform.invokeMethod('getSingleChallenge', id.toString());
  //
  //     String ap = await platform.invokeMethod("getAllProof", id.toString());
  //
  //     Map<String, dynamic> data = jsonDecode(sc);
  //     List<Map<String, dynamic>> apList = [];
  //     List<ProofResponseDataModel> ret = [];
  //
  //     for (var a in apList) {
  //       ret.add(ProofResponseDataModel.fromJson(a));
  //     }
  //     ChallengeModel ret2 = ChallengeModel.fromJson(data);
  //
  //     setState(() {
  //       prooves = ret;
  //       ch = ret2;
  //     });
  //   } on PlatformException catch (e) {
  //     throw Exception("Exception at invokeMethod: getSingleChallenge");
  //   }
  // }

  int getDDay(String d1, DateTime d2) {
    Duration duration = d2.difference(DateFormat("yyyy-MM-dd").parse(d1));

    return duration.inDays;
  }
}
