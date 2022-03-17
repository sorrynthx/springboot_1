package com.freelec.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get메소드 생성
@NoArgsConstructor		// 생성자 생성 (final 없으면 생성자 포함x)
public class PostsUpdateRequestDto {
	
	private String title;
	private String content;
	
	@Builder			// 해당 클래스의 빌더 패턴 클래스 생성
	public PostsUpdateRequestDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
}
