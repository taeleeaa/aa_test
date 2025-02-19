package com.test.architect_prj.security;

import com.test.architect_prj.mapper.MemberMapper;
import com.test.architect_prj.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("CKK1 {}",username);

        MemberVO memberVO = memberMapper.read(username);
        if(memberVO != null) {
            return new CustomUser(memberVO);
        }else {
            throw new UsernameNotFoundException(username);
        }
    }
}
