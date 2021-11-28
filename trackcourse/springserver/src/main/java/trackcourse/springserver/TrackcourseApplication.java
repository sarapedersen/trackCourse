package trackcourse.springserver;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrackcourseApplication {

  /**
   * Starts the application.
   *
   * @param args command line args
   */

  public static void main(String[] args) {
    SpringApplication application = new SpringApplication(TrackcourseApplication.class);

    // If Spring Boot arguments contain the word "testmode",

    // Set the server port
    application.setDefaultProperties(Collections.singletonMap("server.port", String.valueOf(8080)));

    application.run(args);
  }

}
