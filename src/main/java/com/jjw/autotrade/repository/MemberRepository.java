package com.jjw.autotrade.repository;

import com.jjw.autotrade.domain.Member;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long memberId);
}
