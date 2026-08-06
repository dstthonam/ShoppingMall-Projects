package com.thoany.dst.member.exception;

import com.thoany.dst.common.exception.ErrorCode;
import com.thoany.dst.common.exception.ResourceNotFoundException;

public class MemberExistsPhoneNumberException extends ResourceNotFoundException  {

	public MemberExistsPhoneNumberException(String userPhoneNumber) {
		super(ErrorCode.DUPLICATE_PHONENUMBER);
	}

}
