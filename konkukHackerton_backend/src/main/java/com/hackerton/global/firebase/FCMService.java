package com.hackerton.global.firebase;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.hackerton.domain.member.entity.Member;
import com.hackerton.domain.member.entity.MemberRepository;
import com.hackerton.global.error.ErrorCode;
import com.hackerton.global.error.exception.EntityNotFoundException;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import com.hackerton.global.firebase.exception.FirebaseTokenNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.hackerton.global.error.ErrorCode.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FCMService {

    private final FirebaseMessaging firebaseMessaging;
    private final MemberRepository memberRepository;
    public boolean sendNotificationMessageByToken(FCMNotificationRequestDto requestDto) throws FirebaseMessagingException {
        Member member = memberRepository.findById(requestDto.getTargetMemberId())
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 Id에 멤버가 존재하지 않습니다 : " + requestDto.getTargetMemberId()));

        if (member.getFirebaseToken().equals(""))
            throw new FirebaseTokenNotFoundException(TOKEN_NOT_FOUND, "해당 Id의 멤버의 토큰이 존재하지 않습니다 : " + member.getId());

        log.debug("유저 토큰 {}",member.getFirebaseToken());

        Notification notification = Notification.builder()
                .setTitle(requestDto.getTitle())
                .setBody(requestDto.getBody())
                .build();

        Message message = Message.builder()
                .setToken(member.getFirebaseToken())
                .setNotification(notification)
                .build();

        firebaseMessaging.send(message);

        return true;
    }
}
