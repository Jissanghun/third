package com.example.third.service;

import com.example.third.domain.Member;
import com.example.third.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly=true)  // readonly true 로 놓으면 업데이트가 안되기 때문에 update가 필요한 곳에서는 별도로
public class MemberService {   // @Transactional 로 줘야 함
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public Long join(Member member) throws IllegalStateException{ // memberController에서 예외 처리해줘야 함
        validateDuplicateLoginId(member.getLoginId());
        return memberRepository.save(member).getId();
    }

    public void validateDuplicateLoginId(String loginId) {
        if (!findMember(loginId).isEmpty()) {// 동일한 로그인 아이디가 존재한다는 것
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    public Optional<Member> findMember(String loginId){
        Optional<Member> byLoginId = memberRepository.findByLoginId(loginId);
        System.out.println(byLoginId);
        return byLoginId;
    }

    public Optional<Member> findMemberById(Long id){
        return memberRepository.findById(id);
    }
}
