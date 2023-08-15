class SetPlanService {
  static List<String> soundSettings = [
    "알림음",
    "진동",
    "무음",
  ];

  static var alarmSounds = '''
[
    [
        "노래1",
        "노래2",
        "노래3",
        "노래4",
        "노래5",
        "노래6",
        "노래7",
        "노래8",
        "노래9"
    ]
]
    ''';

  static var repeats = '''
[
    [
        "없음",
        "5분 마다",
        "10분 마다",
        "20분 마다",
        "30분 마다",
        "1시간 마다",
        "2시간 마다"
    ]
]
    ''';

  static var missions = '''
[
    [
        "따라 쓰기",
        "수학 문제 풀기",
        "50번 흔들기"
    ]
]
    ''';

  static Map<String, String> categoryToCode = {
    "일상": "C001",
    "업무/학습": "C002",
    "모임/약속": "C003",
    "건강/운동": "C004",
    "여가/오락": "C005",
    "재정관리": "C006",
    "기타": "C007",
  };
  static Map<String, String> codeToCategory = {
    "C001": "일상",
    "C002": "업무/학습",
    "C003": "모임/약속",
    "C004": "건강/운동",
    "C005": "여가/오락",
    "C006": "재정관리",
    "C007": "기타",
  };
}
