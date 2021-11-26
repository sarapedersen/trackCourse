package trackcourse.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileHandlerApp {

  private Collection<Subject> subjects = new ArrayList<>();


  public FileHandlerApp(Collection<Subject> subs) {
    this.subjects = subs;
    }

  public FileHandlerApp() {

  }

  // Takes in a collection of subs as the parameter and converts each to a own Json file
  // adds files  to the Json-folder
  public void writeToJson(Collection<Subject> subs) throws JsonProcessingException, IOException, URISyntaxException {
    for (Subject sub : subs) {
      ObjectMapper objectMapper = new ObjectMapper();
      objectMapper.writeValue(new FileOutputStream("../core/src/json/" + sub.getCourseCode() + ".json"), sub);
    }
  }

  public Collection<Subject> Get() throws URISyntaxException, JsonMappingException, JsonProcessingException {
    URI newUri = new URI("http://localhost:8080/data");
    String data = null;

    if (data == null) {
      try {
        final HttpRequest req = HttpRequest.newBuilder(newUri).header("Accept", "application/json").GET().build();
        final HttpResponse<String> res = HttpClient.newBuilder().build().send(req,
            HttpResponse.BodyHandlers.ofString());
        data = res.body();
        //konverter string av data til collection her
        String[] subjectsInArray = StringSplitter(data);
        for (String subString : subjectsInArray) {
          System.out.println(subString);
          ObjectMapper objectMapper2 = new ObjectMapper();
          Subject sub = objectMapper2.readValue(subString, Subject.class);
          subjects.add(sub);
        }
      } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Server not running");
      }
    }
    return subjects;
  }

  public boolean Post(Collection<Subject> subs) throws URISyntaxException, JsonProcessingException {
    URI newUri = new URI("http://localhost:8080/data");
    ObjectMapper objectMapper = new ObjectMapper();
    String jsondata = objectMapper.writeValueAsString(subs);
    try {
      final HttpRequest req = HttpRequest.newBuilder(newUri).header("Accept", "application/json")
          .header("Content-Type", "application/json").POST(BodyPublishers.ofString(jsondata)).build();
      final HttpResponse<String> res = HttpClient.newBuilder().build().send(req, HttpResponse.BodyHandlers.ofString());
      Boolean successfullyAdded = objectMapper.readValue(res.body(), Boolean.class);
      if (successfullyAdded != null && successfullyAdded) {
        System.out.println("Sucsesfullt posted colectrion of subjects 🙂");
        return true;
      }
      return false;
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Server not running");
    }

  }

  /**
   * Converts the whole string from rest server til array filled with the subjects in string format
   *
   * @param loongboooi string to convert
   * @return betterbois, array of strings
   *
   */
  public String[] StringSplitter(String loongboooi) {
    String[] shorterboois = loongboooi.split("}");
    String[] betterbois = new String[shorterboois.length - 1];

    for (int i = 0; i < betterbois.length; i++) {
      betterbois[i] = shorterboois[i].substring(1) + "}";
    }

    return betterbois;
  }

  // Method for loading the saved subjects
  // goes through json-folder, file for file using a for-loop
  // reads json-file, creates instance of the subject and adds it to the collection of subjects
  // returns the list of subjects
  public Collection<Subject> readFromJson() throws FileNotFoundException, IOException {

    File f = new File("../core/src/json");
    File filesList[] = f.listFiles();
    for (File file : filesList) {
      ObjectMapper objectMapper = new ObjectMapper();
      Subject sub = objectMapper.readValue(file, Subject.class);
      subjects.add(sub);
    }

    return subjects;

  }


}
