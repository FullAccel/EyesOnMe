import 'dart:convert';

import 'package:eom_fe/models/Quotes_model.dart';
import 'package:flutter/services.dart';

import '../models/plan_model.dart';

class ApiService {
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
}
