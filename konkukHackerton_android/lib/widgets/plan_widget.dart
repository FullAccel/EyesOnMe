import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class PlanWidget extends StatefulWidget {
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

  @override
  State<PlanWidget> createState() => _PlanWidgetState();
}

class _PlanWidgetState extends State<PlanWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(
        vertical: 8,
        horizontal: 20,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(20),
        color: Color(0xFFD6FCE5),
        boxShadow: [
          BoxShadow(
              color: Colors.grey.withOpacity(0.5),
              blurRadius: 1,
              spreadRadius: 1,
              offset: Offset(0, 2)),
        ],
      ),
      child: Row(
        children: [
          Padding(
            padding: const EdgeInsets.symmetric(vertical: 10, horizontal: 15),
            child: Icon(
              Icons.notifications,
              color: Color(0xFF8A8A8A),
            ),
          ),
          Text(
            "${widget.alarmStartTime.toString().substring(11, 16)}\n~${widget.alarmEndTime.toString().substring(11, 16)}",
            style: TextStyle(
              color: Color(0xFF3BDE7C),
              fontSize: 14.sp,
            ),
          ),
          Container(
            margin: EdgeInsets.symmetric(horizontal: 36),
            child: Text(
              widget.title,
              style: TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
