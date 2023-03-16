package nworld.dev.helloboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(String name) {
        if(name == null || name.trim().length() == 0) throw new IllegalArgumentException();
        return helloService.sayHello(name);
    }

    @GetMapping("/hello2")
    public String hello2(String car) {
        if(car == null || car.trim().length() == 0) throw new IllegalArgumentException();
        return helloService.sayCars(car);
    }
}
