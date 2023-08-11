package com.hackerton.domain.category.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByMemberId(Long memberId);
    Optional<Category> findByMemberIdAndCategoryCode(Long memberId, CategoryCode categoryCode);

    boolean existsByMemberIdAndCategoryCode(Long memberId, CategoryCode categoryCode);

}
