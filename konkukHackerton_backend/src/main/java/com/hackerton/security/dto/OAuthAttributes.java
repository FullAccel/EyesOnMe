package com.hackerton.security.dto;

import com.hackerton.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String profileUrl;

    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        this.attributes = attributes;
        this.nameAttributeKey = nameAttributeKey;
        this.name = name;
        this.email = email;
        this.profileUrl = picture;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)){
            return ofNaver(userNameAttributeName, attributes);
        }
        else if("kakao".equals(registrationId)){
            return ofKakao(userNameAttributeName, attributes);
        }

        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entrySet : attributes.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");

        Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");

        String kakaoEmail = "";
        System.out.println(kakaoAccount.get("is_email_valid"));
        if((boolean) kakaoAccount.get("is_email_valid"))
            kakaoEmail = (String) kakaoAccount.get("email");
        System.out.println("kakaoEmail = " + kakaoEmail);



        return OAuthAttributes.builder()
                .name((String) profile.get("nickname"))
                .email(kakaoEmail)
                .picture((String) profile.get("profile_image_url"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .picture((String) response.get("profile_image"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        for (Map.Entry<String, Object> entrySet : attributes.entrySet()) {
            System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
        }
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .picture((String) attributes.get("picture"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .profileUrl(profileUrl)
                .build();
    }
}
