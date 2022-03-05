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
		member1.setName("철수");
		
		// when 
		Long saveId = memberService.join(member1);
		
		// then
		Member findMember = memberService.findByOne(saveId).get();
		Assertions.assertThat(member1.getName()).isEqualTo(findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("철수");
		
		Member member2 = new Member();
		member2.setName("철수");
		
		// when
		memberService.join(member1);
		IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
		
		// then
		assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름이 있습니다.");
	}
	
	@Test
	void findMembers() {
		
	}
	
	@Test
	void findByOne() {
		
	}
	
	
}
