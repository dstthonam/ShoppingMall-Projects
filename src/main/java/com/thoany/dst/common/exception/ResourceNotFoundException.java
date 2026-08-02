package com.thoany.dst.common.exception;

public abstract class ResourceNotFoundException extends RuntimeException {

		protected ResourceNotFoundException(Class<?> resourceClass, Object id) {
			
				super(String.format("%s를 찾을 수 없습니다. id = %s", resourceClass.getSimpleName(), id));	
		}

}
