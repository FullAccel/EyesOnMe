class ProofResponseDataModel {
  final int proofId;
  final String date, writing;
  final List<dynamic> images;

  ProofResponseDataModel.fromJson(Map<String, dynamic> json)
      : proofId = json["proofId"],
        date = json["date"],
        writing = json["writing"],
        images = json["images"];
}
