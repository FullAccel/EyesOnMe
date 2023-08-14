class PlanModel {
  final int id;
  final String title,
      alarmStartTime,
      alarmEndTime,
      categoryCode,
      toDoStatusCode;

  PlanModel.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        title = json['title'],
        toDoStatusCode = json['toDoStatusCode'],
        alarmStartTime = json['alarmStartTime'],
        alarmEndTime = json['alarmEndTime'],
        categoryCode = json['categoryCode'];
}
