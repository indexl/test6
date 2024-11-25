package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Member;

@Mapper
public interface MemberDao {

	@Insert("""
			INSERT INTO `member`
				SET regDate = NOW()
					, updateDate = NOW()
					, loginEmail = #{loginEmail}
					, loginPw = #{loginPw}
					, name = #{name}
			""")
	void joinMember(String loginEmail, String loginPw, String name);

	@Select("""
			SELECT *
				FROM `member`
				WHERE loginEmail = #{loginEmail}
			""")
	Member getMemberByLoginEmail(String loginEmail);

	@Select("""
			SELECT *
				FROM `member`
				WHERE id = #{id}
			""")
	Member getMemberById(int id);

	@Select("""
			SELECT LAST_INSERT_ID();
			""")
	int getLastInsertId();
	
	@Update("""
			UPDATE `member`
				SET updateDate = NOW()
					, loginPw = #{loginPw}
				WHERE id = #{loginedMemberId}
			""")
	void modifyPassword(int loginedMemberId, String loginPw);
}