package hello.hellospring.repository;


import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;

class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		
		// 세팅
		Member member = new Member();
		member.setName("Spring 1");
		
		// 회원 등록
		repository.save(member);
		
		// 찾기
		Member result = repository.findById(member.getId()).get();
		
		// 검증
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		// 세팅
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		// 찾기
		Member result = repository.findByName("Spring1").get();
		
		// 검증
		Assertions.assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		// 세팅
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		// 찾기
		List<Member> result = repository.findAll();
		
		// 검증
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}
