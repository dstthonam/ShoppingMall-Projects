package com.thoany.dst.member.controller;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thoany.dst.member.dto.MemberCreateRequestDto;
import com.thoany.dst.member.dto.MemberResponseDto;
import com.thoany.dst.member.dto.MemberUpdateRequestDto;
import com.thoany.dst.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

		private final MemberService memberService;
		
		/**
		@GetMapping
		public ResponseEntity<List<MemberResponseDto>> findMembersAll() {
				
			return ResponseEntity.ok(memberService.findMembersAll());
		}
		*/

		// 페이징 처리
		@GetMapping
		public String findMembers(Pageable pageable) {
			memberService.findMembersPagingAll();
			
			return "main";
		}

		@GetMapping
		public ResponseEntity<List<MemberResponseDto>> findMembersAll(
								@RequestParam(required = false, defaultValue = "0", value = "page") int pageNo,
		                        @RequestParam(required = false, defaultValue = "memberName", value = "criteria") String criteria) {
				
			return ResponseEntity.ok(memberService.findMembersPagingAll(pageNo, criteria));
		}
		
		@GetMapping("/{memberId}")
		public ResponseEntity<MemberResponseDto> findMembersById(
				@PathVariable("memberId") Long memberId) {
				
			return ResponseEntity.ok(memberService.findMembersById(memberId));
		}

		@PostMapping
		public ResponseEntity<MemberResponseDto> createMembers(
				@Valid @RequestBody MemberCreateRequestDto request) {

		    MemberResponseDto response = memberService.createMembers(request);
	
		    return ResponseEntity.status(HttpStatus.CREATED).body(response);
		}

		@PutMapping("/{memberId}")
		public ResponseEntity<MemberResponseDto> updateMembersById(
				@PathVariable("memberId") Long memberId, 
				@Valid @RequestBody MemberUpdateRequestDto request) {
				
			return ResponseEntity.ok(memberService.updateMember(memberId, request));
		}

		@DeleteMapping("/{memberId}")
		public ResponseEntity<Void> deleteMembers(
				@PathVariable("memberId") Long memberId) {
				
			memberService.deleteMember(memberId);
			
			return ResponseEntity.noContent().build();
		}
		
}
