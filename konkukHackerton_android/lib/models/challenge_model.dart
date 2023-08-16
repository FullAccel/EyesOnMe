class ChallengeModel {
  final int id,
      totalProofNum,
      currentSuccessNum,
      achievementRate,
      validationCountPerInterval;
  final String title,
      deadline,
      challengeStatusCode,
      validationIntervalCode,
      categoryCode;
  final List<Map<String, dynamic>> validatorList;

  ChallengeModel.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        title = json['title'],
        deadline = json['deadline'],
        totalProofNum = json['totalProofNum'],
        currentSuccessNum = json['currentSuccessNum'],
        achievementRate = json['achievementRate'],
        challengeStatusCode = json['challengeStatusCode'],
        validationIntervalCode = json['validationIntervalCode'],
        validationCountPerInterval = json['validationCountPerInterval'],
        categoryCode = json['categoryCode'],
        validatorList = json['validatorList'];
}

// {
// "id": 1,
// "title": "Challenge 1",
// "deadline": "2023-08-31",
// "totalProofNum": 6,
// "currentSuccessNum": 0,
// "achievementRate": 0,
// "challengeStatusCode": "P",
// "validationIntervalCode": "VI07",
// "validationCountPerInterval": 3,
// "categoryCode": "C001",
// "validatorList": ["김세연", "박세준", "서지명"]
// },
