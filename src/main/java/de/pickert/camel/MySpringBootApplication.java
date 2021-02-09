package de.pickert.camel;

import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MySpringBootApplication implements CommandLineRunner {

	@Autowired
	private CamelContext camelContext;


	public static void main(String[] args) {
		SpringApplication.run(MySpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Application...");
	}

	@Bean
	public MySpringBootRouter msgRouteBuilder() throws Exception {
		MySpringBootRouter routeBuilder = new MySpringBootRouter();
		camelContext.addRoutes(routeBuilder);
		return routeBuilder;
	}
}
