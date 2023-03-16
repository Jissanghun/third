package com.example.third.repository;

import com.example.third.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.*;

public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;

    @Autowired
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(em.find(Member.class, id));
    }
    @Override
    public Optional<Member> findByLoginId(String loginId) { // loginid 대소문자 구분 확인 => login_id jpa ddl create 자동으로 생성함
        return em.createQuery("SELECT m FROM Member m where login_id = :loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList()
                .stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findAny();
    }// select * from member where login_id = ? ("aaa")
    //em.createQuery("SELECT m FROM Member m where name = :name", Member.class)
    //.setParameter("name", name)


    @Override
    public List<Member> findAll() {
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return members;
    }
    @Override
    public void update(Long id, Member member) { // item 은 값이 수정된 필드 값을 가지고 있음
//       Member findMember = findById(id).get(); // findItem은 수정되기 전의 필드 값을 가지고 있음
       //System.out.println("findItem = " + findItem);
//        findMember.sete(item.getItemName());
//        findMember.setQuantity(item.getQuantity());
//        findMemberm.setPrice(item.getPrice());
     //  map.put(member.getId(), findMember);
    }
}
