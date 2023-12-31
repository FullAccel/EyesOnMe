import 'dart:convert';

import 'package:eom_fe/models/Quotes_model.dart';
import 'package:eom_fe/models/challenge_model.dart';
import 'package:flutter/services.dart';

import '../models/plan_model.dart';

class ApiService {
  static const platform = MethodChannel('samples.flutter.dev/battery');
  static Future<QuotesModel> getQuotes(String jsonString) async {
    final quote = jsonDecode(jsonString);

    return QuotesModel.fromJson(quote);
  }

  static Future<List<PlanModel>> getPlans() async {
    String data = await rootBundle.loadString("assets/json/dummy_plan.json");
    List<PlanModel> ret = [];
    Map<String, dynamic> plans = jsonDecode(data);
    List<dynamic> r = plans['data'];
    //print(plans["data"]);

    for (var plan in r) {
      //print(plan);
      var p = PlanModel.fromJson(plan);
      ret.add(p);
    }

    return ret;
  }

  static List<PlanModel> sortDailyPlans(List<PlanModel> pl) {
    pl.sort((a, b) => a.alarmStartTime.compareTo(b.alarmStartTime));
    print(pl);
    return pl;
  }

  static List<ChallengeModel> sortChallenges(List<ChallengeModel> cl) {
    cl.sort((a, b) => a.deadline.compareTo(b.deadline));

    return cl;
  }

  static List<PlanModel> deleteLastPlans(List<PlanModel> pl) {
    //원래는 DateTime.now().milli~~로.
    pl.removeWhere((element) =>
        DateTime.parse(element.alarmEndTime).millisecondsSinceEpoch <=
        DateTime.parse("2023-08-11 02:30:00").millisecondsSinceEpoch);

    return pl;
  }

  static String DateTimeTo12(String? dt) {
    dt ??= "default";
    //"2023-08-11 23:40:00" -> 11:40 pm
    var tmp = dt.substring(11, 16); // 23:40
    var h = tmp.substring(0, 2);
    if (int.parse(h) == 12) {
      return "12:${tmp.substring(3, 5)} pm";
    } else if (int.parse(h) / 12 < 1) {
      // am
      return "$h:${tmp.substring(3, 5)} am";
    } else {
      return "${int.parse(h) % 12}:${tmp.substring(3, 5)} pm";
    }
  }

  static Future<List<PlanModel>> getTodaysPlan(String yyyymmdd) async {
    print("_getTodaysPlan called");
    List<PlanModel> ret;
    String s = "";
    try {
      s = await platform.invokeMethod('getAllDailyPlansByDate', yyyymmdd);
      print("raw value : $s");
      ret = (jsonDecode(s) as List).map((e) => PlanModel.fromJson(e)).toList();
      ret = ApiService.sortDailyPlans(ret);

      print("todaysPlan.length: ${ret.length}");

      return ret;
    } on PlatformException catch (e) {
      throw Exception("Failed to get Today;s plan");
    }
  }

  static Future<void> _postTodoDataFunc(String jsonString) async {
    try {
      final result =
          await platform.invokeMethod('postTodoDataFunc', jsonString);
      print("alarm: $result");

      // await platform.invokeMethod('testData');
    } on PlatformException catch (e) {
      print("Error: ${e.message}");
    }
  }

  static Future<void> addPlan(String jsonString) async {
    print("jsonString: $jsonString");
    await _postTodoDataFunc(jsonString);
  }
}
