package com.freelec.spring.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter						// get�޼ҵ� ����
@RequiredArgsConstructor	// ������ ���� (final ������ ������ ����x)
public class HelloResponseDto {

	private final String name;
	private final int amount;
}
