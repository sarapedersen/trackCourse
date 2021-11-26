package trackcourse.springserver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.web.bind.annotation.GetMapping;
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
   * Gets the servers' data.
   *
   * @return the data in server in a String
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
   * Adds the data to the server
   *
   * @param subs the collection of subs it posts to the server
   * @return true after adding subs
   * @throws URISyntaxException
   * @throws IOException
   * @throws JsonProcessingException
   */
  @PostMapping
  protected boolean setData(@RequestBody Collection<Subject> subs)
      throws JsonProcessingException, IOException, URISyntaxException {

    FileHandlerApp a = new FileHandlerApp();
    a.writeToJson(subs);

    System.out.println("Post posted!");
    return true;
  }

}

