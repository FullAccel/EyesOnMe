import 'package:dotted_line/dotted_line.dart';
import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../../models/plan_model.dart';
import '../../widgets/appbar_widget.dart';

class PlanPutoff3 extends StatefulWidget {
  const PlanPutoff3({super.key});

  @override
  State<PlanPutoff3> createState() => _PlanPutoff3State();
}

class _PlanPutoff3State extends State<PlanPutoff3> {
  late Future<List<PlanModel>> planList;
  final curSchedule = 0; // 현재 미루려는 일정

  String DateTimeTo12(String? dt) {
    dt ??= "default";
    //"2023-08-11 23:40:00" -> 11:40 pm
    var tmp = dt.substring(11, 16); // 23:40
    var h = tmp.substring(0, 2);
    if (int.parse(h) == 12) {
      return "12:${tmp.substring(14, 16)} pm";
    } else if (int.parse(h) / 12 < 1) {
      // am
      return "$h:${tmp.substring(3, 5)} am";
    } else {
      return "$h:${tmp.substring(3, 5)} pm";
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    planList = ApiService.getPlans();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppbarWidget(
        title: "플랜 미루기",
      ),
      body: Center(
        child: Column(
          children: [
            Container(
              margin: EdgeInsets.symmetric(vertical: 0.03.sh),
              child: Text(
                "다음 일정은 어떻게 할까요?",
                style: TextStyle(
                  fontSize: 18.sp,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            FutureBuilder(
              future: planList,
              builder: (context, snapshot) {
                if (snapshot.hasData) {
                  return Expanded(
                    child: makeList(snapshot),
                  );
                } else if (snapshot.hasError) {
                  return Text("error");
                } else {
                  return Center(child: const CircularProgressIndicator());
                }
              },
            ),
            ElevatedButton(
              onPressed: () {
                Get.toNamed("/plan/finish");
              },
              child: Text(
                "작성 완료",
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 20.sp,
                ),
              ),
              style: ElevatedButton.styleFrom(
                backgroundColor: Theme.of(context).primaryColor,
                minimumSize: Size(0.83.sw, 0.05.sh),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(30),
                ),
              ),
            ),
            SizedBox(height: 0.03.sh),
          ],
        ),
      ),
    );
  }

  ListView makeList(AsyncSnapshot<List<PlanModel>> snapshot) {
    List<PlanModel> pl = List.of(snapshot.data!);
    pl = ApiService.sortDailyPlans(ApiService.deleteLastPlans(pl));

    return ListView.builder(
      shrinkWrap: true,
      itemCount: snapshot.data!.length,
      itemBuilder: (BuildContext context, int index) {
        //List<PlanModel> pl = List.of(snapshot.data!);
        PlanModel plan = pl[index];

        return index == 0
            ? Container(
                padding: EdgeInsets.symmetric(vertical: 15, horizontal: 25),
                margin: EdgeInsets.symmetric(vertical: 20, horizontal: 10),
                decoration: BoxDecoration(
                  color: Color(0xFF9AAAFF),
                  borderRadius: BorderRadius.circular(10),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey.withOpacity(0.3),
                      spreadRadius: 1,
                      blurRadius: 3,
                      offset: Offset(0, 1),
                    )
                  ],
                ),
                //height: 0.2.sh,
                child: Column(
                  children: [
                    Text(
                      "${DateTimeTo12(plan.alarmStartTime)} ~ ${DateTimeTo12(plan.alarmEndTime)}",
                      style: TextStyle(
                        color: Color(0xFF25A7FFF),
                        fontSize: 32.sp,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    SizedBox(
                      height: 10,
                    ),
                    DottedLine(
                      lineLength: 0.5.sw,
                      dashColor: Colors.blue,
                      dashGapLength: 3,
                      lineThickness: 2,
                    ),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Text(
                          "${plan.categoryCode}\n${plan.title}",
                          style: TextStyle(
                            color: Theme.of(context).primaryColor,
                            fontWeight: FontWeight.w600,
                            fontSize: 16.sp,
                          ),
                        ),
                        SizedBox(
                          width: 60,
                          height: 60,
                          child: ElevatedButton(
                            onPressed: () {},
                            child: Text(
                              "수정",
                              style: TextStyle(fontWeight: FontWeight.w600),
                            ),
                            style: ElevatedButton.styleFrom(
                              shape: CircleBorder(),
                              backgroundColor: Colors.white,
                              foregroundColor: Theme.of(context).primaryColor,
                            ),
                          ),
                        ),
                      ],
                    ),
                  ],
                ),
              )
            : Container(
                // 수정 불가
                padding: EdgeInsets.symmetric(vertical: 15, horizontal: 25),
                margin: EdgeInsets.symmetric(vertical: 20, horizontal: 50),
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(10),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey.withOpacity(0.3),
                      spreadRadius: 1,
                      blurRadius: 3,
                      offset: Offset(0, 1),
                    )
                  ],
                ),
                //height: 0.2.sh,
                child: Row(
                  children: [
                    Text(
                      "${DateTimeTo12(plan.alarmStartTime)}\n~ ${DateTimeTo12(plan.alarmEndTime)}",
                      style: TextStyle(
                        color: Color(0xFFA0A0A0).withOpacity(0.7),
                        fontSize: 16.sp,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    SizedBox(width: 30),
                    Text(
                      "${plan.categoryCode}\n${plan.title}",
                      style: TextStyle(
                        color: Color(0xFF8A8A8A).withOpacity(0.8),
                        fontWeight: FontWeight.w600,
                        fontSize: 16.sp,
                      ),
                    ),
                    SizedBox.shrink(),
                  ],
                ),
              );
      },
    );
  }
}
