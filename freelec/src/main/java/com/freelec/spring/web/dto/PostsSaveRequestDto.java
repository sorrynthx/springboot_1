package com.freelec.spring.web.dto;

import com.freelec.spring.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get메소드 생성
@NoArgsConstructor		// 생성자 생성 (final 없으면 생성자 포함x)
public class PostsSaveRequestDto {
	
	private String title;
	private String content;
	private String author;
	
	@Builder			// 해당 클래스의 빌더 패턴 클래스 생성
	public PostsSaveRequestDto(String title, String content, String author) {
		this.title = title;
		this.content = content;
		this.author = author;
	}
	
	public Posts toEntity() {
		return Posts.builder()
				.title(title)
				.content(content)
				.author(author)
				.build();
	}
}
