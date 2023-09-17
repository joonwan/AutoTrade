package com.jjw.autotrade.domain.member;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Member {

    @Id
    private Long Id;

    private String name;
}
