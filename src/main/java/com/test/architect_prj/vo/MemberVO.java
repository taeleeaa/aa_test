package com.test.architect_prj.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MemberVO {
    private String memId;
    private String memName;
    private String memPw;
    private boolean memEnable;
    private String memRegdate;

    private List<AuthVO> authList;
}
