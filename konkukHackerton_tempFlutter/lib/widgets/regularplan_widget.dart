import 'package:eom_fe/models/plan_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class RegularPlanWidget extends StatefulWidget {
  final PlanModel plan;

  const RegularPlanWidget({
    super.key,
    required this.plan,
  });

  @override
  State<RegularPlanWidget> createState() => _RegularPlanWidgetState();
}

class _RegularPlanWidgetState extends State<RegularPlanWidget> {
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.symmetric(
        vertical: 8,
        horizontal: 20,
      ),
      decoration: BoxDecoration(
        borderRadius: BorderRadius.circular(10),
        color: Color(0xFFF5F5F5),
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
            widget.plan.alarmStartTime.substring(11, 16),
            style: TextStyle(
              color: Colors.grey,
              fontSize: 14.sp,
            ),
          ),
          Container(
            margin: EdgeInsets.symmetric(horizontal: 36),
            child: Text(
              widget.plan.title,
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
