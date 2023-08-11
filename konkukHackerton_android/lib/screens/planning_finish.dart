import 'package:eom_fe/models/Quotes_model.dart';
import 'package:flutter/material.dart';
import 'package:flutter_screenutil/flutter_screenutil.dart';
import 'package:get/get.dart';

import '../services/api_service.dart';
import '../widgets/oval_widget.dart';

class PlanningFinish extends StatefulWidget {
  const PlanningFinish({super.key});

  @override
  State<PlanningFinish> createState() => _PlanningFinishState();
}

class _PlanningFinishState extends State<PlanningFinish> {
  late Future<QuotesModel> quote;
  String jsonString = '''
  {
    "quote": "계획 없는 성공은 한낱 꿈에 불과하다.",
    "name": "생텍쥐페리"
  }
  ''';

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    quote = ApiService.getQuotes(jsonString);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        alignment: AlignmentDirectional.center,
        children: [
          OvalWidget(),
          Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              FutureBuilder(
                future: quote,
                builder: (context, snapshot) {
                  if (snapshot.hasData) {
                    return Column(
                      children: [
                        Text(
                          "${snapshot.data?.quote}",
                          style: TextStyle(
                            fontSize: 24.sp,
                            fontWeight: FontWeight.bold,
                          ),
                        ),
                        SizedBox(height: 0.02.sh),
                        Text(
                          "-${snapshot.data?.name}-",
                          style: TextStyle(fontSize: 18.sp),
                        ),
                      ],
                    );
                  } else if (snapshot.hasError) {
                    return Text('${snapshot.error}');
                  }

                  // By default, show a loading spinner.
                  return Center(child: const CircularProgressIndicator());
                },
              ),
            ],
          ),
          Positioned(
            bottom: 0.05.sh,
            child: FilledButton(
              onPressed: () => Get.toNamed('/plan/finish'),
              style: FilledButton.styleFrom(
                backgroundColor: Color(0xFF3BDE7C),
                textStyle: TextStyle(fontWeight: FontWeight.bold),
                fixedSize: Size(0.8.sw, 0.05.sh),
                shadowColor: Colors.grey,
                elevation: 2,
              ),
              child: Text("확인"),
            ),
          ),
        ],
      ),
    );
  }
}
