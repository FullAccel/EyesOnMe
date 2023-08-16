import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
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
  static const platform = MethodChannel('samples.flutter.dev/battery');
  late String dropdownValue; // 카테고리
  String planStartTime = "";
  String planEndTime = "";
  DateTime parsedPlanStartTime = DateTime.now();
  DateTime parsedPlanEndTime = DateTime.now();
  final isSelected = [true, false, false];
  String _alarmSound = "노래1";
  String _repeat = "없음";
  bool _isSilenceMode = true;
  final _textController = TextEditingController();

  Future<void> _postTodoDataFunc(String jsonString) async {
    try {
      final result =
          await platform.invokeMethod('postTodoDataFunc', jsonString);
      print("alarm: $result");

      // await platform.invokeMethod('testData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  Future<void> addPlan(String jsonString) async {
    String? ret;
    await _postTodoDataFunc(jsonString);
    print(ret);
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    dropdownValue = SetPlanService.categoryToCode.keys.first;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
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
                color: Theme.of(context).primaryColor,
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
                  color: Theme.of(context).primaryColor,
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
              items: SetPlanService.categoryToCode.keys
                  .map<DropdownMenuItem<String>>(
                (String value) {
                  return DropdownMenuItem<String>(
                    value: value,
                    child: Text(
                      value,
                      style: TextStyle(
                        color: Theme.of(context).primaryColor,
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
                    controller: _textController,
                    maxLength: 12,
                    decoration: InputDecoration(
                      contentPadding: EdgeInsets.only(
                        bottom: -0.01.sh,
                      ),
                      hintText: "플랜명을 작성해주세요.",
                      hintStyle: TextStyle(
                        color: Theme.of(context).primaryColor,
                        fontWeight: FontWeight.w500,
                        fontSize: 20.sp,
                      ),
                      enabledBorder: UnderlineInputBorder(
                        borderSide: BorderSide(
                          color: Theme.of(context).primaryColor,
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
                    foregroundColor: Theme.of(context).primaryColor,
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
                      : parsedPlanStartTime.hour == 12
                          ? Text(
                              "12 : ${parsedPlanStartTime.minute.toString().padLeft(2, "0")}  pm")
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
                        color: Theme.of(context).primaryColor,
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
                    foregroundColor: Theme.of(context).primaryColor,
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
                      : parsedPlanEndTime.hour == 12
                          ? Text(
                              "12 : ${parsedPlanEndTime.minute.toString().padLeft(2, "0")}  pm")
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
                                  if (index == 0) {
                                    _isSilenceMode = true;
                                  } else {
                                    _isSilenceMode = false;
                                  }
                                });
                              },
                              child: Ink(
                                decoration: BoxDecoration(
                                  color: isSelected[index]
                                      ? Theme.of(context).primaryColor
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
                  onPressed: () {
                    Map<String, dynamic> plan = {
                      "title": _textController.text,
                      "startTime": planStartTime,
                      "endTime": planEndTime,
                      "cCode": SetPlanService.categoryToCode[dropdownValue],
                      "isAlarm": true,
                      "alarmType": isSelected.indexOf(true),
                      "alarmRepeat": SetPlanService.repeatToInt[_repeat],
                    };
                    //addPlan(jsonEncode(plan));
                    Get.toNamed('/plan/finish', arguments: plan);
                  },
                  style: FilledButton.styleFrom(
                    backgroundColor: Theme.of(context).primaryColor,
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
          planStartTime = DateTime(
            time.year,
            time.month,
            time.day,
            time.hour,
            time.minute,
            0,
          ).toString();
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
          planEndTime = DateTime(
            time.year,
            time.month,
            time.day,
            time.hour,
            time.minute,
            0,
          ).toString();
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
