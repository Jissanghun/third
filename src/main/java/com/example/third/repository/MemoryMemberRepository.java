package com.example.third.repository;

import com.example.third.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static final Map<Long, Member> map = new HashMap<>();
    private static Long seq =0L;
    @Override
    public Member save(Member member) {
        member.setId(++seq);
        map.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public Optional<Member> findByLoginId(String loginId) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public void update(Long id, Member member) { // item 은 값이 수정된 필드 값을 가지고 있음
       Member findMember = findById(id).get(); // findItem은 수정되기 전의 필드 값을 가지고 있음
       //System.out.println("findItem = " + findItem);
//        findMember.sete(item.getItemName());
//        findMember.setQuantity(item.getQuantity());
//        findMemberm.setPrice(item.getPrice());
       map.put(member.getId(), findMember);
    }
}
