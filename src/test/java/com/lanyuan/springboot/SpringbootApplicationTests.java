package com.lanyuan.springboot;


import com.lanyuan.springboot.pojo.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootApplicationTests {
	@Autowired
	Role role;
	@Test
	void contextLoads() {
		System.out.println(role);
	}

}
