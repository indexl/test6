package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Member;
import com.example.demo.service.MemberService;
import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	private MemberService memberService;

	public UsrMemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/usr/member/join")
	public String join() {
		return "usr/member/join";
	}

	@PostMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(String loginEmail, String loginPw, String name) {
		memberService.joinMember(loginEmail, loginPw, name);

		return Util.jsReturn(String.format("%s 님이 가입되었습니다", loginEmail), "login");
	}

	@GetMapping("/usr/member/loginIdDupChk")
	@ResponseBody
	public String loginIdDupChk(String loginEmail) {
		Member member = memberService.getMemberByLoginEmail(loginEmail);

		if (member != null) {
			return Util.jsReturn(String.format("%s 은(는) 이미 사용중인 이메일입니다", loginEmail), null);
		}

		return Util.jsReturn(String.format("%s 은(는) 사용가능한 이메일입니다", loginEmail), null);
	}

	@GetMapping("/usr/member/login")
	public String login() {
		return "usr/member/login";
	}

	@PostMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginEmail, String loginPw) {
	    HttpSession session = req.getSession();
	    Member member = memberService.getMemberByLoginEmail(loginEmail);

	    if (member == null) {
	        return Util.jsReturn(String.format("%s 은(는) 존재하지 않는 이메일입니다", loginEmail), null);
	    }

	    if (!member.getLoginPw().equals(loginPw)) {
	        return Util.jsReturn("비밀번호를 확인해주세요", null);
	    }

	    session.setAttribute("loginedMemberId", member.getId());

	    return Util.jsReturn(String.format("%s님 어서오세요~", member.getName()), "/");
	}

	@GetMapping("/usr/member/myPage")
	public String myPage(HttpServletRequest req, Model model) {
	
		if (!isLoggedIn(req)) {
			return "redirect:/usr/member/login";
		}

		Integer loginedMemberId = getLoginedMemberId(req);
		Member member = memberService.getMemberById(loginedMemberId);

		model.addAttribute("member", member);

		return "usr/member/myPage";
	}

	@GetMapping("/usr/member/checkPw")
	public String checkPw() {
		return "usr/member/checkPw";
	}

	@GetMapping("/usr/member/getMemberById")
	@ResponseBody
	public String getMemberById(HttpServletRequest req) {
	
		if (!isLoggedIn(req)) {
			return Util.jsReturn("로그인을 해주세요.", null);
		}

		Integer loginedMemberId = getLoginedMemberId(req);  
		Member member = memberService.getMemberById(loginedMemberId);

		if (member == null) {
			return Util.jsReturn("회원정보를 찾을 수 없습니다.", null);
		}

		return Util.jsReturn(String.format("회원 정보: 이메일 - %s, 이름 - %s", member.getLoginEmail(), member.getName()), null);
	}

	@PostMapping("/usr/member/doCheckPw")
	public String doCheckPw() {
		return "usr/member/modifyPw";
	}
	
	
	

	@GetMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
	    HttpSession session = req.getSession(false);

	    if (session != null) {
	        session.invalidate(); 
	    }

	    return Util.jsReturn("정상적으로 로그아웃 되었습니다", "/usr/home/main");
	}

	@GetMapping("/usr/member/getLoginId")
	@ResponseBody
	public String getLoginId(HttpServletRequest req) {
	
		if (!isLoggedIn(req)) {
			return Util.jsReturn("로그인 상태가 아닙니다.", null);
		}

		Integer loginedMemberId = getLoginedMemberId(req);
		Member member = memberService.getMemberById(loginedMemberId);

		return member.getLoginEmail();
	}

	private boolean isLoggedIn(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return session != null && session.getAttribute("loginedMemberId") != null;
	}

	private Integer getLoginedMemberId(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		return (Integer) session.getAttribute("loginedMemberId");
	}
}