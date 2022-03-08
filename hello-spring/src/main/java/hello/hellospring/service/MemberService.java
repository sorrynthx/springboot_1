package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

//@Service
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원가입
	public Long join(Member member) {
		// 같은 이름 중복회원 방지
		validateDuplicateMember(member);
		// 등록
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		.ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 이름이 있습니다.");
		});
	}
	
	// 전체회원 조회
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	// 아이디로 조회
	public Optional<Member> findByOne(Long memberID) {
		return memberRepository.findById(memberID);
	}
	
}
	
