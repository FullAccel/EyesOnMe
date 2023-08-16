import 'dart:convert';

import 'package:eom_fe/models/challenge_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:weekly_date_picker/weekly_date_picker.dart';

import '../../services/ui_service.dart';

class ChallengeDetail extends StatefulWidget {
  const ChallengeDetail({super.key});

  @override
  State<ChallengeDetail> createState() => _ChallengeDetailState();
}

class _ChallengeDetailState extends State<ChallengeDetail> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  DateTime _selectedDay = DateTime.now();
  late Future<ChallengeModel> ch;
  late int id;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    id = Get.arguments;
    print("id: $id");
    ch = _getSingleChallenge(id);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        child: Column(
          children: [
            WeeklyDatePicker(
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
          ],
        ),
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

  Future<ChallengeModel> _getSingleChallenge(int id) async {
    try {
      String got =
          await platform.invokeMethod('getSingleChallenge', id.toString());
      Future.delayed(const Duration(milliseconds: 700), () {});

      return ChallengeModel.fromJson(jsonDecode(got));
    } on PlatformException catch (e) {
      throw Exception("Exception at invokeMethod: getSingleChallenge");
    }
  }
}
