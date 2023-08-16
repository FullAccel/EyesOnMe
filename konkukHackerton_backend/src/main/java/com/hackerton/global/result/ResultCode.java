package com.hackerton.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // Member
    MEMBER_SAVE_OR_UPDATE_SUCCESS(200, "M001", "회원 정보를 DB에 update 완료하였습니다"),
    GET_USERPROFILE_SUCCESS(200, "M002", "회원 프로필을 조회하였습니다."),
    UPLOAD_MEMBER_IMAGE_SUCCESS(200, "M006", "회원 이미지를 등록하였습니다."),
    DELETE_MEMBER_IMAGE_SUCCESS(200, "M007", "회원 이미지를 삭제하였습니다."),
    GET_EDIT_PROFILE_SUCCESS(200, "M008", "회원 프로필 수정정보를 조회하였습니다."),
    EDIT_PROFILE_SUCCESS(200, "M009", "회원 프로필을 수정하였습니다."),
    LOGOUT_SUCCESS(200, "M020", "로그아웃하였습니다."),


    // Follow
    FOLLOW_SUCCESS(200,"F001", "팔로우를 성공했습니다."),
    FOLLOW_REQUEST_SUCCESS(200, "F002", "팔로우 요청에 성공했습니다"),
    UNFOLLOW_SUCCESS(200, "F003", "언팔로우를 성공했습니다"),
    GET_FOLLOWING_LIST_SUCCESS(200, "F004", "팔로잉 목록 조회에 성공했습니다"),
    GET_FOLLOWER_LIST_SUCCESS(200, "F005", "팔로워 목록 조회에 성공했습니다"),


    //FireBase
    NOTIFICATION_REQUEST_SUCCESS(200, "FB001", "푸쉬 알림 요청에 성공했습니다"),
    SAVE_OR_UPDATE_FIREBASE_TOKEN_SUCCESS(200, "FB002", "파이어베이스 토큰 저장에 성공했습니다"),

    // DailyPlan
    DAILYPLAN_SAVE_SUCCESS(200, "D001", "데일리 플랜를 저장했습니다"),
    GET_DAILYPLAN_SUCCESS(200, "D002", "데일리 플랜을 조회하였습니다"),
    DELETE_DAILPLAN_SUCCESS(200, "D003", "데일리 플랜을 삭제하였습니다"),
    GET_MONTH_DAILYPLAN_SUCCESS(200, "D004", "월 별 데일리 플랜을 조회하였습니다"),

    //ToDo
    SAVE_TODO_SUCCESS(200, "T001", "투두를 저장했습니다"),
    GET_TODO_SUCCESS(200, "T002", "투두를 조회했습니다"),
    DELETE_TODO_SUCCESS(200, "T003", "투두를 삭제하였습니다"),
    GET_TODO_ALL_SUCCESS(200, "T004", "해당 데일리 플랜의 모든 투두를 조회했습니다"),
    UPDATE_TODO_SUCCESS(200, "T005", "투두를 업데이트 했습니다"),
    COMPLETE_TODO(200,"T006","투두 성공을 반영했습니다"),
    FAIL_TODO(200, "T007", "투두 실패를 반영했습니다"),

    //Category
    GET_CATEGORY_LIST_SUCCESS(200, "CT001", "카테고리를 모두 조회하였습니다"),
    GET_BEST3_TODO_SUCCESS(200, "CT002", "달성률 베스트 3 투두를 조회하였습니다"),
    GET_WORST3_TODO_SUCCESS(200, "CT002", "달성률 워스트 3 투두를 플랜을 조회하였습니다"),

    //Challenge
    SAVE_CHALLENGE_SUCCESS(200, "C001", "챌린지를 저장했습니다"),
    SAVE_VALIDATOR_SUCCESS(200, "C002", "검증자를 챌린지에 등록했습니다"),
    GET_CHALLENGE_SUCCESS(200,"C003", "챌린지를 조회했습니다"),
    GET_ALL_CHALLENGE_SUCCESS(200,"C004", "해당 유저의 챌린지를 모두 조회했습니다"),
    UPDATE_CHALLENGE_SUCCESS(200, "C005", "챌린지를 업데이트 했습니다"),

    //Validation
    VALIDATOR_ENTRY_SUCCESS(200, "V001", "검증가가 챌린지에 입장했습니다"),
    DELETE_VALIDATION_SUCCESS(200, "V002", "검증자를 삭제 했습니다"),
    CHECK_VALIDATION_SUCCESS(200, "V003", "검증을 완료했습니다"),


    //Proof
    PROOF_UPLOAD_SUCCESS(200,"P001","검증 사진 등록에 성공하였습니다."),
    GET_PROOF_SUCCESS(200,"P002","해당 날짜의 Proof를 조회했습니다"),
    GET_ALL_PROOF_SUCCESS(200,"P003","챌린지의 Proof를 모두 조회했습니다"),
    UPDATE_PROOF_SUCCESS(200, "P004", "Proof를 업데이트 하였습니다"),
    DELETE_PROOF_SUCCESS(200, "P005", "Proof를 삭제 하였습니다"),
    ;

    private final int status;
    private final String code;
    private final String message;
}
