//package com.test.architect_prj;
//
//import com.test.architect_prj.mapper.MemberMapper;
//import com.test.architect_prj.vo.AuthVO;
//import com.test.architect_prj.vo.MemberVO;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@Slf4j
//@SpringBootTest
//public class MemberMapperTest {
//    // 암호화및 암호체크용
//    private static PasswordEncoder bcryptPasswordEncoder = new BCryptPasswordEncoder();
//
//    @Autowired
//    private MemberMapper memberMapper;
//
//    @Test
//    @DisplayName("member(사용자) 등록 test")
//    @Disabled
//    public void insMemberTest() {
//
//        // 샘플용 Id & Name
//        List<String> memberIds   = List.of("e7e","roze","jenni","nobi");
//        List<String> memberNames = List.of("272","로제","제니","노비");
//
//        MemberVO memberVO = null;
//        int totalRow = 0;
//
//        for(int index=0; index < memberNames.size(); index++) {
//            memberVO = new MemberVO();
//            memberVO.setMemId(memberIds.get(index));
//            memberVO.setMemName(memberNames.get(index));
//            // 암호 인코딩 필요!, 암호는 Id와 그냥 같겡
//            memberVO.setMemPw(bcryptPasswordEncoder.encode(memberIds.get(index)));
//            totalRow +=memberMapper.insertMember(memberVO);
//
//        }
//        assertEquals(memberNames.size(), totalRow);
//    }
//
//    @Test
//    @DisplayName("auth(권한) 등록 test")
//    @Disabled
//    public void insAuthTest() {
//
//        AuthVO authVO = new AuthVO();
//        MemberVO memberVO = new MemberVO();
//
//        List<String> memberIds   = List.of("e7e","roze","jenni","nobi","ksj");
//        int totalRow = 0;
//
//        for (String memId : memberIds) {
//            switch (memId) {
//                case "e7e":
//                    authVO.setMemId(memId);
//                    authVO.setAuthName("ROLE_CEO");
//                    totalRow += memberMapper.insertAuth(authVO);
//
//                    authVO.setAuthName("ROLE_MANAGER");
//                    totalRow += memberMapper.insertAuth(authVO);
//                    break;
//
//                case "roze" :
//                case "jenni":
//                    authVO.setMemId(memId);
//                    authVO.setAuthName("ROLE_MANAGER");
//                    totalRow += memberMapper.insertAuth(authVO);
//                    break;
//
//                case "nobi":
//                    authVO.setMemId(memId);
//                    authVO.setAuthName("ROLE_ALBA");
//                    totalRow += memberMapper.insertAuth(authVO);
//                    break;
//
//                default: // 그냥 괘니 member, auth 양쪽에 1번에 등록
//                    memberVO.setMemId(memId);
//                    memberVO.setMemName("friend_"+memId);
//                    // 암호 인코딩 필요!, 암호는 Id와 그냥 같겡
//                    memberVO.setMemPw(bcryptPasswordEncoder.encode(memId));
//                    totalRow +=memberMapper.insertMember(memberVO);
//
//                    authVO.setMemId(memId);
//                    authVO.setAuthName("ROLE_USER");
//                    totalRow += memberMapper.insertAuth(authVO);
//            }
//
//        }
//
//        assertEquals(7, totalRow);
//    }
//
//
//    @Test
//    @DisplayName("read member(사용자검색) test")
//    @Disabled
//    public void readMember() {
//
//        MemberVO memberVO = memberMapper.read("e7e");
//
//        //암호 확인!
//        log.debug("암호일치: {}",bcryptPasswordEncoder.matches("e7e", memberVO.getMemPw()));
//
//        log.debug("ck: {}",memberVO);
//        memberVO.getAuthList().forEach(authVO -> log.debug("{}",authVO));
//
//        assertEquals(2, memberVO.getAuthList().size());
//    }
//}
