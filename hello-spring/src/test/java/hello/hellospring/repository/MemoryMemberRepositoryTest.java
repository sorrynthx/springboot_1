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
		
		// ����
		Member member = new Member();
		member.setName("Spring 1");
		
		// ȸ�� ���
		repository.save(member);
		
		// ã��
		Member result = repository.findById(member.getId()).get();
		
		// ����
		Assertions.assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		// ����
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		// ã��
		Member result = repository.findByName("Spring1").get();
		
		// ����
		Assertions.assertThat(result).isEqualTo(member1);
	}
	
	@Test
	public void findAll() {
		// ����
		Member member1 = new Member();
		member1.setName("Spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("Spring2");
		repository.save(member2);
		
		// ã��
		List<Member> result = repository.findAll();
		
		// ����
		Assertions.assertThat(result.size()).isEqualTo(2);
	}
}
