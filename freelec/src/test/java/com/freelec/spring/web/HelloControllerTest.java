package com.freelec.spring.web;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) 					 // SpringRunner 스프링 실행자 사용, 스프링부트-JUnit 테스트 연결.
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)에 집중, @Controller, @ControllerAdvice 사용 (@Service, @Component 는 불가) - JPA이용 불가.
public class HelloControllerTest {

	@Autowired				// 스프링이 관리하는 빈(Bean) 주입.
	private MockMvc mvc;	// 웹 API 테스트할 때 사용, MVC테스트 시작점.
	
	@Test
	public void hello() throws Exception {
		String hello = "hello";
		
		mvc.perform(get("/hello"))				// MockMvc를 통해 /hello 주소로 HTTP GET 요청.
			.andExpect(status().isOk())			// mvc.perform 결과 검증, HTTP Status 검증(200).
			.andExpect(content().string(hello));// Controller에서 "hello"리턴 맞는지 검증.
	}
	
	@Test
	public void helloDto() throws Exception {
		String name = "hello";
		int amount = 1000;
		
		// param은 String만 가능
		mvc.perform(
					get("/hello/dto")
						.param("name", name)
						.param("amount", String.valueOf(amount)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.name", is(name)))		// json응답값을 필드별로 검증할 수 있는 메소드.
						.andExpect(jsonPath("$.amount", is(amount)));
	}
	
}
 