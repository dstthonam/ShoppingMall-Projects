package com.thoany.dst.member.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thoany.dst.member.dto.MemberCreateRequestDto;
import com.thoany.dst.member.dto.MemberResponseDto;
import com.thoany.dst.member.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

		private final MemberService memberService;
		
		@PostMapping
		public ResponseEntity<MemberResponseDto> createMembers(@Valid @RequestBody MemberCreateRequestDto request) {
				
				return ResponseEntity.ok(memberService.createMembers(request));
		}
		
}
