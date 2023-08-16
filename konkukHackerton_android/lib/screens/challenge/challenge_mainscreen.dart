import 'dart:convert';

import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:intl/intl.dart';

import '../../models/challenge_model.dart';

class ChallengeMainScreen extends StatefulWidget {
  const ChallengeMainScreen({super.key});

  @override
  State<ChallengeMainScreen> createState() => _ChallengeMainScreenState();
}

class _ChallengeMainScreenState extends State<ChallengeMainScreen> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  List<ChallengeModel> challengeList = [];
  DateTime now = DateTime.now();

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    getChallenge();
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
                child: Container(
                  margin: EdgeInsets.only(left: 0.04.sw),
                  child: Text(
                    "Challenge",
                    style: TextStyle(
                      color: Color(0xFF3BDE7C),
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
          return Container(
            child: Row(
              children: [
                Column(
                  children: [
                    Container(
                      decoration: BoxDecoration(
                        borderRadius: BorderRadius.circular(10),
                      ),
                      child: Text(
                          "D-${getDDay(challengeList[index].deadline, now)}"),
                    ),
                  ],
                ),
              ],
            ),
          );
        },
        separatorBuilder: (BuildContext context, int index) => const Divider(),
        itemCount: challengeList.length,
      ),
    );
  }

  int getDDay(String d1, DateTime d2) {
    Duration duration = d2.difference(DateFormat("yyyy-MM-dd").parse(d1));

    return duration.inDays;
  }

  Future<List<ChallengeModel>> _getAllChallenges() async {
    print("_getAllChallenges()");
    List<ChallengeModel> ret = [];
    String tmp;
    try {
      tmp = await platform.invokeMethod("getAllChallenges");
      print("invoke getAllChallenges: $tmp");
      List<dynamic> list = jsonDecode(tmp);

      for (var ch in list) {
        ret.add(ChallengeModel.fromJson(ch));
        print(ret);
      }

      return ApiService.sortChallenges(ret);
    } on PlatformException catch (e) {
      throw Exception("Exception: _getAllChallenges");
    }
  }

  Future<void> getChallenge() async {
    List<ChallengeModel> ret = [];
    try {
      ret = await _getAllChallenges();
    } catch (e) {
      print("Error: getChallenge()");
    }

    setState(() {
      challengeList = ret;
    });
  }
}
