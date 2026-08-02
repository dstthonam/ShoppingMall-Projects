package com.thoany.dst.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequestDto {
		
		//@Size(max = 10)
		//private Long memberId;
	
	    @NotBlank(message = "이메일은 필수입니다.")
	    @Email(message = "이메일 형식이 아닙니다.")
	    @Size(max = 100)
		private String userEmail;
	    
	    @NotBlank(message = "전화번호는 필수입니다.")
	    @Pattern(
	    	    regexp = "^01[016789]-?\\d{3,4}-?\\d{4}$",
	    	    message = "휴대폰 번호 형식이 올바르지 않습니다."
	    	)
	    @Size(max = 100)
		private String userPhoneNumber;

	    @Size(max = 1000)
		private String userAddress;

	    @Size(max = 1000)
		private String description;

}
