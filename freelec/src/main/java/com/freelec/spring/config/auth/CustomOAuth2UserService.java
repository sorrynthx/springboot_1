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
		
		// registrationId ���� �α��� �������� ���񽺸� �����ϴ� �ڵ�, ���̹� �α��� ��� ��, ���̹�����, �������� �����ϱ� ���� ���
		String registrationId = userRequest.getClientRegistration().getRegistrationId();
		
		// userNameAttributeName. OAuth2 �α��� ���� ��, Ű�� �Ǵ� �ʵ� (Primary Key�� ���� ����)
		// ������ ���� O, ���̹�,īī���� ������
		String userNameAttributeName = userRequest.getClientRegistration()
													.getProviderDetails()
													.getUserInfoEndpoint()
													.getUserNameAttributeName(); 
		
		// OAuthAttributes. OAuth2UserService�� ���� ������ OAuth2User�� attribute�� ���� Ŭ����
		OAuthAttributes attributes = OAuthAttributes
										.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
		
		User_ user = saveOrUpdate(attributes);
		
		// SessionUser. ���ǿ� ����� ������ �����ϱ� ���� Dto Ŭ����
		httpSession.setAttribute("user", new SessionUser(user));
		
		
		return new DefaultOAuth2User(
					Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())), 
					attributes.getAttributes(), 
					attributes.getNameAttributeKey()
				);
	}

	// ���� ���� ������Ʈ ��, ���� ����(�̸�, ����)
	private User_ saveOrUpdate(OAuthAttributes attributes) {
		
		User_ user = userRepository.findByEmail(attributes.getEmail())
									.map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
									.orElse(attributes.toEntity());
		
		return userRepository.save(user);
	}
	
}
