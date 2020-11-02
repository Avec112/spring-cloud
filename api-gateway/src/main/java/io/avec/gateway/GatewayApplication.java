package io.avec.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/green")
						.filters(f -> f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/failed")))
						.uri("lb://green"))
				.route(p -> p.path("/blue")
						.filters(f -> f.hystrix(config -> config.setName("mycmd").setFallbackUri("forward:/failed")))
						.uri("lb://blue"))
				.build();
	}

	@Controller
	static class FailedController {
		@RequestMapping("/failed")
		public @ResponseBody
		String failed() {
			return "Service is not available. Please try again later.";
		}
	}
}
