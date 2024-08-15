package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJ	oin")
	@ResponseBody
	public Object doJoin(String LoginId, String LoginPw,String name,String nickname,String cellphoneNum,String email) {
        
		if (Ut.EmptyOrNull(LoginId)) {
			return "LoginId를 입력해주세요 ";
		}
		if (Ut.EmptyOrNull(LoginPw)) {
			return "LoginPw를 입력해주세요 ";
		}
	
		if (Ut.EmptyOrNull(name)) {
			return "name를 입력해주세요 ";
		}
	
		if (Ut.EmptyOrNull(nickname)) {
			return "nickname를 입력해주세요 ";
		}
	
		if (Ut.EmptyOrNull(cellphoneNum)) {
			return "cellphoneNum를 입력해주세요 ";
		}
		if (Ut.EmptyOrNull(email)) {
			return "email를 입력해주세요 ";
		}
	
		
	
		int id = memberService.dojoin(LoginId,LoginPw,name,nickname,cellphoneNum,email);
		
		if(id == -1) {
			return "이미 사용중인 아이디야";
		}
		
		Member member = memberService.getMemberById(id);
		return member;
	}

}