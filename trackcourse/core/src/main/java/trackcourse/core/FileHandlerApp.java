package trackcourse.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import javax.swing.event.SwingPropertyChangeSupport;

import java.io.FileOutputStream;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.internal.Paths;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

public class FileHandlerApp {

  private Collection<Subject> subjects = new ArrayList<>();


  public FileHandlerApp(Collection<Subject> subs) {
    this.subjects = subs;
    }

  public FileHandlerApp() {

  }

  public void setSubjects(Collection<Subject> subs) {
    this.subjects = subs;
  }

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
        String[] subjectsInArray = StingSplitter(data);
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
  public String[] StingSplitter(String loongboooi) {
    String[] shorterboois = loongboooi.split("}");
    String[] betterbois = new String[shorterboois.length - 1];

    for (int i = 0; i < betterbois.length; i++) {
      betterbois[i] = shorterboois[i].substring(1) + "}";
    }

    return betterbois;
  }

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


  public void deleteCurrentFiles() {
    File f = new File("../core/src/json");
    File filesList[] = f.listFiles();
    for (File file : filesList) {
      if (!file.isDirectory()) {
        boolean success = file.delete();
        if (success) {
          System.out.println("File deleted.");
        } else {
          System.out.println("File could not be deleted.");
        }
      }
    }
  }

}
