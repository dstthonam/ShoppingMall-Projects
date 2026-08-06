package com.thoany.dst.member.service;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoany.dst.member.dto.MemberCreateRequestDto;
import com.thoany.dst.member.dto.MemberResponseDto;
import com.thoany.dst.member.dto.MemberUpdateRequestDto;
import com.thoany.dst.member.entity.Members;
import com.thoany.dst.member.exception.MemberExistsEmailException;
import com.thoany.dst.member.exception.MemberExistsPhoneNumberException;
import com.thoany.dst.member.exception.MemberNotFoundException;
import com.thoany.dst.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

		private final MemberRepository memberRepository;

		public List<MemberResponseDto> findMembersAll() {
				
				return memberRepository.findAll()
									.stream()
									.map(MemberResponseDto::from)
									.toList(); 
		}
		
		public void findMembersPagingAll(int pageNo, String criteria) {
				
				memberRepository.findByMemberOrderByMemberNameAsc(createMembers(), pageable)
									.map(MemberResponseDto::from); 
		}
		
		public MemberResponseDto findMembersById(Long memberId) {
			
			    Members members = memberRepository.findById(memberId)
			    			.orElseThrow(() -> new MemberNotFoundException(memberId));
	
			    return MemberResponseDto.from(members);
		}
		
		@Transactional
		public MemberResponseDto createMembers(MemberCreateRequestDto request) {

				// validation check
				validateDuplicateEmail(request.getUserEmail());
				validateDuplicatePhoneNumber(request.getUserPhoneNumber());
				
				// 정적 팩토리 메서드로 변경
				Members members = Members.create(request);
	
				memberRepository.save(members);
				
				return MemberResponseDto.from(members);
		}
		
		@Transactional
		public MemberResponseDto updateMember(Long memberId, MemberUpdateRequestDto request) {
				
				Members members = memberRepository.findById(memberId)
		    				.orElseThrow(() -> new MemberNotFoundException(memberId));
				
				// validation check
				validateDuplicateEmail(memberId, request.getUserEmail());
				validateDuplicatePhoneNumber(memberId, request.getUserPhoneNumber());
				
				members.updateMembers(
										request.getUserEmail(),
										request.getUserPhoneNumber(),
										request.getUserAddress(),
										request.getDescription());
				
				return MemberResponseDto.from(members);
		}

		@Transactional
		public void deleteMember(Long memberId) {
				
				memberRepository.delete(memberRepository.findById(memberId)
		    			.orElseThrow(() -> new MemberNotFoundException(memberId)));
		}
		
		// 예외처리 커스텀을 위해 미사용
		/**
		@Transactional
		public void deleteMembersById(Long memberId) {
				
				memberRepository.deleteById(memberId);
		}
		*/
		
		// PIS 이메일 중복 확인
		private void validateDuplicateEmail(String email) {
				if (memberRepository.existsByUserEmail(email)) {
					throw new MemberExistsEmailException(email);
				}
		}

		// PIS 전화번호 중복 확인
		private void validateDuplicatePhoneNumber(String phoneNumber) {
				if (memberRepository.existsByUserPhoneNumber(phoneNumber)) {
					throw new MemberExistsPhoneNumberException(phoneNumber);
				}
		}

		// PIS 이메일 중복 확인(Update)
		private void validateDuplicateEmail(Long memberId, String email) {
				if (memberRepository.existsByUserEmailAndMemberIdNot(email, memberId)) {
					throw new MemberExistsEmailException(email);
				}
		}

		// PIS 전화번호 중복 확인(Update)
		private void validateDuplicatePhoneNumber(Long memberId, String phoneNumber) {
				if (memberRepository.existsByUserPhoneNumberAndMemberIdNot(phoneNumber, memberId)) {
					throw new MemberExistsPhoneNumberException(phoneNumber);
				}
		}
		
}
