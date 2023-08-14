import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class PlanWidget extends StatelessWidget {
  static const platform = MethodChannel("samples.flutter.dev/battery");
  final id, title, alarmStartTime, alarmEndTime, categoryCode, complete;
  const PlanWidget({
    super.key,
    required this.id,
    required this.title,
    required this.alarmStartTime,
    required this.alarmEndTime,
    required this.categoryCode,
    required this.complete,
  });

  Future<void> _deletePlan() async {
    String value;
    try {
      value = await platform.invokeMethod("deleteTodoDataFunc", id.toString());
    } on PlatformException catch (e) {
      value = "error message : ${e.message}";
    }
  }

  @override
  Widget build(BuildContext context) {
    bool isPlan = title != "기상" && title != "취침";
    //print(title);
    return Container(
      padding:
          isPlan ? EdgeInsets.zero : EdgeInsets.symmetric(vertical: 0.001.sh),
      margin: EdgeInsets.symmetric(
        vertical: 5,
        horizontal: 20,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        color: isPlan ? Color(0xFFD6FCE5) : Color(0xFFF5F5F5),
        boxShadow: [
          BoxShadow(
            color: Colors.grey.withOpacity(0.3),
            blurRadius: 1,
            spreadRadius: 1,
            offset: const Offset(0, 1),
          ),
        ],
      ),
      child: Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        children: [
          Container(
            child: Row(
              children: [
                Padding(
                  padding: EdgeInsets.symmetric(
                      vertical: 0.02.sh, horizontal: 0.05.sw),
                  child: Icon(
                    Icons.notifications,
                    color: Color(0xFF8A8A8A),
                  ),
                ),
                Text(
                  isPlan
                      ? "${ApiService.DateTimeTo12(alarmStartTime)}\n~${ApiService.DateTimeTo12(alarmEndTime)}"
                      : "${ApiService.DateTimeTo12(alarmEndTime)}",
                  style: TextStyle(
                    color: isPlan ? Color(0xFF3BDE7C) : Color(0xFF8A8A8A),
                    fontSize: 14.sp,
                    fontWeight: isPlan ? FontWeight.bold : FontWeight.normal,
                  ),
                ),
                Container(
                  margin: isPlan
                      ? EdgeInsets.symmetric(horizontal: 0.06.sw)
                      : EdgeInsets.symmetric(horizontal: 0.085.sw),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      isPlan
                          ? Text(
                              categoryCode,
                              style: TextStyle(
                                color: Color(0xFF3BDE7C),
                              ),
                            )
                          : const SizedBox.shrink(),
                      Text(
                        title,
                        style: TextStyle(
                          fontSize: isPlan ? 18.sp : 14.sp,
                          fontWeight:
                              isPlan ? FontWeight.bold : FontWeight.normal,
                          color: isPlan
                              ? const Color(0xFF20884A)
                              : const Color(0xFF8A8A8A),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            ),
          ),
          isPlan
              ? Container(
                  margin: EdgeInsets.only(right: 0.05.sw),
                  child: Stack(
                    children: <Widget>[
                      Positioned.fill(
                        child: Container(
                          margin: EdgeInsets.all(10.sp),
                          decoration: BoxDecoration(
                            //color: Colors.grey.withOpacity(0.5),
                            borderRadius: BorderRadius.circular(50),
                            boxShadow: [
                              BoxShadow(
                                color: Colors.grey.withOpacity(0.4),
                                blurRadius: 1,
                                offset: Offset(0, 1),
                              ),
                            ],
                          ),
                        ),
                      ),
                      Positioned.fill(
                        child: Container(
                          margin: EdgeInsets.all(20.sp),
                          color: const Color(0xFF3BDE7C),
                          // Color
                        ),
                      ),
                      IconButton(
                        icon: Icon(Icons.remove_circle), // Icon
                        color: Colors.white,
                        iconSize: 40.sp,
                        style: IconButton.styleFrom(
                          backgroundColor: Colors.white,
                        ),
                        onPressed: () {
                          _deletePlan();
                        },
                      ),
                    ],
                  ),
                )
              : SizedBox.shrink(),
        ],
      ),
    );
  }
}
