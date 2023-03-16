package com.example.third.controller.session;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSession {
    private Long id;
    private String loginId;
    private String name;
}
