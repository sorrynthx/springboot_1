package com.freelec.spring.web.dto;

import com.freelec.spring.domain.posts.Posts;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get皋家靛 积己
@NoArgsConstructor		// 积己磊 积己 (final 绝栏搁 积己磊 器窃x)
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
