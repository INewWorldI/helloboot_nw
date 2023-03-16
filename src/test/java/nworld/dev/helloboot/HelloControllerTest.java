package nworld.dev.helloboot;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloControllerTest {
    @Test
    void helloController() {
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return name;
            }

            @Override
            public String sayCars(String car) {
                return car;
            }
        });

        String hello = helloController.hello("Spring");
        assertThat(hello).isEqualTo("Spring");
    }

    @Test
    void failsHelloController() {
        HelloController helloController = new HelloController(new HelloService() {
            @Override
            public String sayHello(String name) {
                return null;
            }

            @Override
            public String sayCars(String car) {
                return null;
            }
        });

        assertThatThrownBy(() -> helloController.hello(null)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> helloController.hello("")).isInstanceOf(IllegalArgumentException.class);
    }
}
