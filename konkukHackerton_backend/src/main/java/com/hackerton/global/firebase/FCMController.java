package com.hackerton.global.firebase;

import com.google.firebase.messaging.FirebaseMessagingException;
import com.hackerton.global.firebase.dto.FCMNotificationRequestDto;
import com.hackerton.global.result.ResultCode;
import com.hackerton.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.hackerton.global.result.ResultCode.*;

@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class FCMController {

    private final FCMService fcmService;

    @PostMapping("")
    public ResponseEntity<ResultResponse> sendRequestNotification(@RequestBody FCMNotificationRequestDto requestDto) throws FirebaseMessagingException {
        boolean isSendComplete = fcmService.sendNotificationMessageByToken(requestDto);

        return ResponseEntity.ok(ResultResponse.of(NOTIFICATION_REQUEST_SUCCESS, isSendComplete));
    }



}
