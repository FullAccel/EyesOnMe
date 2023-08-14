class MemberModel {
  // data class MemberData(
  // val id: Int,
  // val name: String,
  // val email: String,
  // val profileUrl: String,
  // val planSuccessCount: Int,
  // val challengeSuccessCount: Int,
  // var firebaseToken: String = ""
  // )

  final int id, planSuccessCount, challengeSuccessCount;
  final String name, email, profileUrl, firebaseToken;

  MemberModel.fromJson(Map<String, dynamic> json)
      : id = json['id'],
        name = json["name"],
        email = json['email'],
        profileUrl = json["profileUrl"],
        planSuccessCount = json["planSuccessCount"],
        challengeSuccessCount = json["challengeSuccessCount"],
        firebaseToken = json["firebaseToken"];
}

// {
// "id": 123,
// "name": "John Doe",
// "email": "johndoe@example.com",
// "profileUrl": "https://example.com/johndoe/profile.jpg",
// "planSuccessCount": 10,
// "challengeSuccessCount": 5,
// "firebaseToken": "your_firebase_token_here"
// }
