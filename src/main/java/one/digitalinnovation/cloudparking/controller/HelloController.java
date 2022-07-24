package one.digitalinnovation.cloudparking.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping(value = "/")
@ApiIgnore
public class HelloController {

    @GetMapping
    public String sayHello() {
        return "Hello DIO.";
    }
}
