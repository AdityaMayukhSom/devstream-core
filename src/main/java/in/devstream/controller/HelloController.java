package in.devstream.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloController {

    @GetMapping(path = "/{name}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String doGet(@PathVariable String name) {
        return String.format("Greetings For %s From Spring Boot", name);
    }
}
