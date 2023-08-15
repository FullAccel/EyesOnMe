import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:flutter_slidable/flutter_slidable.dart';

class PlanWidget extends StatefulWidget {
  static const platform = MethodChannel("samples.flutter.dev/battery");
  final id,
      title,
      toDoStatusCode,
      alarmStartTime,
      alarmEndTime,
      categoryCode,
      curRoute;

  const PlanWidget({
    super.key,
    required this.id,
    required this.title,
    required this.toDoStatusCode,
    required this.alarmStartTime,
    required this.alarmEndTime,
    required this.categoryCode,
    required this.curRoute,
  });

  @override
  State<PlanWidget> createState() => _PlanWidgetState();
}

class _PlanWidgetState extends State<PlanWidget> {
  Future<void> _deletePlan() async {
    String value;
    try {
      value = await PlanWidget.platform
          .invokeMethod("deleteTodoDataFunc", widget.id.toString());
    } on PlatformException catch (e) {
      value = "error message : ${e.message}";
    }
  }

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    bool isPlan = widget.title != "기상" && widget.title != "취침";
    DateTime now = DateTime.now();
    //print(title);
    return Slidable(
      endActionPane: ActionPane(
        motion: ScrollMotion(),
        children: [
          SlidableAction(
            onPressed: null,
            backgroundColor: Color(0xFFDADADA).withOpacity(0.5),
            foregroundColor: Colors.red,
            icon: Icons.delete,
          ),
          SlidableAction(
            onPressed: null,
            backgroundColor: Color(0xFFDADADA).withOpacity(0.8),
            foregroundColor: Color(0xFF3BDE7C),
            icon: Icons.border_color,
          ),
          SlidableAction(
            onPressed: null,
            backgroundColor: Color(0xFF3BDE7C),
            foregroundColor: Colors.white,
            label: "달성",
          ),
        ],
      ),
      child: Container(
        padding:
            isPlan ? EdgeInsets.zero : EdgeInsets.symmetric(vertical: 0.001.sh),
        margin: EdgeInsets.symmetric(
          vertical: 5,
          horizontal: 20,
        ),
        decoration: BoxDecoration(
          borderRadius: BorderRadius.circular(10),
          color: now.isAfter(DateTime.parse(widget.alarmEndTime))
              ? Color(0xFFF5F5F5)
              : Color(0xFFD6FCE5),
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
                        ? "${ApiService.DateTimeTo12(widget.alarmStartTime)}\n~${ApiService.DateTimeTo12(widget.alarmEndTime)}"
                        : "${ApiService.DateTimeTo12(widget.alarmEndTime)}",
                    style: TextStyle(
                      color: !now.isAfter(DateTime.parse(widget.alarmEndTime))
                          ? Color(0xFF3BDE7C)
                          : Color(0xFF8A8A8A),
                      fontSize: 14.sp,
                      fontWeight: isPlan ? FontWeight.bold : FontWeight.normal,
                    ),
                  ),
                  Container(
                    margin: !now.isAfter(DateTime.parse(widget.alarmEndTime))
                        ? EdgeInsets.symmetric(horizontal: 0.06.sw)
                        : EdgeInsets.symmetric(horizontal: 0.085.sw),
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        isPlan
                            ? Text(
                                widget.categoryCode,
                                style: TextStyle(
                                  color: now.isAfter(
                                          DateTime.parse(widget.alarmEndTime))
                                      ? Color(0xFF8A8A8A)
                                      : Color(0xFF3BDE7C),
                                ),
                              )
                            : const SizedBox.shrink(),
                        Text(
                          widget.title,
                          style: TextStyle(
                            fontSize: isPlan ? 18.sp : 14.sp,
                            fontWeight:
                                isPlan ? FontWeight.bold : FontWeight.normal,
                            color: !now.isAfter(
                                    DateTime.parse(widget.alarmEndTime))
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
                        widget.curRoute.contains("show")
                            ? IconButton(
                                icon: Icon(Icons.remove_circle), // Icon
                                color: Colors.white,
                                iconSize: 40.sp,
                                style: IconButton.styleFrom(
                                  backgroundColor: Colors.white,
                                ),
                                onPressed: () {
                                  _deletePlan();
                                },
                              )
                            : SizedBox.shrink(),
                      ],
                    ),
                  )
                : SizedBox.shrink(),
          ],
        ),
      ),
    );
  }
}
