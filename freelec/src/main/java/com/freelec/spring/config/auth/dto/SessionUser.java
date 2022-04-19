package com.freelec.spring.config.auth.dto;

import com.freelec.spring.domain.user.User_;

import lombok.Getter;

@Getter
public class SessionUser {
	private String name;
	private String email;
	private String picture;
	
	// 인증된 사용자 정보만 필요
	public SessionUser(User_ user) {
		this.name = user.getName();
		this.email = user.getEmail();
		this.picture = user.getPicture();
	}
}
