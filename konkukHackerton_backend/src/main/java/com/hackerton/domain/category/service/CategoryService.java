package com.hackerton.domain.category.service;

import com.hackerton.domain.category.dto.CategoryResponseDto;
import com.hackerton.domain.category.entity.Category;
import com.hackerton.domain.category.entity.CategoryCode;
import com.hackerton.domain.category.entity.CategoryRepository;
import com.hackerton.domain.challengeGroup.challenge.entity.Challenge;
import com.hackerton.domain.dailyPlanGroup.dailyPlan.entity.DailyPlan;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.exception.EntityAlreadyExistException;
import com.hackerton.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hackerton.global.error.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    private final int MIN_CATEGORY_REQUIRED = 3;
    private final int FIRST = 0;
    private final int THIRD = 2;

    @Transactional
    public Category save(String categoryCode, Object dailyPlanOrChallenge, Member member) {

        Category category = categoryRepository.findByMemberIdAndCategoryCode(member.getId(), CategoryCode.findByCode(categoryCode))
                .orElse(Category.builder()
                        .categoryCode(CategoryCode.findByCode(categoryCode))
                        .build());

        if(dailyPlanOrChallenge instanceof DailyPlan)
            category.plusCountByToDo();
        else if(dailyPlanOrChallenge instanceof Challenge)
            category.plusCountByChallenge();
        if(member == null)
            throw new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 데일리 플래이나 챌린지가 유저에 할당되어 있지 않습니다");

        category.setMember(member);
        return categoryRepository.save(category);
    }

    @Transactional
    public Category update(Category category, String afterCategoryCode, Object dailyPlanOrChallenge) {
        Member member = category.getMember();

        Category oldCategory = categoryRepository.findByMemberIdAndCategoryCode(member.getId(), category.getCategoryCode())
                .orElseThrow(() -> new EntityNotFoundException(CATEGORY_NOT_FOUND, "해당 유저 Id( " + member.getId() + " )는 "
                        + category.getCategoryCode() + "에 해당하는 카테고리를 가지고 있지 않습니다."));

        Category newCategory = Category.builder()
                .categoryCode(CategoryCode.findByCode(afterCategoryCode))
                .build();

        if(dailyPlanOrChallenge instanceof DailyPlan)
        {
            newCategory.plusCountByToDo();
            oldCategory.minusCountByToDo();
        }
        else if(dailyPlanOrChallenge instanceof Challenge)
        {
            newCategory.plusCountByChallenge();
            oldCategory.minusCountByChallenge();
        }


        newCategory.setMember(member);
        return categoryRepository.save(newCategory);
    }

    public List<CategoryResponseDto> getCategoryListByMemberId(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "카테고리에 연결된 해당 멤버가 존재하지 않습니다 : " + memberId));

        List<Category> categoryList = categoryRepository.findAllByMemberId(memberId);
        if(categoryList.size() == 0)
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND, "해당 유저는 데일리플랜을 작성하지 않아 카테고리가 없습니다");

        return categoryList.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<CategoryResponseDto> getBest3DailyPlanCategory(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "카테고리에 연결된 해당 멤버가 존재하지 않습니다 : " + memberId));

        List<Category> categoryList = categoryRepository.findAllByMemberId(memberId);

        categoryList.sort((x,y) -> Double.compare(computeDailyPlanAchievementRate(x),computeDailyPlanAchievementRate(y)));

        if(categoryList.size() < MIN_CATEGORY_REQUIRED)
            return categoryList.stream()
                    .map(CategoryResponseDto::new)
                    .collect(Collectors.toList());

        return categoryList.subList(FIRST,THIRD).stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<CategoryResponseDto> getWorst3DailyPlanCategory(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "카테고리에 연결된 해당 멤버가 존재하지 않습니다 : " + memberId));

        List<Category> categoryList = categoryRepository.findAllByMemberId(memberId);

        categoryList.sort((x,y) -> -Double.compare(computeDailyPlanAchievementRate(x),computeDailyPlanAchievementRate(y)));

        if(categoryList.size() < MIN_CATEGORY_REQUIRED)
            return categoryList.stream()
                    .map(CategoryResponseDto::new)
                    .collect(Collectors.toList());

        return categoryList.subList(FIRST,THIRD).stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

    private double computeDailyPlanAchievementRate(Category category) {
        return ((double) category.getSuccessToDoCount()) / category.getCountByToDo();
    }

    private double computeChallengeAchievementRate(Category category) {
        return ((double) category.getSuccessChallengeCount()) / category.getCountByChallenge();
    }
}
