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

@RequiredArgsConstructor			// final로 선언된 모든 필드를 인자값으로 하는 생성자 생성
@RestController						// 컨트롤러를 JSON으로 반환하는 컨트롤러 (@ResponsBody를 한번에 사용).
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
