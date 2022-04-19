package com.freelec.spring.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_, Long> {
	
	// 소셜 로그인으로 반환되는 값중 email을 통해 이미 생성된 사용자인지 처음 가입자인지 판단
	Optional<User_> findByEmail(String email);
}
