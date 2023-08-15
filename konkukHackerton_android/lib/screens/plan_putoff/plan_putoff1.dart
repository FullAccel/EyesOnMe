import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../../widgets/appbar_widget.dart';

class PlanPutoff1 extends StatefulWidget {
  const PlanPutoff1({super.key});

  @override
  State<PlanPutoff1> createState() => _PlanPutoff1State();
}

class _PlanPutoff1State extends State<PlanPutoff1> {
  final reasons = [
    "이전 일정을 아직 못 끝냈어요.",
    "집중이 잘 되지 않아요.",
    "다른 일정이 생겼어요",
    "준비가 더 필요해요",
    "컨디션이 좋지 않아요",
  ];

  List<bool> isPressed = [
    false,
    false,
    false,
    false,
    false,
    false,
  ];
  int wasPressed = -1;
  final textController = TextEditingController();
  bool isExpanded = false;

  canGoNext() {
    if (wasPressed >= 0 || textController.text.isNotEmpty) {
      return Get.toNamed('/plan/putoff2', arguments: Get.arguments);
    }

    return null;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      resizeToAvoidBottomInset: false,
      appBar: AppbarWidget(
        title: "플랜 미루기",
      ),
      body: SingleChildScrollView(
        child: Column(
          children: [
            SizedBox(
              height: 20,
            ),
            Text(
              "이유에 대해 간략히 알려주세요.",
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 18.sp,
              ),
            ),
            SizedBox(
              height: 10,
            ),
            Column(
              children: [
                ListView.separated(
                  shrinkWrap: true,
                  padding: EdgeInsets.symmetric(
                    vertical: 5,
                    horizontal: 25,
                  ),
                  itemBuilder: (context, index) {
                    return Container(
                      child: ElevatedButton(
                        onPressed: () {
                          setState(() {
                            if (wasPressed >= 0) {
                              isPressed[wasPressed] = false;
                            }
                            wasPressed = index;
                            isPressed[index] = true;
                            isExpanded = false;
                          });
                        },
                        child: Text(reasons[index]),
                        style: ElevatedButton.styleFrom(
                          alignment: Alignment.centerLeft,
                          backgroundColor: isPressed[index]
                              ? Color(0xFF3BDE7C)
                              : Colors.white,
                          foregroundColor:
                              isPressed[index] ? Colors.white : Colors.black,
                          shadowColor: Colors.grey,
                          elevation: 3,
                          padding: EdgeInsets.symmetric(
                            vertical: 10,
                            horizontal: 20,
                          ),
                        ),
                      ),
                    );
                  },
                  separatorBuilder: (context, index) => SizedBox(
                    height: 10,
                  ),
                  itemCount: reasons.length,
                ),
                GestureDetector(
                  onTap: () {
                    setState(() {
                      if (wasPressed < 0) {
                        wasPressed = 5;
                      } else {
                        isPressed[wasPressed] = false;
                      }
                      isExpanded = !isExpanded;

                      wasPressed = 5;
                    });
                  },
                  child: AnimatedContainer(
                    alignment: Alignment.centerLeft,
                    width: 1.sw - 50,
                    margin: EdgeInsets.symmetric(vertical: 8),
                    padding: EdgeInsets.symmetric(
                      horizontal: 20,
                    ),
                    decoration: BoxDecoration(
                      borderRadius: BorderRadius.circular(8),
                      boxShadow: [
                        BoxShadow(
                          color: Colors.grey,
                          blurRadius: 2,
                          offset: Offset(0, 3),
                        ),
                      ],
                      color: isExpanded ? Color(0xFF3BDE7C) : Colors.white,
                    ),
                    duration: Duration(milliseconds: 300),
                    height: isExpanded ? 120 : 50,
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        Text(
                          "기타",
                          style: TextStyle(
                            color: isExpanded ? Colors.white : Colors.black,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        SizedBox(
                          height: isExpanded ? 10 : 0,
                        ),
                        isExpanded
                            ? TextField(
                                maxLength: 16,
                                controller: textController,
                                decoration: InputDecoration(
                                  counterText: "",
                                  filled: true,
                                  fillColor: Colors.white,
                                  contentPadding: EdgeInsets.symmetric(
                                    vertical: 10,
                                    horizontal: 20,
                                  ),
                                  hintText: '이유를 간단하게 적어주세요.',
                                  border: OutlineInputBorder(
                                    borderRadius: BorderRadius.circular(30),
                                    borderSide: BorderSide(
                                      style: BorderStyle.none,
                                    ),
                                  ),
                                ),
                              )
                            : SizedBox.shrink(),
                      ],
                    ),
                  ),
                ),
              ],
            ),
            Container(
              margin: EdgeInsets.all(30),
              child: ElevatedButton(
                onPressed: wasPressed < 0 ? null : canGoNext,
                child: Text(
                  "작성 완료",
                  style: TextStyle(
                    fontWeight: FontWeight.bold,
                    fontSize: 20.sp,
                  ),
                ),
                style: ElevatedButton.styleFrom(
                  disabledBackgroundColor: Color(0xFFDADADA),
                  disabledForegroundColor: Color(0xFF8A8A8A),
                  padding: EdgeInsets.symmetric(
                    vertical: 10,
                    horizontal: 120,
                  ),
                  backgroundColor: Color(0xFF3BDE7C),
                  foregroundColor: Colors.white,
                  shadowColor: Colors.grey,
                  elevation: 3,
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30),
                  ),
                ),
              ),
            ),
            SizedBox(
              height: 40,
            ),
          ],
        ),
      ),
    );
  }
}
