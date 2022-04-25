package com.freelec.spring.config.auth.dto;

import java.io.Serializable;

import com.freelec.spring.domain.user.User_;

import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
