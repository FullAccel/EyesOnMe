import 'dart:convert';

import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:weekly_date_picker/weekly_date_picker.dart';

import '../../models/plan_model.dart';
import '../../widgets/plan_widget.dart';

class ShowPlanScreen extends StatefulWidget {
  const ShowPlanScreen({super.key});

  @override
  State<ShowPlanScreen> createState() => _ShowPlanScreenState();
}

class _ShowPlanScreenState extends State<ShowPlanScreen> {
  static const platform = MethodChannel("samples.flutter.dev/battery");
  late Future<List<PlanModel>> datas;
  DateTime _selectedDay = DateTime.now();
  late final PageController _controller;
  late final DateTime _initialSelectedDay;
  late int _weeknumberInSwipe;
  final int _weekIndexOffset = 5200;
  var todoDatas = [];

  Future<void> _postTodoDataFunc(dynamic jsonData) async {
    String value;
    try {
      return platform.invokeMethod("postTodoDataFunc", jsonData);
    } on PlatformException catch (e) {
      value = "error message : ${e.message}";
    }
  }

  Future<List<PlanModel>> _getAllDailyPlansByDate(String yyyymmdd) async {
    var value;
    try {
      print("start _getAllDailyPlansByDate");
      return await platform.invokeMethod("getAllDailyPlansByDate", yyyymmdd);

      return jsonDecode(value);
    } on PlatformException catch (e) {
      value = "error message : ${e.message}";
      return [];
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _controller = PageController(initialPage: _weekIndexOffset);

    datas = ApiService.getPlans();
    //datas = _getAllDailyPlansByDate("20230813");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0.08.sh,
        title: Text(
          "전체 플랜",
          style: TextStyle(
            fontWeight: FontWeight.w600,
            fontSize: 24.sp,
          ),
        ),
        centerTitle: true,
        elevation: 1,
        backgroundColor: Colors.white,
        foregroundColor: Colors.black,
      ),
      body: FutureBuilder(
        future: datas,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return Stack(
              children: [
                Column(
                  children: [
                    Container(
                      alignment: Alignment.centerLeft,
                      padding: EdgeInsets.symmetric(
                        vertical: 0.01.sh,
                        horizontal: 0.04.sw,
                      ),
                      child: Text(
                        "${_selectedDay.month}월",
                        style: TextStyle(
                            fontSize: 20.sp, fontWeight: FontWeight.bold),
                      ),
                    ),
                    WeeklyDatePicker(
                      selectedDay: _selectedDay, // DateTime
                      changeDay: (value) => setState(() {
                        _selectedDay = value;
                      }),
                      enableWeeknumberText: false,
                      // weeknumberColor: const Color(0xFF57AF87),
                      // weeknumberTextColor: Colors.white,
                      backgroundColor: Colors.white,
                      weekdayTextColor: const Color(0xFF8A8A8A),
                      digitsColor: Color(0xFF8A8A8A),
                      selectedBackgroundColor: const Color(0xFF3BDE7C),
                      weekdays: const [
                        "MON",
                        "TUE",
                        "WED",
                        "THU",
                        "FRI",
                        "SAT",
                        "SUN"
                      ],
                      daysInWeek: 7,
                    ),
                    SizedBox(
                      height: 0.05.sh,
                    ),
                    Expanded(
                      child: makePlanList(snapshot),
                    ),
                  ],
                ),
                Positioned(
                  right: -90,
                  bottom: -90,
                  child: FilledButton(
                    onPressed: () {
                      Get.toNamed("/plan/finish");
                    },
                    clipBehavior: Clip.hardEdge,
                    style: FilledButton.styleFrom(
                      shape: CircleBorder(),
                      fixedSize: Size(220.sp, 220.sp),
                      backgroundColor: Color(0xFF3BDE7C),
                    ),
                    child: Container(
                      margin: EdgeInsets.only(
                        bottom: 0.08.sh,
                        right: 0.15.sw,
                      ),
                      child: Text(
                        "작성 완료",
                        style: TextStyle(
                          fontSize: 20.sp,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                    ),
                  ),
                ),
              ],
            );
          } else if (snapshot.hasError) {
            return Text("error");
          }
          // By default, show a loading spinner.
          return Center(child: const CircularProgressIndicator());
        },
      ),
    );
  }

  Widget makePlanList(AsyncSnapshot<List<PlanModel>> snapshot) {
    List<PlanModel> pl = List.of(snapshot.data!);
    pl = ApiService.sortDailyPlans(pl);

    return ListView.builder(
      itemCount: pl.length,
      itemBuilder: (context, index) {
        //print(index);

        return Column(
          children: [
            index == pl.length - 1
                ? Stack(
                    children: [
                      Positioned.fill(
                        child: Container(
                          margin: EdgeInsets.all(15.sp),
                          decoration: BoxDecoration(
                            color: Colors.white,
                            borderRadius: BorderRadius.circular(50),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey.withOpacity(0.4),
                                blurRadius: 1,
                                spreadRadius: 4,
                                offset: Offset(0, 1),
                              ),
                            ],
                          ),
                        ),
                      ),
                      IconButton(
                        onPressed: () {
                          Get.toNamed("/plan/add");
                        },
                        icon: Icon(Icons.add_circle),
                        color: Color(0xFF3BDE7C),
                        iconSize: 55.sp,
                      ),
                    ],
                  )
                : SizedBox.shrink(),
            PlanWidget(
              id: pl[index].id,
              title: pl[index].title,
              toDoStatusCode: pl[index].toDoStatusCode,
              alarmStartTime: pl[index].alarmStartTime,
              alarmEndTime: pl[index].alarmEndTime,
              categoryCode: pl[index].categoryCode,
              curRoute: Get.currentRoute,
            ),
          ],
        );
      },
      scrollDirection: Axis.vertical,
    );
  }
}
