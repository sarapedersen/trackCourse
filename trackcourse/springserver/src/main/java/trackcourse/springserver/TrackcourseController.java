package trackcourse.springserver;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrackcourseController {

  @RequestMapping("/helloworld")
  public static String Hello() {
    return "Hello World";
  }

  @RequestMapping("/courses")
  public String Hello(String json) {
    return json;
  }

}
