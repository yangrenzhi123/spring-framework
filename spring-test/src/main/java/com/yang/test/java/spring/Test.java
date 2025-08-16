package com.yang.test.java.spring;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(value = "com.yang.test.java.spring")
public class Test {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("123456", Test.class);
		context.close();
	}
	@Bean
	public Test test1() {
		return new Test();
	}
}