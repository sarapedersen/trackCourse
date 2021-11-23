package trackcourse.springserver;

import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

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
import org.springframework.web.bind.annotation.RestController;

@Service
public class TrackcourseService {

  @GetMapping("/checkName")
  public String checkName(String name) {
    return "Hello World";
  }

}
