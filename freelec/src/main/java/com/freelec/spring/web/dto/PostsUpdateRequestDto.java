package com.freelec.spring.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter					// get�޼ҵ� ����
@NoArgsConstructor		// ������ ���� (final ������ ������ ����x)
public class PostsUpdateRequestDto {
	
	private String title;
	private String content;
	
	@Builder			// �ش� Ŭ������ ���� ���� Ŭ���� ����
	public PostsUpdateRequestDto(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
}
