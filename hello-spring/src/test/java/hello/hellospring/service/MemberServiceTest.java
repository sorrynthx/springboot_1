package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memberRepository;
	
	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
	@Test
	void join() {
		// given
		Member member1 = new Member();
		member1.setName("ö��");
		
		// when 
		Long saveId = memberService.join(member1);
		
		// then
		Member findMember = memberService.findByOne(saveId).get();
		Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void  joinTest11() {
		// given
		Member member1 = new Member();
		member1.setName("ö��");
		
		Member member2 = new Member();
		member2.setName("ö��");
		
		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		// then
		assertThat(e.getMessage()).isEqualTo("�̹� �����ϴ� �̸��� �ֽ��ϴ�.");
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findByOne() {
		
	}
	
	
}
