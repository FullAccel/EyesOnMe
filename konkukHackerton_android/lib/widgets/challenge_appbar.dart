import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class ChallengeAppbar extends StatefulWidget {
  ChallengeAppbar({
    super.key,
  });

  @override
  State<ChallengeAppbar> createState() => _ChallengeAppbarState();
}

class _ChallengeAppbarState extends State<ChallengeAppbar> {
  final isSelected = [true, false];

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 0.07.sh,
      decoration: BoxDecoration(
        border: Border(
          bottom: BorderSide(
            width: 0.2,
            color: Colors.grey,
          ),
        ),
      ),
      child: Container(
        height: 0.08.sh,
        child: ToggleButtons(
          renderBorder: false,
          fillColor: Theme.of(context).scaffoldBackgroundColor,
          selectedColor: Theme.of(context).primaryColor,
          selectedBorderColor: Colors.white,
          color: Colors.grey,
          constraints: BoxConstraints(
            maxHeight: 0.1.sh,
          ),
          children: [
            Container(
              child: Text(
                "챌린지",
                style: TextStyle(fontSize: 20.sp),
              ),
              margin: const EdgeInsets.symmetric(horizontal: 70.0),
            ),
            Container(
              child: Text(
                "검증",
                style: TextStyle(fontSize: 20.sp),
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
    );
  }
}
