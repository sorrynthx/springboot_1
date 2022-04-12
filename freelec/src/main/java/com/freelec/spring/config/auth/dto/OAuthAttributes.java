package com.freelec.spring.config.auth.dto;

import java.util.Map;

import com.freelec.spring.domain.user.Role;
import com.freelec.spring.domain.user.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OAuthAttributes {
	
	private Map<String, Object> attributes;
	private String nameAttributeKey;
	private String name;
	private String email;
	private String picture;
	
	@Builder
	public OAuthAttributes(Map<String, Object> attributes, 
							String nameAttributeKey, 
							String name, String email, String picture) {
		this.attributes = attributes;
		this.nameAttributeKey = nameAttributeKey;
		this.name = name;
		this.email = email;
		this.picture = picture;
	}
	
	// OAuth2User���� ��ȯ�ϴ� ����� ������ Map�̱� ������ �� �ϳ��ϳ��� ��ȯ�Ѵ�
	public static OAuthAttributes of(String registrationId,
									 String userNameAttributeName,
									 Map<String, Object> attributes) {
		return ofGoogle(userNameAttributeName, attributes);
	}

	
	private static OAuthAttributes ofGoogle(String userNameAttributeName, 
											Map<String, Object> attributes2) {
		
		return OAuthAttributes.builder()
								.name((String) attributes2.get("name"))
								.email((String) attributes2.get("email"))
								.picture((String) attributes2.get("picture"))
								.attributes(attributes2)
								.nameAttributeKey(userNameAttributeName)
								.build();
	}
	
	// User ��ƼƼ ����. OAuthAttributes���� ��ƼƼ�� �����ϴ� ������ ó�� ������ ��. ���� ��, �⺻ ������ GUEST�� ���
	public User toEntity() {
		return User.builder()
					.name(name)
					.email(email)
					.picture(picture)
					.role(Role.GUEST)
					.build();
	}
	
}