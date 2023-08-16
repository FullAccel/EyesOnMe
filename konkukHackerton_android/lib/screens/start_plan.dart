import 'dart:convert';

import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../models/plan_model.dart';

class StartPlan extends StatefulWidget {
  const StartPlan({super.key});

  @override
  State<StartPlan> createState() => _StartPlanState();
}

class _StartPlanState extends State<StartPlan> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  late PlanModel plan;

  Future<void> _getSingleTodoData(String id) async {
    PlanModel ret;
    try {
      String got = await platform.invokeMethod("getSingleTodoData", id);
      Future.delayed(Duration(milliseconds: 2000),(){});
      ret = PlanModel.fromJson(jsonDecode(got));
    } on PlatformException catch (e) {
      throw Exception(e.message);
    }

    setState(() {
      plan = ret;
    });
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    Future.delayed(Duration(milliseconds: 500), () {});
    _getSingleTodoData(Get.arguments["planId"].toString());
  }

  @override
  Widget build(BuildContext context) {
    //final controller = Get.put(PlanController());

    return Scaffold(
      body: Stack(
        alignment: AlignmentDirectional.topCenter,
        children: [
          OvalWidget(),
          Positioned(
            bottom: 0.08.sh,
            child: Column(
              children: [
                SizedBox(
                  height: 0.1.sh,
                ),
                Text(
                  "플랜 시작",
                  style: TextStyle(
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                    fontSize: 22.sp,
                  ),
                ),
                SizedBox(
                  height: 0.05.sh,
                ),
                Text(
                  plan.title,
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 36.sp,
                  ),
                ),
                Text(
                  "시작 시간이예요!",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 22.sp,
                  ),
                ),
                SizedBox(
                  height: 0.02.sh,
                ),
                Text(
                  plan.alarmStartTime,
                  style: TextStyle(
                    color: Colors.white,
                    fontWeight: FontWeight.bold,
                    fontSize: 36.sp,
                  ),
                ),
                SizedBox(
                  height: 0.36.sh,
                ),
                Container(
                  margin: EdgeInsets.only(bottom: 10),
                  child: Row(
                    children: [
                      Column(
                        children: [
                          SizedBox(
                            height: 50,
                          ),
                          IconButton(
                            onPressed: () {
                              Get.toNamed(
                                "/plan/delete1",
                                arguments: plan,
                              );
                            },
                            icon: Icon(
                              Icons.cancel,
                              color: Colors.white,
                              shadows: [
                                BoxShadow(
                                  color: Colors.grey,
                                  blurRadius: 10,
                                ),
                              ],
                            ),
                            iconSize: 80,
                          ),
                          Text("취소하기"),
                        ],
                      ),
                      SizedBox(
                        width: 15,
                      ),
                      ElevatedButton(
                        onPressed: () {},
                        style: ElevatedButton.styleFrom(
                          fixedSize: Size(110, 110),
                          shape: CircleBorder(),
                          backgroundColor: Theme.of(context).primaryColor,
                        ),
                        child: Text(
                          "시작하기",
                          style: TextStyle(
                            fontSize: 20.sp,
                          ),
                        ),
                      ),
                      SizedBox(
                        width: 15,
                      ),
                      Column(
                        children: [
                          SizedBox(
                            height: 50,
                          ),
                          IconButton(
                            onPressed: () {
                              Get.toNamed(
                                "/plan/putoff1",
                                arguments: plan,
                              );
                            },
                            icon: Icon(
                              Icons.arrow_circle_right,
                              color: Colors.white,
                              shadows: [
                                BoxShadow(
                                  color: Colors.grey,
                                  blurRadius: 10,
                                ),
                              ],
                            ),
                            iconSize: 80,
                          ),
                          Text("미루기"),
                        ],
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
