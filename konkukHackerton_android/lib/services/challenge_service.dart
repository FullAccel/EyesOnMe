class ChallengeService {
  static String getInterval(String code, int count) {
    switch (code) {
      case "VI01":
        return "하루에 $count번";
      case "VI07":
        return "일주일에 $count번";
      case "VI14":
        return "이주일에 $count번";
      case "VI30":
        return "한 달에 $count번";
      case "VI00":
        return "설정 안 함";
      default:
        return "null";
    }
  }

  static Map<int, dynamic> intervalCodeOf = {
    0: "VI01",
    1: "VI07",
    2: "VI14",
    3: "VI30",
    4: "VI00",
  };
}
