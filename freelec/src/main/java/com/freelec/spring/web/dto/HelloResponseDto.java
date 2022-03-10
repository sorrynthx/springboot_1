package com.freelec.spring.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter						// get皋家靛 积己
@RequiredArgsConstructor	// 积己磊 积己 (final 绝栏搁 积己磊 器窃x)
public class HelloResponseDto {

	private final String name;
	private final int amount;
}
