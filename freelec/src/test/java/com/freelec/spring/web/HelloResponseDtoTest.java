package com.freelec.spring.web;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.freelec.spring.web.dto.HelloResponseDto;

public class HelloResponseDtoTest {
	
	@Test
	public void lombokTest() {
		// given
		String name = "test";
		int amount = 1000;
		
		// when
		HelloResponseDto dto = new HelloResponseDto(name, amount);
		
		// then
		assertThat(dto.getName()).isEqualTo(name);		// assertThat 검증
		assertThat(dto.getAmount()).isEqualTo(amount);	// isEqualTo 같은지 비교
	}
	
}
