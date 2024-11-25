package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrReplyController {
	
	private ReplyService replyService;
	
	public UsrReplyController(ReplyService replyService) {
		this.replyService = replyService;
	}
	
	@PostMapping("/usr/reply/doWrite")
	@ResponseBody
	public String doWrite(HttpServletRequest req, String relTypeCode, int relId, String body) {
	    HttpSession session = req.getSession(false);

	    Integer loginedMemberId = (Integer) session.getAttribute("loginedMemberId");
	    
	    replyService.writeReply(loginedMemberId, relTypeCode, relId, body);
	    
	    return Util.jsReturn("댓글이 작성되었습니다.", String.format("../article/detail?id=%d", relId));
	}
}