package com.jjw.autotrade.repository;

import com.jjw.autotrade.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;


@Repository
public class MemberRepositoryImpl implements MemberRepository{

    @PersistenceContext
    EntityManager em;

    public MemberRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Member findById(Long memberId) {
        return  em.find(Member.class, memberId);
    }
}
