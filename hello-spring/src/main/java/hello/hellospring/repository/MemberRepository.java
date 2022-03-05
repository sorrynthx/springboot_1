package hello.hellospring.repository;

import java.util.List;
import java.util.Optional;

import hello.hellospring.domain.Member;

public interface MemberRepository {
	// 회원등록
	Member save(Member member);
	// 아이디로 회원찾기
	Optional<Member> findById(Long id);
	// 이름으로 회원찾기
	Optional<Member> findByName(String name);
	// 전체 회원찾기
	List<Member> findAll();
	
}
