package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDao;
import com.example.demo.dto.Member;

@Service
public class MemberService {
	
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}

	public void joinMember(String loginEmail, String loginPw, String name) {
		memberDao.joinMember(loginEmail, loginPw, name);
	}

	public Member getMemberByLoginEmail(String loginEmail) {
		return memberDao.getMemberByLoginEmail(loginEmail);
	}

	public Member getMemberById(int id) {
		return memberDao.getMemberById(id);
	}

	public int getLastInsertId() {
		return memberDao.getLastInsertId();
	}

	public void modifyPassword(int loginedMemberId, String loginPw) {
		memberDao.modifyPassword(loginedMemberId, loginPw);
	}
}