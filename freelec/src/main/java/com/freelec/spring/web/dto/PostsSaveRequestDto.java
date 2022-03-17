package com.freelec.spring.web.dto;

import com.freelec.spring.domain.posts.Posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get�޼ҵ� ����
@NoArgsConstructor		// ������ ���� (final ������ ������ ����x)
public class PostsSaveRequestDto {
	
	private String title;
	private String content;
	private String author;
	
	@Builder			// �ش� Ŭ������ ���� ���� Ŭ���� ����
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