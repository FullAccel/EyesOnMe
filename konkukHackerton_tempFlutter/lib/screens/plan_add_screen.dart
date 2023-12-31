import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter_picker/picker.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_time_picker_spinner/flutter_time_picker_spinner.dart';
import 'package:get/get.dart';

import '../services/setplan_service.dart';

class PlanAddScreen extends StatefulWidget {
  PlanAddScreen({super.key});

  @override
  State<PlanAddScreen> createState() => _PlanAddScreenState();
}

class _PlanAddScreenState extends State<PlanAddScreen> {
  late String dropdownValue;
  String planStartTime = "";
  String planEndTime = "";
  DateTime parsedPlanStartTime = DateTime.now();
  DateTime parsedPlanEndTime = DateTime.now();
  final isSelected = [true, false, false];
  String _alarmSound = "노래1";
  String _repeat = "없음";
  bool _isSilenceMode = false;
  final categorys = {
    "일상": "C001",
    "업무/학습": "C002",
    "모임/약속": "C003",
    "건강/운동": "C004",
    "여가/오락": "C005",
    "재정관리": "C006",
    "기타": "C007",
  };

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    dropdownValue = categorys.keys.first;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0.08.sh,
        title: Text(
          "세부 플랜 설정",
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
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Container(
            alignment: Alignment.center,
            width: 0.4.sw,
            margin: EdgeInsets.only(top: 0.02.sh, left: 0.08.sw),
            decoration: BoxDecoration(
              borderRadius: BorderRadius.circular(45),
              border: Border.all(
                color: Color(0xFF3BDE7C),
                width: 4,
              ),
            ),
            child: DropdownButton<String>(
              padding: EdgeInsets.only(left: 0.05.sw),
              hint: Text("카테고리"),
              value: dropdownValue,
              icon: Container(
                margin: EdgeInsets.only(right: 0.05.sw),
                child: Icon(
                  Icons.keyboard_arrow_down_sharp,
                  color: Color(0xFF3BDE7C),
                ),
              ),
              underline: SizedBox.shrink(),
              isExpanded: true,
              elevation: 12,
              style: TextStyle(
                color: Colors.white,
                fontWeight: FontWeight.w600,
                fontSize: 16.sp,
              ),
              dropdownColor: Colors.white,
              borderRadius: BorderRadius.circular(10),
              onChanged: (String? value) {
                // This is called when the user selects an item.
                setState(() {
                  dropdownValue = value!;
                });
              },
              items: categorys.keys.map<DropdownMenuItem<String>>(
                (String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(
                      value,
                      style: TextStyle(
                        color: Color(0xFF3BDE7C),
                      ),
                    ),
                  );
                },
              ).toList(),
            ),
          ),
          Align(
            child: Column(
              children: [
                SizedBox(
                  height: 0.1.sh,
                  width: 0.8.sw,
                  child: TextField(
                    maxLength: 12,
                    decoration: InputDecoration(
                      contentPadding: EdgeInsets.only(
                        bottom: -0.01.sh,
                      ),
                      hintText: "플랜명을 작성해주세요.",
                      hintStyle: TextStyle(
                        color: Color(0xFF3BDE7C),
                        fontWeight: FontWeight.w500,
                        fontSize: 20.sp,
                      ),
                      enabledBorder: UnderlineInputBorder(
                        borderSide: BorderSide(
                          color: Color(0xFF3BDE7C),
                          width: 2,
                        ),
                      ),
                    ),
                  ),
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    backgroundColor: Color(0xFFF5F5F5),
                    foregroundColor: Color(0xFF3BDE7C),
                    fixedSize: Size(
                      0.54.sw,
                      0.0625.sh,
                    ),
                    textStyle: TextStyle(
                      fontSize: 32.sp,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  onPressed: () {
                    showDialog(
                        //barrierDismissible: false,
                        context: context,
                        builder: (BuildContext context) {
                          return AlertDialog(
                            titlePadding: EdgeInsets.symmetric(
                              vertical: 5,
                              horizontal: 5,
                            ),
                            title: Container(
                              alignment: Alignment.centerLeft,
                              child: IconButton(
                                onPressed: () {
                                  Navigator.of(context).pop();
                                },
                                icon: Icon(
                                  Icons.arrow_back_outlined,
                                  size: 30,
                                ),
                              ),
                            ),
                            content: hourMinute12H_start(parsedPlanStartTime),
                            actions: [
                              TextButton(
                                onPressed: () {
                                  setState(() {
                                    parsedPlanStartTime =
                                        DateTime.parse(planStartTime);
                                  });
                                  Navigator.of(context).pop();
                                },
                                child: Text("확인"),
                              )
                            ],
                          );
                        });
                  },
                  child: parsedPlanStartTime.hour > 12
                      ? Text(
                          "${(parsedPlanStartTime.hour % 12).toString().padLeft(2, "0")} : ${parsedPlanStartTime.minute.toString().padLeft(2, "0")}  pm")
                      : Text(
                          "${parsedPlanStartTime.hour.toString().padLeft(2, "0")} : ${parsedPlanStartTime.minute.toString().padLeft(2, "0")}  am"),
                ),
                SizedBox(
                  height: 0.05.sh,
                  child: RotatedBox(
                    quarterTurns: 1,
                    child: Text(
                      "~",
                      style: TextStyle(
                        fontSize: 32.sp,
                        color: Color(0xFF3BDE7C),
                      ),
                      textAlign: TextAlign.center,
                    ),
                  ),
                ),
                ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    shape: RoundedRectangleBorder(
                      borderRadius: BorderRadius.circular(15),
                    ),
                    backgroundColor: Color(0xFFF5F5F5),
                    foregroundColor: Color(0xFF3BDE7C),
                    fixedSize: Size(
                      0.54.sw,
                      0.0625.sh,
                    ),
                    textStyle: TextStyle(
                      fontSize: 32.sp,
                      fontWeight: FontWeight.w600,
                    ),
                  ),
                  onPressed: () {
                    showDialog(
                        //barrierDismissible: false,
                        context: context,
                        builder: (BuildContext context) {
                          return AlertDialog(
                            titlePadding: EdgeInsets.symmetric(
                              vertical: 5,
                              horizontal: 5,
                            ),
                            title: Container(
                              alignment: Alignment.centerLeft,
                              child: IconButton(
                                onPressed: () {
                                  Navigator.of(context).pop();
                                },
                                icon: Icon(
                                  Icons.arrow_back_outlined,
                                  size: 30,
                                ),
                              ),
                            ),
                            content: hourMinute12H_end(parsedPlanEndTime),
                            actions: [
                              TextButton(
                                onPressed: () {
                                  setState(() {
                                    parsedPlanEndTime =
                                        DateTime.parse(planEndTime);
                                  });
                                  Navigator.of(context).pop();
                                },
                                child: Text("확인"),
                              )
                            ],
                          );
                        });
                  },
                  child: parsedPlanEndTime.hour > 12
                      ? Text(
                          "${(parsedPlanEndTime.hour % 12).toString().padLeft(2, "0")} : ${parsedPlanEndTime.minute.toString().padLeft(2, "0")}  pm")
                      : Text(
                          "${parsedPlanEndTime.hour.toString().padLeft(2, "0")} : ${parsedPlanEndTime.minute.toString().padLeft(2, "0")}  am"),
                ),
              ],
            ),
          ),
          SizedBox(height: 0.1.sh),
          Container(
            padding: EdgeInsets.symmetric(horizontal: 0.03.sw),
            child: Column(
              children: [
                Container(
                  padding: EdgeInsets.symmetric(
                      vertical: 0.03.sh, horizontal: 0.05.sw),
                  decoration: BoxDecoration(
                    border: Border(
                      top: BorderSide(
                        color: Colors.grey.withOpacity(0.5),
                      ),
                    ),
                  ),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "알람",
                        style: TextStyle(
                          fontSize: 16.sp,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                      Ink(
                        width: 0.7.sw,
                        height: 0.05.sh,
                        decoration: BoxDecoration(
                          color: Colors.white,
                          borderRadius: BorderRadius.circular(45),
                          boxShadow: [
                            BoxShadow(
                              color: Colors.grey.withOpacity(0.5),
                              blurRadius: 1,
                              spreadRadius: 1,
                              offset: Offset(0, 1),
                            ),
                          ],
                        ),
                        child: GridView.count(
                          padding: EdgeInsets.all(4),
                          primary: true,
                          crossAxisCount: 3,
                          crossAxisSpacing: 3,
                          childAspectRatio: 2.5,
                          children: List.generate(
                            SetPlanService.soundSettings.length,
                            (index) => InkWell(
                              onTap: () {
                                setState(() {
                                  for (int i = 0;
                                      i < SetPlanService.soundSettings.length;
                                      i++) {
                                    if (i == index) {
                                      isSelected[i] = true;
                                    } else {
                                      isSelected[i] = false;
                                    }
                                  }
                                  if (index == 2) {
                                    _isSilenceMode = true;
                                  } else {
                                    _isSilenceMode = false;
                                  }
                                });
                              },
                              child: Ink(
                                decoration: BoxDecoration(
                                  color: isSelected[index]
                                      ? Color(0xFF3BDE7C)
                                      : Colors.white,
                                  borderRadius: BorderRadius.circular(45),
                                ),
                                child: Center(
                                  child: Text(
                                    SetPlanService.soundSettings[index],
                                    style: TextStyle(
                                      color: isSelected[index]
                                          ? Colors.white
                                          : Colors.grey,
                                      fontWeight: FontWeight.bold,
                                    ),
                                  ),
                                ),
                              ),
                            ),
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 0.05.sw),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "알람음",
                        style: TextStyle(
                          fontSize: 16.sp,
                          fontWeight: FontWeight.w600,
                          color: _isSilenceMode
                              ? Colors.black.withOpacity(0.3)
                              : Colors.black,
                        ),
                      ),
                      OutlinedButton(
                        style: OutlinedButton.styleFrom(
                          backgroundColor: Color(0xFFF5F5F5).withOpacity(0.9),
                          foregroundColor: Colors.black,
                          elevation: 2,
                          disabledBackgroundColor:
                              Color(0xFFF5F5F5).withOpacity(1),
                          disabledForegroundColor:
                              Colors.black.withOpacity(0.3),
                          fixedSize: Size(0.7.sw, 0.05.sh),
                        ),
                        onPressed: _isSilenceMode
                            ? null
                            : () {
                                showAlarmPicker(context);
                              },
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              _alarmSound,
                            ),
                            Icon(
                              Icons.keyboard_arrow_down_outlined,
                              color: Colors.grey.withOpacity(0.8),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 0.05.sw),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "반복",
                        style: TextStyle(
                          fontSize: 16.sp,
                          fontWeight: FontWeight.w600,
                        ),
                      ),
                      OutlinedButton(
                        style: OutlinedButton.styleFrom(
                          backgroundColor: Color(0xFFF5F5F5).withOpacity(0.9),
                          foregroundColor: Colors.black,
                          elevation: 2,
                          fixedSize: Size(0.7.sw, 0.05.sh),
                        ),
                        onPressed: () {
                          showRepeatPicker(context);
                        },
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              _repeat,
                            ),
                            Icon(
                              Icons.keyboard_arrow_down_outlined,
                              color: Colors.grey.withOpacity(0.8),
                            ),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
                SizedBox(height: 0.1.sh),
                FilledButton(
                  onPressed: () => Get.toNamed(
                    '/plan/finish',
                    arguments:
                        Get.arguments.addAll({planStartTime, planEndTime}),
                  ),
                  style: FilledButton.styleFrom(
                    backgroundColor: Color(0xFF3BDE7C),
                    textStyle: TextStyle(fontWeight: FontWeight.bold),
                    fixedSize: Size(0.8.sw, 0.05.sh),
                  ),
                  child: Text("작성 완료"),
                ),
                SizedBox(height: 0.01.sh),
              ],
            ),
          ),
        ],
      ),
    );
  }

  Widget hourMinute12H_start(DateTime t) {
    return TimePickerSpinner(
      time: t,
      isForce2Digits: true,
      is24HourMode: false,
      minutesInterval: 15,
      onTimeChange: (time) {
        setState(() {
          planStartTime = time.toString();
        });
      },
    );
  }

  Widget hourMinute12H_end(DateTime t) {
    return TimePickerSpinner(
      time: t,
      isForce2Digits: true,
      is24HourMode: false,
      minutesInterval: 15,
      onTimeChange: (time) {
        setState(() {
          planEndTime = time.toString();
        });
      },
    );
  }

  void showAlarmPicker(BuildContext context) {
    Picker(
        cancelText: "",
        adapter: PickerDataAdapter<String>(
          pickerData: JsonDecoder().convert(SetPlanService.alarmSounds),
          isArray: true,
        ),
        hideHeader: true,
        selecteds: [0],
        title: Container(
          alignment: Alignment.centerLeft,
          child: Builder(builder: (context) {
            return IconButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              icon: Icon(
                Icons.arrow_back_outlined,
                size: 30,
              ),
            );
          }),
        ),
        selectedTextStyle: TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.w600,
        ),
        onConfirm: (Picker picker, List value) {
          //print(value.toString());
          //print(picker.getSelectedValues());
          setState(() {
            _alarmSound = picker.getSelectedValues().first;
            //print(alarmSound);
          });
        }).showDialog(context);
  }

  void showRepeatPicker(BuildContext context) {
    Picker(
        cancelText: "",
        adapter: PickerDataAdapter<String>(
          pickerData: JsonDecoder().convert(SetPlanService.repeats),
          isArray: true,
        ),
        hideHeader: true,
        selecteds: [0],
        title: Container(
          alignment: Alignment.centerLeft,
          child: Builder(builder: (context) {
            return IconButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              icon: Icon(
                Icons.arrow_back_outlined,
                size: 30,
              ),
            );
          }),
        ),
        selectedTextStyle: TextStyle(
          color: Colors.black,
          fontWeight: FontWeight.w600,
        ),
        onConfirm: (Picker picker, List value) {
          //print(value.toString());
          //print(picker.getSelectedValues());
          setState(() {
            _repeat = picker.getSelectedValues().first;
            //print(alarmSound);
          });
        }).showDialog(context);
  }
}
