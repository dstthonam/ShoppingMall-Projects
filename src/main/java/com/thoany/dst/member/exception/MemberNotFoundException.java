package com.thoany.dst.member.exception;

import com.thoany.dst.common.exception.ErrorCode;
import com.thoany.dst.common.exception.ResourceNotFoundException;
import com.thoany.dst.member.entity.Members;

public class MemberNotFoundException extends ResourceNotFoundException {

	public MemberNotFoundException(Long memberId) {
		super(ErrorCode.MEMBER_NOT_FOUND);
	}

}
