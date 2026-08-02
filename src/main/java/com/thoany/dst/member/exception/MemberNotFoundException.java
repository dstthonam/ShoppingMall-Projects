package com.thoany.dst.member.exception;

import com.thoany.dst.common.exception.ResourceNotFoundException;
import com.thoany.dst.member.entity.Members;

public class MemberNotFoundException extends ResourceNotFoundException {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

		public MemberNotFoundException(Long memberId) {
				super(Members.class, memberId);
		}

}
