package in.devstream.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stream")
public class StreamController {

    @GetMapping("/")
    public String doGet() {
        return "Here Comes Devstream Streaming";
    }
}
