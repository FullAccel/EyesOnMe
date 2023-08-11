import 'package:get/get.dart';

import '../models/plan_model.dart';

class PlanController extends GetxController {
  // static PlanController get to => Get.find();
  // var list = <Map<String, dynamic>>[].obs;
  //
  // // yyyymmdd 형식
  // var today = "".obs;
  //
  // @override
  // void onInit() {
  //   // TODO: implement onInit
  //   super.onInit();
  //   getPlans();
  // }
  //
  // void setToday(String d) {
  //   today(d);
  // }
  //
  // void getPlans() async {
  //   if (Get.context != null) {
  //     String data = await DefaultAssetBundle.of(Get.context!)
  //         .loadString("assets/json/dummy_plan.json");
  //     list(jsonDecode(data).cast<Map<String, dynamic>>().toList());
  //   } else {
  //     Future.delayed(Duration(milliseconds: 200), getPlans);
  //   }
  // }
  final plan = PlanModel().obs;
}
