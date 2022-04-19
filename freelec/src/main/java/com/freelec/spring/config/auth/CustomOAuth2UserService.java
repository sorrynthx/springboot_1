package com.freelec.spring.config.auth;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.freelec.spring.config.auth.dto.OAuthAttributes;
import com.freelec.spring.config.auth.dto.SessionUser;
import com.freelec.spring.domain.user.User_;
import com.freelec.spring.domain.user.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
	
	private final UserRepository userRepository;
	private final HttpSession httpSession;
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		
		OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
		OAuth2User oAuth2User = delegate.loadUser(userRequest);
		
		// registrationId 현재 로그인 진행중인 서비스를 구분하는 코드, 네이버 로그인 사용 시, 네이버인지, 구글인지 구분하기 위해 사용
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		// userNameAttributeName. OAuth2 로그인 진행 시, 키가 되는 필드 (Primary Key와 같은 역할)
		// 구글은 지원 O, 네이버,카카오는 미지원
		String userNameAttributeName = userRequest.getClientRegistration()
													.getProviderDetails()
													.getUserInfoEndpoint()
													.getUserNameAttributeName(); 
		
		// OAuthAttributes. OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담은 클래스
		OAuthAttributes attributes = OAuthAttributes
										.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		User_ user = saveOrUpdate(attributes);
		
		// SessionUser. 세션에 사용자 정보를 저장하기 위한 Dto 클래스
		httpSession.setAttribute("user", new SessionUser(user));
		
		
		return new DefaultOAuth2User(
					Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), 
					attributes.getAttributes(), 
					attributes.getNameAttributeKey()
				);
	}

	// 구글 정보 업데이트 시, 정보 수정(이름, 사진)
	private User_ saveOrUpdate(OAuthAttributes attributes) {
		
		User_ user = userRepository.findByEmail(attributes.getEmail())
									.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
									.orElse(attributes.toEntity());
		
		return userRepository.save(user);
	}
	
}
