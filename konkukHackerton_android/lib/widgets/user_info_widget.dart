import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class UserInfoWidget extends StatefulWidget {
  const UserInfoWidget({super.key});

  @override
  State<UserInfoWidget> createState() => _UserInfoWrapperState();
}

class _UserInfoWrapperState extends State<UserInfoWidget> {
  final isSelected = [true, false];

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 0.235.sh,
      decoration: BoxDecoration(
        color: Colors.white,
        border: Border(
          bottom: BorderSide(
            width: 0.2,
            color: Colors.grey,
          ),
        ),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.end,
        children: [
          Flexible(
            flex: 2,
            child: Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Image.asset(
                  "assets/images/dummy_profile_image.png",
                  scale: 0.85,
                ),
                SizedBox(width: 20),
                Column(
                  mainAxisAlignment: MainAxisAlignment.end,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Text(
                          "김이름",
                          style: TextStyle(
                            fontSize: 26.sp,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                        SizedBox(width: 8),
                        Column(
                          children: [
                            SizedBox(
                              height: 4,
                            ),
                            Icon(Icons.settings),
                          ],
                        )
                      ],
                    ),
                    SizedBox(height: 5),
                    Row(
                      children: [
                        Padding(
                          padding: const EdgeInsets.only(top: 3),
                          child: Text(
                            "100",
                            style: TextStyle(fontSize: 16.sp),
                          ),
                        ),
                        SizedBox(width: 3),
                        Text(
                          "팔로잉",
                          style: TextStyle(fontSize: 16.sp),
                        ),
                        SizedBox(width: 24),
                        Padding(
                          padding: const EdgeInsets.only(top: 3),
                          child: Text(
                            "100",
                            style: TextStyle(fontSize: 16.sp),
                          ),
                        ),
                        SizedBox(width: 3),
                        Text(
                          "팔로워",
                          style: TextStyle(fontSize: 16.sp),
                        ),
                      ],
                    ),
                    SizedBox(height: 15),
                  ],
                ),
                SizedBox(
                  width: 30,
                ),
              ],
            ),
          ),
          SizedBox(height: 10),
          Flexible(
            flex: 1,
            child: ToggleButtons(
              renderBorder: false,
              borderColor: Colors.white,
              fillColor: Colors.white,
              selectedColor: Color(0xFF3BDE7C),
              selectedBorderColor: Colors.white,
              color: Colors.grey,
              children: [
                Container(
                  child: Text(
                    "데일리",
                    style: TextStyle(fontSize: 18.sp),
                  ),
                  margin: const EdgeInsets.symmetric(horizontal: 70.0),
                ),
                Container(
                  child: Text(
                    "챌린지",
                    style: TextStyle(fontSize: 18.sp),
                  ),
                  margin: const EdgeInsets.symmetric(horizontal: 70.0),
                ),
              ],
              isSelected: isSelected,
              onPressed: (int index) {
                setState(
                  () {
                    for (int buttonIndex = 0;
                        buttonIndex < isSelected.length;
                        buttonIndex++) {
                      if (buttonIndex == index) {
                        isSelected[buttonIndex] = true;
                      } else {
                        isSelected[buttonIndex] = false;
                      }
                    }
                  },
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}
