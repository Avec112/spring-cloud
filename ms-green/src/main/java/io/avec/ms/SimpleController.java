package io.avec.ms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @Value("${spring.application.name}") private String name;
    @Value("${server.port}") private String port;

    @RequestMapping("/green")
    private String simple() {
        return name + ":" + port;
    }
}
