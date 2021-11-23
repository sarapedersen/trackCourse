package trackcourse.springserver;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import trackcourse.core.CourseList;
import trackcourse.core.Subject;

@RestController
public class TrackcourseController {

  @RequestMapping("/helloworld")
  public String Hello() {
    return "Hello World";
  }

  @RequestMapping("/courses")
  public String Hello(String json) {
    return json;
  }

  @GetMapping("/checkName")
  @ResponseBody
  public String checkName(@RequestParam String name) throws IOException {
    String response;

    Subject sub = new Subject(name);
    //CourseList courseList = new CourseList();
    CourseList.getFullName(sub.getCourseCode());

    if(CourseList.validate(name)){
        response = "NAME IS VALID";
    } else{
        response = "NAME IS INVALID";
    }

    return "<html><title>Name check for " + sub.getCourseCode() + "</title>"
            + "<body><h1>" + response + "</h1></body></html>";
    
  }

}
