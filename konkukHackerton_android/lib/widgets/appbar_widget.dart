import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';

class AppbarWidget extends StatelessWidget implements PreferredSizeWidget {
  final title;

  AppbarWidget({
    super.key,
    required this.title,
  });

  @override
  Widget build(BuildContext context) {
    return AppBar(
      toolbarHeight: 0.08.sh,
      title: Text(
        "$title",
        style: TextStyle(
          fontWeight: FontWeight.w600,
          fontSize: 24.sp,
        ),
      ),
      centerTitle: true,
      elevation: 1,
      backgroundColor: Colors.white,
      foregroundColor: Colors.black,
    );
  }

  @override
  // TODO: implement preferredSize
  Size get preferredSize => Size.fromHeight(AppBar().preferredSize.height);
}
