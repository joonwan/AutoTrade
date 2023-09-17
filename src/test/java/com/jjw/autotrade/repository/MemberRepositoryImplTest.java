package com.jjw.autotrade.repository;

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
class MemberRepositoryImplTest {


    private final MemberRepository memberRepository;

    @Autowired
    public MemberRepositoryImplTest(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Test
    public void save() throws Exception {
        //given
        Member member = new Member();
        member.setId(1L);
        member.setName("testUser");
        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(member.getId());

        //then

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}