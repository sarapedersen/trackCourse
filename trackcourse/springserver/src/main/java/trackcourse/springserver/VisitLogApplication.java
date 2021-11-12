package trackcourse.springserver;

import com.fasterxml.jackson.databind.Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VisitLogApplication {

  /**
   * Starts the application.
   *
   * @param args command line args
   */
  public static void main(String[] args) {
    SpringApplication.run(VisitLogApplication.class, args);
  }
}
