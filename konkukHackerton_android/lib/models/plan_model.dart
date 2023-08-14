class PlanModel {
  final int id;
  final String title, alarmStartTime, alarmEndTime, categoryCode;
  final bool complete;

  PlanModel.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        title = json['title'],
        alarmStartTime = json['alarmStartTime'],
        alarmEndTime = json['alarmEndTime'],
        categoryCode = json['categoryCode'],
        complete = json['_complete'];
}
