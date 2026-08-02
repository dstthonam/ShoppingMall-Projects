package com.thoany.dst;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.thoany.dst.member.repository.MemberRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
class DstApplicationTests {

		@Autowired
		MemberRepository memberRepository;
	
		@Test
		void memberRepositoryTest() {
				System.out.println("memberRepositoryTest");
			 	// test
		}

}
