package com.hackerton.domain.category.entity;

import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.dailyPlanGroup.toDo.entity.ToDo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @Column(name = "CATEGORY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoryCode categoryCode;

    private int countByToDo;

    private int countByChallenge;

    private int successToDoCount;

    private int successChallengeCount;

    @OneToMany(mappedBy = "category")
    private List<ToDo> toDoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Builder
    public Category(CategoryCode categoryCode, int countByToDo, int countByChallenge, int successToDoCount, int successChallengeCount, Member member) {
        this.categoryCode = categoryCode;
        this.countByToDo = countByToDo;
        this.countByChallenge = countByChallenge;
        this.successToDoCount = successToDoCount;
        this.successChallengeCount = successChallengeCount;
        this.member = member;
    }

    public void plusCountByToDo()
    {
        this.countByToDo += 1;
    }
    public void plusCountByChallenge()
    {
        this.countByChallenge += 1;
    }
    public void minusCountByToDo()
    {
        this.countByToDo -= 1;
    }
    public void minusCountByChallenge()
    {
        this.countByChallenge -= 1;
    }
    public void completeToDo()
    {
        this.successToDoCount += 1;
    }
    public void cancelCompleteToDo()
    {
        this.successToDoCount -= 1;
    }
    public void completeChallenge(){
        this. successChallengeCount += 1;
    }
    public void cancelCompleteChallenge(){
        this. successChallengeCount -= 1;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
