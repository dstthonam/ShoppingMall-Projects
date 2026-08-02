package com.thoany.dst.member.entity;

import com.thoany.dst.common.entity.BaseEntity;
import com.thoany.dst.member.dto.MemberCreateRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBERS")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Members extends BaseEntity {

		@Id
		@GeneratedValue(
			    strategy = GenerationType.SEQUENCE,
			    generator = "member_seq"
			)
		@SequenceGenerator(
			    name = "member_seq",
			    sequenceName = "MEMBER_SEQ",
			    allocationSize = 1
			)
		@Column(name = "MEMBER_ID")
		private Long memberId;

		@Column(name = "MEMBER_NAME", nullable = false, length = 100)
		private String memberName;

		@Column(name = "USER_ID", length = 10)
		private Long userId;

		@Column(name = "USER_EMAIL", length = 100)
		private String userEmail;

		@Column(name = "USER_PHONE_NUMBER", length = 100)
		private String userPhoneNumber;

		@Column(name = "USER_ADDRESS", length = 1000)
		private String userAddress;

		@Column(name = "DESCRIPTION", length = 1000)
		private String description;

		@Builder
		public Members (String memberName, String userEmail, String userPhoneNumber, String userAddress, String description) {
				this.memberName = memberName;
				this.userEmail = userEmail;
				this.userPhoneNumber = userPhoneNumber;
				this.userAddress = userAddress;
				this.description = description;
		}
		
		public void updateMembers (String userEmail, String userPhoneNumber, String userAddress, String description) {
				this.userEmail = userEmail;
				this.userPhoneNumber = userPhoneNumber;
				this.userAddress = userAddress;
				this.description = description;
		}
		
		// 정적 팩토리 메서드
		public static Members create(MemberCreateRequestDto request) {
			    return Members.builder()
			            .memberName(request.getMemberName())
			            .userEmail(request.getUserEmail())
			            .userPhoneNumber(request.getUserPhoneNumber())
			            .userAddress(request.getUserAddress())
			            .description(request.getDescription())
			            .build();
		}
		
}
