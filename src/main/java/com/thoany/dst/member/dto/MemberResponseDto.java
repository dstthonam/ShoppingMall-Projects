package com.thoany.dst.member.dto;

import com.thoany.dst.member.entity.Members;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponseDto {

		private Long 	memberId;
		private String	memberName;
		private Long	userId;
		private String	userEmail;
		private String	userPhoneNumber;
		private String	userAddress;
		private String	description;
		
		public static MemberResponseDto from(Members members) {
				return new MemberResponseDto(
						members.getMemberId(),
						members.getMemberName(),
						members.getUserId(),
						members.getUserEmail(),
						members.getUserPhoneNumber(),
						members.getUserAddress(),
						members.getDescription()
				);
		}
		
}
