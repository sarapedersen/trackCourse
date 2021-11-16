package trackcourse.springserver;

import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

import java.util.Collection;
import java.util.logging.FileHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TrackcourseController.CONTROLLER_PATH)
public class TrackcourseController {

  public static final String CONTROLLER_PATH = "/trackcourse";

  /**
   * Gets the servers' data.
   *
   * @return the data
   * 
   * @GetMapping protected Collection<Subject> getSubjects() { return
   *             "getSubjects();" }
   */

  /**
   * Adds a subject to the server
   *
   * @param Subject visit to add
   * @return true after adding visit
   */
  /*
   * @PostMapping protected boolean addSubjcet(@RequestBody Subject sub) {
   * trackService.addSubject(sub); return true; }
   */
}
