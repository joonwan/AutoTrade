package com.jjw.autotrade.service;

import com.jjw.autotrade.domain.Member;
import com.jjw.autotrade.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{


    private final MemberRepository repository;


    @Override
    public Member join(Member member) {
        repository.save(member);
        return member;
    }

    @Override
    public Member findMember(Long memberId) {
        return  repository.findById(memberId);
    }
}
