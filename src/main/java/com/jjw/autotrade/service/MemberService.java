package com.jjw.autotrade.service;

import com.jjw.autotrade.domain.Member;

public interface MemberService {

    Member join(Member member);

    Member findMember(Long member);
}
