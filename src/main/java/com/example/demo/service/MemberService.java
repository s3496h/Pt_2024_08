package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

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

	public int dojoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum, String email) {
		
		Member existMember = getMemberByLoginId(loginId);
		
		if ( existMember != null) {
			return -1;
		}
		
		existMember = getMemberByNameAndEmail(name,email);
		
		memberRepository.dojoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		
		return memberRepository.getLastInsertId();
	}

	private Member getMemberByNameAndEmail(String name, String email) {
		return memberRepository.getMemberByNameAndEmail(name,email);
	}

	private Member getMemberByLoginId(String loginId) {
		
		return memberRepository.getMemberByLoginId(loginId);
	}

}
