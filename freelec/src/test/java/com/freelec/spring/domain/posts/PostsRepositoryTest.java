package com.freelec.spring.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest														// 별다른 설정이 없다면 H2 데이터베이스를 자동으로 실행
public class PostsRepositoryTest {
	
	@Autowired
	PostsRepository postsRepository;
	
	@AfterEach														// Junit 단위 테스트가 끝날 때마다 수행되는 메소드
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void savePosts() {
		// given
		String title = "테스트 게시글";
		String content = "테스트 본문";
		
		postsRepository.save(										// 테이블 Posts에 insert/update 쿼리를 실행 (id 있으면 update, 없으면 insert)
								Posts.builder()			
								.title(title)
								.content(content)
								.author("sorrynthx@gmail.com")
								.build()
							);
		// when
		List<Posts> postsList = postsRepository.findAll();			// 테이블 Posts에 있는 모든 데이터 조회
		
		// then
		Posts posts = postsList.get(0);
		assertThat(posts.getTitle()).isEqualTo(title);
		assertThat(posts.getContent()).isEqualTo(content);
	}
	
}
