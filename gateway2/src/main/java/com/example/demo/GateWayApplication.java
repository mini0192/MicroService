package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class GateWayApplication {

	@Bean
	public RouteLocator router(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("ms1", r -> r
						.path("/ms1/**")
						.filters(f -> f
								.filter(new GlFilter().apply(new GlFilter.Config(true, false)))
								.rewritePath("/ms1/(?<segment>.*)", "/${segment}"))
						.uri("lb://ms1"))
				.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GateWayApplication.class, args);
	}

}
