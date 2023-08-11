package com.hackerton.global;

import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.category.entity.CategoryCode;
import com.hackerton.domain.category.entity.CategoryRepository;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlanRepository;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DummyDataInitializer {

    private final MemberRepository memberRepository;
    private final DailyPlanRepository dailyPlanRepository;
    private final ToDoRepository toDoRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void init() {
        Member member = Member.builder()
                .name("John Doe")
                .email("john@example.com")
                .profileUrl("profile.jpg")
                .planSuccessCount(10)
                .challengeSuccessCount(5)
                .firebaseToken("firebaseToken")
                .build();
        Member save = memberRepository.save(member);

        DailyPlan dailyPlan = DailyPlan.builder()
                .member(save)
                .yearMonth("202308")
                .date(05)
                .build();
        DailyPlan save1 = dailyPlanRepository.save(dailyPlan);

        Category category = Category.builder()
                .categoryCode(CategoryCode.DAILY)
                .countByToDo(5)
                .countByChallenge(5)
                .successToDoCount(3)
                .successChallengeCount(4)
                .member(member)
                .build();

        Category save2 = categoryRepository.save(category);

        ToDo todo = ToDo.builder()
                .title("title")
                .alarmStartTime("08:30")
                .alarmEndTime("23:30")
                .dailyPlan(save1)
                .category(save2)
                .build();

        toDoRepository.save(todo);
    }
}
