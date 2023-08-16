import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../../models/plan_model.dart';
import '../../widgets/appbar_widget.dart';

class PlanPutoff2 extends StatefulWidget {
  const PlanPutoff2({super.key});

  @override
  State<PlanPutoff2> createState() => _PlanPutoff2State();
}

class _PlanPutoff2State extends State<PlanPutoff2> {
  final String hour = "0";
  final String minute = "0";
  List<String> putoffHour = [
    "00",
    "01",
    "02",
    "03",
    "04",
    "05",
    "06",
    "07",
    "08",
    "09",
    "10",
  ];
  List<String> putoffMinute = [
    "00",
    "05",
    "10",
    "15",
    "20",
    "25",
    "30",
    "35",
    "40",
    "45",
    "50",
    "55",
  ];

  late Future<List<PlanModel>> planList;
  int tmpHourIndex = 0;
  int tmpMinuteIndex = 0;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    planList = Get.arguments;
  }

  @override
  Widget build(BuildContext context) {
    //Future<List<PlanModel>> planList = Get.arguments;
    String selectedH = "00";
    String selectedM = "00";

    //print(Get.arguments.length);
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
                "언제 다시 알림을 울릴까요?",
                style: TextStyle(
                  fontSize: 18.sp,
                  fontWeight: FontWeight.bold,
                ),
              ),
            ),
            GestureDetector(
              onTap: () {
                showModalBottomSheet(
                  context: context,
                  builder: (BuildContext context) {
                    return Container(
                      height: 0.4.sh,
                      color: Colors.white,
                      child: Row(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: <Widget>[
                          CupertinoButton(
                            child: Icon(
                              Icons.arrow_back_outlined,
                              size: 30,
                              color: Colors.black,
                            ),
                            onPressed: () => Get.back(),
                          ),
                          Expanded(
                            child: CupertinoPicker(
                              scrollController: new FixedExtentScrollController(
                                initialItem: 0,
                              ),
                              useMagnifier: true,
                              magnification: 1.22,
                              itemExtent: 32.0,
                              squeeze: 1,
                              backgroundColor: Colors.white,
                              onSelectedItemChanged: (int index) {
                                setState(() {
                                  tmpHourIndex = index;
                                });
                              },
                              children: List<Widget>.generate(
                                putoffHour.length,
                                (int index) {
                                  return Center(
                                    child: Text(
                                      putoffHour[index],
                                      style: TextStyle(
                                        fontSize: 24.sp,
                                      ),
                                    ),
                                  );
                                },
                              ),
                            ),
                          ),
                          Expanded(
                            child: CupertinoPicker(
                              scrollController: new FixedExtentScrollController(
                                initialItem: 0,
                              ),
                              useMagnifier: true,
                              magnification: 1.22,
                              itemExtent: 32.0,
                              squeeze: 1,
                              backgroundColor: Colors.white,
                              onSelectedItemChanged: (int index) {
                                setState(() {
                                  tmpMinuteIndex = index;
                                });
                              },
                              children: new List<Widget>.generate(
                                putoffMinute.length,
                                (int index) {
                                  return Center(
                                    child: Text(
                                      putoffMinute[index],
                                      style: TextStyle(
                                        fontSize: 24.sp,
                                      ),
                                    ),
                                  );
                                },
                              ),
                            ),
                          ),
                          CupertinoButton(
                            child: Text("확인"),
                            onPressed: () {
                              setState(() {
                                selectedH = putoffHour[tmpHourIndex];
                                selectedM = putoffMinute[tmpMinuteIndex];
                              });
                              Get.back();
                            },
                          ),
                        ],
                      ),
                    );
                  },
                  // _showDialog(
                  //   CupertinoPicker(
                  //     magnification: 1.5,
                  //     squeeze: 1,
                  //     useMagnifier: true,
                  //     itemExtent: 32,
                  //     // This sets the initial item.
                  //     scrollController: FixedExtentScrollController(
                  //       initialItem: 0,
                  //     ),
                  //     // This is called when selected item is changed.
                  //     onSelectedItemChanged: (int idx) {
                  //       setState(() {
                  //         selectedH = putoffHour[idx];
                  //       });
                  //     },
                  //     children: List<Widget>.generate(
                  //       putoffHour.length,
                  //       (int index) {
                  //         return Center(child: Text(putoffHour[index]));
                  //       },
                  //     ),
                  //   ),
                  // );
                );
              },
              child: Container(
                padding: EdgeInsets.symmetric(vertical: 10),
                width: 0.8.sw,
                decoration: BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.circular(15),
                  boxShadow: [
                    BoxShadow(
                      color: Colors.grey.withOpacity(0.3),
                      spreadRadius: 2,
                      blurRadius: 1,
                      offset: Offset(0, 1.5),
                    ),
                  ],
                ),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text(
                      "${selectedH}",
                      style: TextStyle(
                        color: Theme.of(context).primaryColor,
                        fontSize: 48.sp,
                      ),
                    ),
                    Text(
                      " 시간    ",
                      style: TextStyle(
                          color: Color(0xFFBCBCBC),
                          fontSize: 20.sp,
                          fontWeight: FontWeight.bold),
                    ),
                    Text(
                      "${selectedM}",
                      style: TextStyle(
                        color: Theme.of(context).primaryColor,
                        fontSize: 48.sp,
                      ),
                    ),
                    Text(
                      " 분 후",
                      style: TextStyle(
                        color: Color(0xFFBCBCBC),
                        fontSize: 20.sp,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                  ],
                ),
              ),
            ),
            SizedBox(
              height: 0.55.sh,
            ),
            Container(
              child: Column(
                children: [
                  Text(
                    "${hour.padLeft(2, "0")}시 ${minute.padLeft(2, "0")}분에 알람이 울립니다.",
                    style: TextStyle(
                      color: Theme.of(context).primaryColor,
                      fontSize: 20.sp,
                    ),
                  ),
                  ElevatedButton(
                    onPressed: () {
                      Get.toNamed("/plan/putoff3", arguments: planList);
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
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }

  void _showDialog(Widget child) {
    showCupertinoModalPopup<void>(
      context: context,
      builder: (BuildContext context) => Container(
        height: 0.4.sh,
        // padding: EdgeInsets.only(top: 6.0),
        // The Bottom margin is provided to align the popup above the system navigation bar.
        margin: EdgeInsets.only(
          bottom: MediaQuery.of(context).viewInsets.bottom,
        ),
        // Provide a background color for the popup.
        color: CupertinoColors.systemBackground.resolveFrom(context),
        // Use a SafeArea widget to avoid system overlaps.
        child: SafeArea(
          top: false,
          child: child,
        ),
      ),
    );
  }
}
