package com.yang.test.java.spring;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.module.ModuleReader;
import java.util.Objects;
import java.util.stream.Stream;

@ComponentScan(value = "com.yang.test.java.spring")
public class Test {
	public static void main(String[] args) {
		ModuleLayer.boot().configuration().modules().stream()
				.forEach(resolvedModule -> {
					System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					// NOTE: a ModuleReader and a Stream returned from ModuleReader.list() must be closed.
					try (ModuleReader moduleReader = resolvedModule.reference().open();
						 Stream<String> names = moduleReader.list()) {
						names
								.forEach(System.out::println);
					}
					catch (IOException ex) {
						throw new UncheckedIOException(ex);
					}

					System.out.println("#########################");
				});



//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("123456", Test.class);
//		context.close();
	}
	@Bean
	public Test test1() {
		return new Test();
	}


}
@Component
class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		RootBeanDefinition beanDefinition = new RootBeanDefinition(MyBean.class);
		registry.registerBeanDefinition("myBean", beanDefinition);
	}
}
class MyBean {}