package io.avec.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Slf4j
@SpringBootApplication
public class FrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendApplication.class, args);
    }

    @Controller
    static class MyController {
        @Value("${blue.url}")
        private String blueUrl;

        @Value("${green.url}")
        private String greenUrl;

        @RequestMapping("/blue")
        public @ResponseBody String blue(RestTemplate restTemplate) {
            return getString(restTemplate, blueUrl);
        }

        @RequestMapping("/green")
        public @ResponseBody String green(RestTemplate restTemplate) {
            return getString(restTemplate, greenUrl);
        }

        private String getString(RestTemplate restTemplate, String url) {
            //            log.debug("url={}", url);
            String result = restTemplate.getForObject(url, String.class);
            log.debug("result -> {}", result);
            return result;
        }
    }
}
