package name.lizhe.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@ComponentScan("name.lizhe")
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}