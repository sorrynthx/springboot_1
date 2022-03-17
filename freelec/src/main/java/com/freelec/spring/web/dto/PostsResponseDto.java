package com.freelec.spring.web.dto;

import com.freelec.spring.domain.posts.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get�޼ҵ� ����
@NoArgsConstructor		// ������ ���� (final ������ ������ ����x)
public class PostsResponseDto {
	
	private Long id;
	private String title;
	private String content;
	private String author;
	
	public PostsResponseDto(Posts entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.content = entity.getTitle();
		this.author = entity.getAuthor();
	}
	
}
