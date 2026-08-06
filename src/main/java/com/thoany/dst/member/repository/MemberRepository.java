package com.thoany.dst.member.repository;

import java.awt.print.Pageable;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.thoany.dst.member.entity.Members;

public interface MemberRepository extends JpaRepository<Members, Long>{
		
		Page<Members> findByMemberOrderByMemberNameAsc(Members members, Pageable pageable);
	
		// Email 중복 여부 체크
		boolean existsByUserEmail(String userEmail);
		
		// Email 중복 여부 체크(Update)
		boolean existsByUserEmailAndMemberIdNot(String userEmail, Long memberId);
		
		// 전화번호 중복 여부 체크
		boolean existsByUserPhoneNumber(String userPhoneNumber);
		
		// Email 중복 여부 체크(Update)
		boolean existsByUserPhoneNumberAndMemberIdNot(String userPhoneNumber, Long memberId);
}
