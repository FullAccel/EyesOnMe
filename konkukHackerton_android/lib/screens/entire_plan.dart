import 'package:eom_fe/services/api_service.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:weekly_date_picker/weekly_date_picker.dart';

import '../models/plan_model.dart';
import '../widgets/plan_widget.dart';

class EntirePlan extends StatefulWidget {
  const EntirePlan({super.key});

  @override
  State<EntirePlan> createState() => _EntirePlanState();
}

class _EntirePlanState extends State<EntirePlan> {
  late Future<List<PlanModel>> datas;
  DateTime _selectedDay = DateTime.now();
  late final PageController _controller;
  late final DateTime _initialSelectedDay;
  late int _weeknumberInSwipe;
  final int _weekIndexOffset = 5200;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _controller = PageController(initialPage: _weekIndexOffset);

    datas = ApiService.getPlans();
    print(datas);
    print("fetching plans success");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        toolbarHeight: 0.08.sh,
        title: Text(
          "전체 플랜",
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
      body: FutureBuilder(
        future: datas,
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return Column(
              children: [
                Container(
                  alignment: Alignment.centerLeft,
                  padding: EdgeInsets.symmetric(
                    vertical: 0.01.sh,
                    horizontal: 0.04.sw,
                  ),
                  child: Text(
                    "${_selectedDay.month}월",
                    style:
                        TextStyle(fontSize: 20.sp, fontWeight: FontWeight.bold),
                  ),
                ),
                WeeklyDatePicker(
                  selectedDay: _selectedDay, // DateTime
                  changeDay: (value) => setState(() {
                    _selectedDay = value;
                  }),
                  enableWeeknumberText: false,
                  // weeknumberColor: const Color(0xFF57AF87),
                  // weeknumberTextColor: Colors.white,
                  backgroundColor: Colors.white,
                  weekdayTextColor: const Color(0xFF8A8A8A),
                  digitsColor: Color(0xFF8A8A8A),
                  selectedBackgroundColor: const Color(0xFF3BDE7C),
                  weekdays: const [
                    "MON",
                    "TUE",
                    "WED",
                    "THU",
                    "FRI",
                    "SAT",
                    "SUN"
                  ],
                  daysInWeek: 7,
                ),
                SizedBox(
                  height: 0.05.sh,
                ),
                Container(
                  height: 500,
                  child: ListView.builder(
                    itemBuilder: (context, index) {
                      return PlanWidget(
                        id: snapshot.data![index].id,
                        title: snapshot.data![index].title,
                        alarmStartTime: snapshot.data![index].alarmStartTime,
                        alarmEndTime: snapshot.data![index].alarmEndTime,
                        categoryCode: snapshot.data![index].categoryCode,
                        complete: snapshot.data![index].complete,
                      );
                    },
                    itemCount: snapshot.data!.length,
                  ),
                ),
              ],
            );
          } else if (snapshot.hasError) {
            return Text("error");
          }
          // By default, show a loading spinner.
          return Center(child: const CircularProgressIndicator());
        },
      ),
    );
  }
}
