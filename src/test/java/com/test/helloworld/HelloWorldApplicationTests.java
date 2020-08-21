package com.test.helloworld;

import com.test.helloworld.controller.HelloWorldController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class HelloWorldApplicationTests {

	@Autowired
	private HelloWorldController helloWorldController;

	@Test
	void contextLoads() {
		assertThat(helloWorldController).isNotNull();
	}

}
