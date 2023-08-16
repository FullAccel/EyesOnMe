class QuotesModel {
  final String quote, name;

  QuotesModel.fromJson(Map<String, dynamic> json)
      : quote = json['quote'],
        name = json['name'];
}
