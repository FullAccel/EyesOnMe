import 'dart:convert';

import 'package:eom_fe/services/setplan_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter_picker/picker.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_time_picker_spinner/flutter_time_picker_spinner.dart';
import 'package:get/get.dart';

class PlanningWakeupTime extends StatefulWidget {
  const PlanningWakeupTime({super.key});

  @override
  State<PlanningWakeupTime> createState() => _PlanningWakeupTimeState();
}

class _PlanningWakeupTimeState extends State<PlanningWakeupTime> {
  String wakeup_time = DateTime.now().toString();

  final isSelected = [true, false, false];
  String _alarmSound = "노래1";
  String _repeat = "없음";
  String _mission = "따라 쓰기";
  bool _isSilenceMode = false;

  Widget hourMinute12H(DateTime t) {
    return TimePickerSpinner(
      time: t,
      isForce2Digits: true,
      is24HourMode: false,
      minutesInterval: 15,
      onTimeChange: (time) {
        setState(() {
          wakeup_time = DateTime(
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

  void showMissionPicker(BuildContext context) {
    Picker(
        cancelText: "",
        adapter: PickerDataAdapter<String>(
          pickerData: JsonDecoder().convert(SetPlanService.missions),
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
            _mission = picker.getSelectedValues().first;
            //print(alarmSound);
          });
        }).showDialog(context);
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    //print(DateTime.now().toString());
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0.08.sh,
        title: Text(
          "기상 시간",
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
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Column(
            children: [
              SizedBox(
                height: 0.03.sh,
              ),
              Text(
                "언제 일어나는게 좋을까요?",
                style: TextStyle(
                  fontSize: 18.sp,
                  fontWeight: FontWeight.w600,
                ),
              ),
              SizedBox(
                height: 0.02.sh,
              ),
              Builder(builder: (BuildContext context) {
                DateTime parsedWakeupTime = DateTime.parse(wakeup_time);
                return ElevatedButton(
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Color(0xFFF5F5F5),
                    foregroundColor: Theme.of(context).primaryColor,
                    minimumSize: Size(
                      0.6.sw,
                      0.07.sh,
                    ),
                    textStyle: TextStyle(
                      fontSize: 40.sp,
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
                            content: hourMinute12H(parsedWakeupTime),
                            actions: [
                              TextButton(
                                onPressed: () {
                                  Navigator.of(context).pop();
                                },
                                child: Text("확인"),
                              )
                            ],
                          );
                        });
                  },
                  child: parsedWakeupTime.hour > 12
                      ? Text(
                          "${(parsedWakeupTime.hour % 12).toString().padLeft(2, "0")} : ${parsedWakeupTime.minute.toString().padLeft(2, "0")}  pm")
                      : parsedWakeupTime.hour == 12
                          ? Text(
                              "12 : ${parsedWakeupTime.minute.toString().padLeft(2, "0")}  pm")
                          : Text(
                              "${parsedWakeupTime.hour.toString().padLeft(2, "0")} : ${parsedWakeupTime.minute.toString().padLeft(2, "0")}  am"),
                );
              })
            ],
          ),
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
                Container(
                  padding: EdgeInsets.symmetric(horizontal: 0.05.sw),
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      Text(
                        "미션",
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
                          showMissionPicker(context);
                        },
                        child: Row(
                          mainAxisAlignment: MainAxisAlignment.spaceBetween,
                          children: [
                            Text(
                              _mission,
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
                SizedBox(height: 0.05.sh),
                FilledButton(
                  onPressed: () {
                    Get.toNamed(
                      '/plan/sleep',
                      arguments: {
                        "argName": "wakeupAndSleep",
                        "data": [
                          {
                            "startTime": wakeup_time,
                            "alarmType": isSelected.indexOf(true),
                            "alarmRepeat": SetPlanService.repeatToInt[_repeat]
                          },
                        ],
                      },
                    );
                  },
                  style: FilledButton.styleFrom(
                    backgroundColor: Theme.of(context).primaryColor,
                    textStyle: TextStyle(fontWeight: FontWeight.bold),
                    fixedSize: Size(0.8.sw, 0.05.sh),
                  ),
                  child: Text("작성 완료"),
                ),
                SizedBox(height: 0.03.sh),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
