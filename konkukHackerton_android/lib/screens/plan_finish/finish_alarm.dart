import 'package:eom_fe/services/api_service.dart';
import 'package:eom_fe/widgets/oval_widget.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../../models/plan_model.dart';

class FinishAlarm extends StatefulWidget {
  const FinishAlarm({super.key});

  @override
  State<FinishAlarm> createState() => _FinishAlarmState();
}

class _FinishAlarmState extends State<FinishAlarm> {
  late Future<List<PlanModel>> planList;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    planList = ApiService.getPlans();
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
                  "플랜 종료",
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
                  "운동",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 36.sp,
                  ),
                ),
                Text(
                  "잘 진행되고 있나요?",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 22.sp,
                  ),
                ),
                SizedBox(
                  height: 0.02.sh,
                ),
                Text(
                  "10:00 pm",
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
                                arguments: planList,
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
                          backgroundColor: Color(0xFF3BDE7C),
                        ),
                        child: Text(
                          "진행 중",
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
                                "/plan/finish1",
                                arguments: planList,
                              );
                            },
                            icon: Icon(
                              Icons.check_circle,
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
                          Text("완료하기"),
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
