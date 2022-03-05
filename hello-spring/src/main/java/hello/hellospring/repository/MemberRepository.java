package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	// ȸ�����
	Member save(Member member);
	// ���̵�� ȸ��ã��
	Optional<Member> findById(Long id);
	// �̸����� ȸ��ã��
	Optional<Member> findByName(String name);
	// ��ü ȸ��ã��
	List<Member> findAll();
	
}
