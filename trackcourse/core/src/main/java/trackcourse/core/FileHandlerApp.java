package trackcourse.core;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
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

  public void writeToJson(Collection<Subject> subs) throws JsonProcessingException, IOException {
    for (Subject sub : subs) {
      ObjectMapper objectMapper = new ObjectMapper(); 
      objectMapper.writeValue(
        new FileOutputStream("../core/src/json/" + sub.getName() + ".json"), sub);
      }

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

    public void deleteCurrentFiles(){
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



