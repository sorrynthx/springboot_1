package com.freelec.spring.config.auth.dto;

import com.freelec.spring.domain.user.User_;

import lombok.Getter;

@Getter
public class SessionUser {
	private String name;
	private String email;
	private String picture;
	
	// ������ ����� ������ �ʿ�
	public SessionUser(User_ user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
