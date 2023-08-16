import 'package:eom_fe/services/challenge_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';
import 'package:intl/intl.dart';

import '../../services/setplan_service.dart';

class MakeChallenge extends StatefulWidget {
  MakeChallenge({super.key});
  FocusNode textFocus = FocusNode();

  @override
  State<MakeChallenge> createState() => _MakeChallengeState();
}

class _MakeChallengeState extends State<MakeChallenge> {
  static const platform = MethodChannel('samples.flutter.dev/battery');

  String dropdownValue = "일상";
  final _textController = TextEditingController();
  DateTime _selectedDay = DateTime.now();
  int _interval = 0;
  int _count = 0;
  List<String> intervals = ["하루", "일주일", "이주일", "한 달", "설정 안 함"];
  List<int> counts = [1, 2, 3, 4, 5, 6, 7];

  @override
  void setState(VoidCallback fn) {
    // TODO: implement setState
    super.setState(fn);
  }

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        widget.textFocus.unfocus();
      },
      child: Scaffold(
        resizeToAvoidBottomInset: false,
        appBar: AppBar(
          toolbarHeight: 0.08.sh,
          title: Text(
            "챌린지 설정",
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
                      maxLength: 10,
                      decoration: InputDecoration(
                        contentPadding: EdgeInsets.only(
                          bottom: -0.01.sh,
                        ),
                        hintText: "어떤 활동에 도전하나요?",
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
                      foregroundColor: Color(0xFF3BDE7C),
                      fixedSize: Size(
                        0.6.sw,
                        0.0625.sh,
                      ),
                      textStyle: TextStyle(
                        fontSize: 32.sp,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                    onPressed: () {
                      _selectDate(context);
                    },
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          "${_selectedDay.year}",
                          style: TextStyle(
                              fontSize: 22.sp,
                              color: Theme.of(context).primaryColor),
                        ),
                        Text(
                          " 년   ",
                          style: TextStyle(
                              fontSize: 16.sp,
                              color: Theme.of(context).shadowColor),
                        ),
                        Text(
                          "${_selectedDay.month}",
                          style: TextStyle(
                              fontSize: 22.sp,
                              color: Theme.of(context).primaryColor),
                        ),
                        Text(
                          " 월  ",
                          style: TextStyle(
                              fontSize: 16.sp,
                              color: Theme.of(context).shadowColor),
                        ),
                        Text(
                          "${_selectedDay.day}",
                          style: TextStyle(
                              fontSize: 22.sp,
                              color: Theme.of(context).primaryColor),
                        ),
                        Text(
                          " 일 까지",
                          style: TextStyle(
                              fontSize: 16.sp,
                              color: Theme.of(context).shadowColor),
                        ),
                      ],
                    ),
                  ),
                  SizedBox(height: 0.05.sh),
                  ElevatedButton(
                    style: ElevatedButton.styleFrom(
                      shape: RoundedRectangleBorder(
                        borderRadius: BorderRadius.circular(15),
                      ),
                      backgroundColor: Color(0xFFF5F5F5),
                      foregroundColor: Color(0xFF3BDE7C),
                      fixedSize: Size(
                        0.6.sw,
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
                            actions: [
                              TextButton(
                                style: TextButton.styleFrom(
                                  backgroundColor:
                                      Theme.of(context).primaryColor,
                                  shape: RoundedRectangleBorder(
                                    borderRadius: BorderRadius.circular(30),
                                  ),
                                  foregroundColor: Colors.white,
                                  padding: EdgeInsets.symmetric(
                                      vertical: 12, horizontal: 40),
                                  elevation: 5,
                                ),
                                onPressed: () {
                                  Navigator.of(Get.overlayContext!,
                                          rootNavigator: true)
                                      .pop();
                                },
                                child: Text(
                                  "확인",
                                  style: TextStyle(
                                    fontSize: 20.sp,
                                    fontWeight: FontWeight.w600,
                                  ),
                                ),
                              ),
                            ],
                            backgroundColor: Colors.white,
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
                            content: Row(
                              mainAxisAlignment: MainAxisAlignment.center,
                              children: [
                                Container(
                                  width: 0.3.sw,
                                  height: 0.3.sh,
                                  child: ListWheelScrollView(
                                    useMagnifier: true,
                                    magnification: 1.2,
                                    physics: const FixedExtentScrollPhysics(),
                                    itemExtent: 40.0,
                                    onSelectedItemChanged: (value) {
                                      setState(() {
                                        _interval = value;
                                        //print(_interval);
                                      });
                                    },
                                    children: List.generate(intervals.length,
                                        (index) {
                                      return Center(
                                        child: Text(
                                          '${intervals[index]}',
                                          style: TextStyle(
                                            fontSize: 24.sp,
                                          ),
                                        ),
                                      );
                                    }),
                                  ),
                                ),
                                SizedBox(width: 20),
                                Container(
                                  width: 0.3.sw,
                                  height: 0.3.sh,
                                  child: ListWheelScrollView(
                                    useMagnifier: true,
                                    magnification: 1.2,
                                    physics: const FixedExtentScrollPhysics(),
                                    overAndUnderCenterOpacity: 0.5,
                                    itemExtent: 40.0,
                                    onSelectedItemChanged: (value) {
                                      setState(() {
                                        _count = value;
                                      });
                                    },
                                    children:
                                        List.generate(counts.length, (index) {
                                      return Center(
                                        child: Text(
                                          '${counts[index]}',
                                          style: TextStyle(
                                            fontSize: 24.sp,
                                          ),
                                        ),
                                      );
                                    }),
                                  ),
                                ),
                              ],
                            ),
                          );
                        },
                      );
                    },
                    child: Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Text(
                          intervals[_interval],
                          style: TextStyle(
                              fontSize: 22.sp,
                              color: Theme.of(context).primaryColor),
                        ),
                        Text(
                          " 에",
                          style: TextStyle(
                              fontSize: 16.sp,
                              color: Theme.of(context).shadowColor),
                        ),
                        SizedBox(width: 0.05.sw),
                        Container(
                          margin: EdgeInsets.only(top: 5),
                          child: Text(
                            "${counts[_count]}",
                            style: TextStyle(
                                fontSize: 22.sp,
                                color: Theme.of(context).primaryColor),
                          ),
                        ),
                        Text(
                          " 번",
                          style: TextStyle(
                              fontSize: 16.sp,
                              color: Theme.of(context).shadowColor),
                        ),
                      ],
                    ),
                  ),
                ],
              ),
            ),
            SizedBox(height: 0.1.sh),
            Container(
              padding: EdgeInsets.symmetric(horizontal: 0.03.sw),
              child: Center(
                child: Column(
                  children: [
                    SizedBox(height: 0.3.sh),
                    FilledButton(
                      onPressed: () {
                        _makeChallengeWOValidator();
                        Future.delayed(Duration(milliseconds: 500), () {});
                        Get.offAllNamed("/");
                      },
                      style: FilledButton.styleFrom(
                        backgroundColor: Theme.of(context).primaryColor,
                        textStyle: TextStyle(
                            fontWeight: FontWeight.bold, fontSize: 18.sp),
                        fixedSize: Size(0.8.sw, 0.06.sh),
                        elevation: 5,
                      ),
                      child: Text("작성 완료"),
                    ),
                    SizedBox(height: 0.01.sh),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }

  Future<void> _makeChallengeWOValidator() async {
    Map<String, dynamic> param = {
      "title": _textController.text,
      "deadline": DateFormat("yyyy-MM-dd").format(_selectedDay),
      "validationIntervalCode": ChallengeService.intervalCodeOf[_interval],
      "validationCountPerInterval": counts[_count],
      "categoryCode": SetPlanService.categoryToCode[dropdownValue],
    };
    print(param);

    try {
      await platform.invokeMethod('makeChallengeWOValidator', param.toString());
    } on PlatformException catch (e) {
      throw Exception("Exception at invokeMethod: makeChallengeWOValidator");
    }
  }

  Future _selectDate(BuildContext context) async {
    final DateTime? selected = await showDatePicker(
      context: context,
      initialDate: DateTime.now(),
      firstDate: DateTime(2022),
      lastDate: DateTime(2030),
      helpText: "",
    );
    if (selected != null) {
      setState(() {
        _selectedDay = selected;
      });
    }
  }
}
