import 'package:eom_fe/models/member_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class UserInfoWidget extends StatefulWidget {
  final MemberModel memberData;
  UserInfoWidget({
    super.key,
    required this.memberData,
  });

  @override
  State<UserInfoWidget> createState() => _UserInfoWrapperState();
}

class _UserInfoWrapperState extends State<UserInfoWidget> {
  final isSelected = [true, false];

  @override
  Widget build(BuildContext context) {
    print("widget.memberData : ${widget.memberData.email}");
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
              mainAxisAlignment: MainAxisAlignment.start,
              children: [
                SizedBox(width: 0.1.sw),
                Image.asset(
                  "assets/images/dummy_profile_image.png",
                  scale: 0.85,
                ),
                SizedBox(width: 0.1.sw),
                Column(
                  mainAxisAlignment: MainAxisAlignment.end,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Row(
                      children: [
                        Text(
                          widget.memberData.name,
                          style: TextStyle(
                            fontSize: 26.sp,
                            fontWeight: FontWeight.w600,
                          ),
                        ),
                      ],
                    ),
                    SizedBox(height: 0.03.sh),
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
              selectedColor: Theme.of(context).primaryColor,
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
