package com.thoany.dst.member.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thoany.dst.member.dto.MemberCreateRequestDto;
import com.thoany.dst.member.dto.MemberResponseDto;
import com.thoany.dst.member.dto.MemberUpdateRequestDto;
import com.thoany.dst.member.entity.Members;
import com.thoany.dst.member.exception.MemberNotFoundException;
import com.thoany.dst.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

		private final MemberRepository memberRepository;

		public List<MemberResponseDto> findAll() {
				
				return memberRepository.findAll()
									.stream()
									.map(MemberResponseDto::from)
									.toList(); 
		}
		
		public MemberResponseDto findById(Long memberId) {
				
				Members members = memberRepository.findById(memberId)
																	.orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
				
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
																	.orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
				
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
		
		@Transactional
		public void deleteMemberById(Long memberId) {
				
				memberRepository.deleteById(memberId);
		}
		
		// PIS 이메일 중복 확인
		private void validateDuplicateEmail(String email) {
				if (memberRepository.existsByUserEmail(email)) {
						// 커스텀예외처리하기
						throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
				}
		}

		// PIS 전화번호 중복 확인
		private void validateDuplicatePhoneNumber(String phoneNumber) {
				if (memberRepository.existsByUserPhoneNumber(phoneNumber)) {
						// 커스텀예외처리하기
						throw new IllegalArgumentException("이미 가입된 전화번호 입니다.");
				}
		}

		// PIS 이메일 중복 확인(Update)
		private void validateDuplicateEmail(Long memberId, String email) {
				if (memberRepository.existsByUserEmailAndMemberIdNot(email, memberId)) {
						// 커스텀예외처리하기
						throw new IllegalArgumentException("이미 가입된 이메일 입니다.");
				}
		}

		// PIS 전화번호 중복 확인(Update)
		private void validateDuplicatePhoneNumber(Long memberId, String phoneNumber) {
				if (memberRepository.existsByUserPhoneNumberAndMemberIdNot(phoneNumber, memberId)) {
						// 커스텀예외처리하기
						throw new IllegalArgumentException("이미 가입된 전화번호 입니다.");
				}
		}
		
		
}
