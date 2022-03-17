package com.freelec.spring.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.freelec.spring.service.posts.PostsService;
import com.freelec.spring.web.dto.PostsResponseDto;
import com.freelec.spring.web.dto.PostsSaveRequestDto;
import com.freelec.spring.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor			// final�� ����� ��� �ʵ带 ���ڰ����� �ϴ� ������ ����
@RestController						// ��Ʈ�ѷ��� JSON���� ��ȯ�ϴ� ��Ʈ�ѷ� (@ResponsBody�� �ѹ��� ���).
public class PostsApiController {
	
	private final PostsService postsService;
	
	@PostMapping("/api/v1/posts")
	public Long save(@RequestBody PostsSaveRequestDto requestDto) {
		return postsService.save(requestDto);
	}
	
	@PostMapping("/api/v1/posts/{id}")
	public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
		return postsService.update(id, requestDto);
	}
	
	@GetMapping("/api/v1/posts/{id}")
	public PostsResponseDto findById(@PathVariable Long id) {
		return postsService.findById(id);
	}
}
