package com.hackerton.security.service;

import com.hackerton.member.domain.Member;
import com.hackerton.member.dto.MemberRepository;
import com.hackerton.security.dto.OAuthAttributes;
import com.hackerton.security.dto.SessionMember;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        Member member = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionMember(member));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(member.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey());
    }


    private Member saveOrUpdate(OAuthAttributes attributes) {
        System.out.println(attributes.getEmail());
        System.out.println("Hello");
        Member member = memberRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        System.out.println(member.getEmail());
        return memberRepository.save(member);
    }
}
