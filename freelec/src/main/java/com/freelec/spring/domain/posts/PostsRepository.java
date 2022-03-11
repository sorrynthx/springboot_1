package com.freelec.spring.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {	// JpaRepository<객체타입, 아이디타입>

}
