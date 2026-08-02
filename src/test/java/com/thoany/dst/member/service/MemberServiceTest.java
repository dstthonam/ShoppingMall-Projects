package com.thoany.dst.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thoany.dst.member.dto.MemberCreateRequestDto;
import com.thoany.dst.member.dto.MemberResponseDto;
import com.thoany.dst.member.dto.MemberUpdateRequestDto;
import com.thoany.dst.member.entity.Members;
import com.thoany.dst.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
public class MemberServiceTest {

		@Autowired
		MemberService memberService;
		
		@Autowired
		MemberRepository memberRepository;

		@Test
		@Disabled("Test Completed")
		void SearchAllMemberTest() {

		    // given
			memberService.createMembers(
					MemberCreateRequestDto.builder()
						.memberName("홍길동")
						.userEmail("hong@test.com")
						.userPhoneNumber("01012341234")
						.userAddress("서울")
						.description("테스트")
						.build());

			memberService.createMembers(
					MemberCreateRequestDto.builder()
						.memberName("박길동")
						.userEmail("park@test.com")
						.userPhoneNumber("01099991234")
						.userAddress("부산")
						.description("테스트02")
						.build());

		    // when
		    List<MemberResponseDto> members = memberService.findAll();

		    // then
		    members.forEach(member -> System.out.println(member.getMemberName()));
		    
		    assertThat(members).hasSize(2);
		}
		
		@Test
		@Disabled("Test Completed")
		void SearchMemberTest() {

		    // given
		    MemberCreateRequestDto request = MemberCreateRequestDto.builder()
		            .memberName("홍길동")
		            .userEmail("hong@test.com")
		            .userPhoneNumber("01012341234")
		            .userAddress("서울")
		            .description("테스트")
		            .build();

		    MemberResponseDto createdMember = memberService.createMembers(request);

		    // when
		    MemberResponseDto found = memberService.findById(createdMember.getMemberId());

		    // then
		    assertThat(found.getMemberName()).isEqualTo("홍길동");
		    assertThat(found.getUserEmail()).isEqualTo("hong@test.com");
		}
		
		@Test
		@Disabled("Test Completed")
		void createMemberTest() {

		    // given
		    MemberCreateRequestDto request = MemberCreateRequestDto.builder()
		    			.memberName("홍길동")
		    			.userEmail("hong@test.com")
		    			.userPhoneNumber("01012341234")
		    			.userAddress("서울")
		    			.description("테스트")
		    			.build();
			
		    // when
		    MemberResponseDto createdMember = memberService.createMembers(request);

		    // then
		    Members member = memberRepository.findById(createdMember.getMemberId()).orElseThrow();

		    assertThat(member.getMemberName()).isEqualTo("홍길동");
		    assertThat(member.getUserEmail()).isEqualTo("hong@test.com");
		}

		@Test
		@Disabled("Test Completed")
		void updateMemberTest() {
			
		    // given
			// create Member
		    MemberCreateRequestDto request = MemberCreateRequestDto.builder()
		    			.memberName("홍길동")
		    			.userEmail("hong@test.com")
		    			.userPhoneNumber("01012341234")
		    			.userAddress("서울")
		    			.description("테스트")
		    			.build();

		    MemberResponseDto createdMember = memberService.createMembers(request);

		    // when
		    // update Member
		    MemberUpdateRequestDto updateRequest = MemberUpdateRequestDto.builder()
		    			.userEmail("park@test.com")
		    			.userPhoneNumber("01022223333")
		    			.userAddress("인천")
		    			.description("테스트03")
		    			.build(); 

		    MemberResponseDto updatedMember = memberService.updateMember(createdMember.getMemberId(), updateRequest);

		    // then
		    Members members = memberRepository.findById(updatedMember.getMemberId()).orElseThrow();
		    
		    assertThat(members.getUserEmail()).isEqualTo(updateRequest.getUserEmail());
		    assertThat(members.getUserAddress()).isEqualTo(updateRequest.getUserAddress());
		    assertThat(members.getUserAddress()).isEqualTo("인천1");
		    assertThat(members.getUserPhoneNumber()).isEqualTo(updateRequest.getUserPhoneNumber());
		}

		@Test
		//@Disabled("Test Completed")
		void deleteMemberTest() {
			
			    // given
				// create Member
			    MemberCreateRequestDto request = MemberCreateRequestDto.builder()
			    			.memberName("홍길동")
			    			.userEmail("hong@test.com")
			    			.userPhoneNumber("01012341234")
			    			.userAddress("서울")
			    			.description("테스트")
			    			.build();
	
			    MemberResponseDto createdMember = memberService.createMembers(request);
	
			    // when
			    // delete Member
			    memberService.deleteMember(createdMember.getMemberId());
			    
			    // then
			    assertThat(memberRepository.findById(createdMember.getMemberId())).isEmpty();
			    assertThat(memberRepository.existsById(createdMember.getMemberId())).isFalse();
		}
		
}
