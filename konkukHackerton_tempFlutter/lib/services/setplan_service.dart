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
}
