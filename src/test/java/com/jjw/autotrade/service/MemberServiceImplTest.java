package com.jjw.autotrade.service;

import com.jjw.autotrade.domain.Member;
import com.jjw.autotrade.memberconst.MemberConst;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class MemberServiceImplTest {

    private final MemberService memberService;

    @Autowired
    public MemberServiceImplTest(MemberService memberService) {
        this.memberService = memberService;
    }


    @Test
    public void memberServiceTest() throws Exception {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("testUser");
        //when

        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        //then

        Assertions.assertThat(member.getId()).isEqualTo(findMember.getId());
    }

    @Test
    public void createJWTToken() throws Exception {
        //given

        Member member = new Member();
        member.setACCESS_KEY(MemberConst.ACCESS_KEY);
        member.setSECRET_KEY(MemberConst.PRIVATE_KEY);


        String memberInfo = memberService.getMemberInfo(member.getACCESS_KEY(), member.getSECRET_KEY(), null);
        log.info(memberInfo);
    }
}