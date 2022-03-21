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

@RequiredArgsConstructor		// final�� ����� ��� �ʵ带 ���ڰ����� �ϴ� ������ ����
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
									.orElseThrow(() -> new IllegalArgumentException("�ش� �Խñ��� �����ϴ�. id=" + id));
		
		posts.update(requestDto.getTitle(), requestDto.getContent());
		
		return id;
	}
	
	@Transactional
	public PostsResponseDto findById(Long id) {
		Posts entity = postsRepository.findById(id)
									.orElseThrow(() -> new IllegalArgumentException("�ش� �Խñ��� �����ϴ�. id="+id));
		
		return new PostsResponseDto(entity);
	}
	
	@Transactional(readOnly = true)
	public List<PostsListResponseDto> findAllDesc() {
		/*
		 *  postsRepository ����� �Ѿ�� Posts�� Stream�� map�� ���� PostsListResponseDto ��ȯ -> List�� ��ȯ
		 * 
		 * */
		return postsRepository.findAllDesc().stream()
				.map(PostsListResponseDto::new)		// .map(posts -> new PostsListResponseDto(posts))
				.collect(Collectors.toList());		 
	}

}
