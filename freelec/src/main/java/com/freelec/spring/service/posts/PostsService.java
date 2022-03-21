package com.freelec.spring.service.posts;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.freelec.spring.domain.posts.Posts;
import com.freelec.spring.domain.posts.PostsRepository;
import com.freelec.spring.web.dto.PostsListResponseDto;
import com.freelec.spring.web.dto.PostsResponseDto;
import com.freelec.spring.web.dto.PostsSaveRequestDto;
import com.freelec.spring.web.dto.PostsUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor		// final로 선언된 모든 필드를 인자값으로 하는 생성자 생성
@Service
public class PostsService {
	
	private final PostsRepository postsRepository;
	
	@Transactional
	public Long save(PostsSaveRequestDto requestDto) {
		return postsRepository.save(requestDto.toEntity()).getId();
	}
	
	@Transactional
	public Long update(Long id, PostsUpdateRequestDto requestDto) {
		Posts posts = postsRepository.findById(id)
									.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
		
		posts.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}
	
	@Transactional
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
									.orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
		
		return new PostsResponseDto(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		/*
		 *  postsRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto 변환 -> List로 반환
		 * 
		 * */
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)		// .map(posts -> new PostsListResponseDto(posts))
				.collect(Collectors.toList());		 
	}

}
