package com.test.architect_prj.mapper;
import com.test.architect_prj.vo.AuthVO;
import com.test.architect_prj.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    public int insertMember(MemberVO memberVO);
    public int insertAuth(AuthVO authVO);
    public MemberVO read(String memId);
}
