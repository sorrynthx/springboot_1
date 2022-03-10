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

@RunWith(SpringRunner.class) 					 // SpringRunner ������ ������ ���, ��������Ʈ-JUnit �׽�Ʈ ����
@WebMvcTest(controllers = HelloController.class) // Web(Spring MVC)�� ����, @Controller, @ControllerAdvice ��� (@Service, @Component �� �Ұ�)
public class HelloControllerTest {

	@Autowired				// �������� �����ϴ� ��(Bean) ����
	private MockMvc mvc;	// �� API �׽�Ʈ�� �� ���, MVC�׽�Ʈ ������
	
	@Test
	public void hello() throws Exception {
		String hello = "hello";
		
		mvc.perform(get("/hello"))				// MockMvc�� ���� /hello �ּҷ� HTTP GET ��û
			.andExpect(status().isOk())			// mvc.perform ��� ����, HTTP Status ����(200)
			.andExpect(content().string(hello));// Controller���� "hello"���� �´��� ����
	}
	
	@Test
	public void helloDto() throws Exception {
		String name = "hello";
		int amount = 1000;
		
		// param�� String�� ����
		mvc.perform(
					get("/hello/dto")
						.param("name", name)
						.param("amount", String.valueOf(amount)))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.name", is(name)))		// json���䰪�� �ʵ庰�� ������ �� �ִ� �޼ҵ�
						.andExpect(jsonPath("$.amount", is(amount)));
	}
	
}
