package trackcourse.springserver;

import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;
import trackcourse.core.CourseList;

import java.io.IOException;
import java.util.Collection;
import java.util.logging.FileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Service
public class TrackcourseService {

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

    return "<html><title>Name check for" + sub.getCourseCode() + "</title>"
            + "<body><h1>" + response + "</h1></body></html>";
    
  }



}
