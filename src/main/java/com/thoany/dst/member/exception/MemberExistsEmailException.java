package com.thoany.dst.member.exception;

import com.thoany.dst.common.exception.ErrorCode;
import com.thoany.dst.common.exception.ResourceNotFoundException;

public class MemberExistsEmailException extends ResourceNotFoundException  {

	public MemberExistsEmailException(String userEmail) {
		super(ErrorCode.DUPLICATE_EMAIL);
	}

}
