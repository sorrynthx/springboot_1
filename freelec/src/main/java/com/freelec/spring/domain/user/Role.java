package com.freelec.spring.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
	
	// ������ ��ť��Ƽ������ ���� �ڵ忡 �׻� ROLE_�� �տ� �־�� �Ѵ�
	GUEST("ROLE_GUEST", "�մ�"),
	USER("ROLE_USER", "�Ϲ� �����");
	
	private final String key;
	private final String title;
}
