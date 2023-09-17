package com.jjw.autotrade.service;

import com.jjw.autotrade.domain.Member;
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
}