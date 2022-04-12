package com.freelec.spring.domain.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
	
	// �Ҽ� �α������� ��ȯ�Ǵ� ���� email�� ���� �̹� ������ ��������� ó�� ���������� �Ǵ�
	Optional<User> findByEmail(String email);
}