package com.jjw.autotrade.service;

import com.jjw.autotrade.domain.Member;

public interface MemberService {

    Member join(Member member);

    Member findMember(Long memberId);

    String getMemberInfo(String accessKey, String secretKey, String queryString);

}
