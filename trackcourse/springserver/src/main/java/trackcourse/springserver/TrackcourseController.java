package trackcourse.springserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

@RestController
@RequestMapping(TrackcourseController.CONTROLLER_PATH)
public class TrackcourseController {

  public static final String CONTROLLER_PATH = "/data";

  /**
   * Gets the servers' VisitLog.
   *
   * @return the visit log
   * @throws IOException
   * @throws FileNotFoundException
   */
  @GetMapping
  protected String getData() throws FileNotFoundException, IOException {
    FileHandlerApp a = new FileHandlerApp();
    Collection<Subject> c = a.readFromJson();

    ObjectMapper objectMapper = new ObjectMapper();
    String jsonSubject = objectMapper.writeValueAsString(c);

    System.out.println("GET request gotten!");

    return jsonSubject;
  }

  /**
   * Adds a visit to the servers' VisitLog.
   *
   * @param visit visit to add
   * @return true after adding visit
   */
  @PostMapping
  protected boolean addData() {

    return true;
  }

}

/*
 * import java.io.IOException;
 * 
 * import org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.PutMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.bind.annotation.ResponseBody; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * @RestController
 * 
 * @RequestMapping("/data") public class TrackcourseController {
 * 
 * @PostMapping public static String Post(String json) {
 * System.out.println("posting..."); return json; }
 * 
 * @PostMapping protected boolean addVisit(@RequestBody Visit visit) {
 * visitLogService.addVisit(visit); return true; }
 * 
 * }
 * 
 */