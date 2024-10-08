package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

@Service
public class MemberService {
 
	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
			this.memberRepository = memberRepository;

	}

   public Member getMemberById(int id) {
		return memberRepository.getMemberById(id);
	}

	public ResultData<Integer> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,String email) {
		
		Member existMember = getMemberByLoginId(loginId);
		
		if ( existMember != null) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s)입니다.", loginId));
		}
		existMember = getMemberByNameAndEmail(name,email);
		
		if(existMember != null) {
			return ResultData.from("F-8", Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다.", name, email));
		
		}
		
		memberRepository.dojoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		int id = memberRepository.getLastInsertId();
		
		return ResultData.from("S-1"," 회원 가입됨 ", id);
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name,email);
	}

	private Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

}
