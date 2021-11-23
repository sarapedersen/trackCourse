package trackcourse.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import java.io.FileOutputStream;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
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

    System.out.println("trying to post to server...");
    Post("Saved");
    System.out.println("done sending.");
  }

  public boolean Post(String str) throws URISyntaxException {
    URI newUri = new URI("http://localhost:8080/data");
    System.out.println("Strarting to send http");
    try {
      final HttpRequest req = HttpRequest.newBuilder(newUri).header("Accept", "application/json")
          .header("Content-Type", "application/json").POST(BodyPublishers.ofString(str)).build();
      final HttpResponse<String> res = HttpClient.newBuilder().build().send(req, HttpResponse.BodyHandlers.ofString());
      System.out.println("Sycsessfully sendt http post");
      return true;
    } catch (IOException | InterruptedException e) {
      System.out.println("Http request failed");
      throw new RuntimeException(e);
    }

    TrackcourseController.writeData(data);

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
