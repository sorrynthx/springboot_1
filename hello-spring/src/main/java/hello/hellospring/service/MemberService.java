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
	
	// ȸ������
	public Long join(Member member) {
		// ���� �̸� �ߺ�ȸ�� ����
		validateDuplicateMember(member);
		// ���
		memberRepository.save(member);
		return member.getId();
	}
	
	private void validateDuplicateMember(Member member) {
		memberRepository.findByName(member.getName())
		.ifPresent(m -> {
			throw new IllegalStateException("�̹� �����ϴ� �̸��� �ֽ��ϴ�.");
		});
	}
	
	// ��üȸ�� ��ȸ
	public List<Member> findMembers(){
		return memberRepository.findAll();
	}

	// ���̵�� ��ȸ
	public Optional<Member> findByOne(Long memberID) {
		return memberRepository.findById(memberID);
	}
	
}
	
