package trackcourse.ui;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import trackcourse.core.Subject;
import trackcourse.core.FileHandlerApp;
import trackcourse.ui.AppController;


public class AppControllerTest extends ApplicationTest {

    @FXML TextField nameInput;
    @FXML Slider diffSlider;
    @FXML Slider timeSlider;
    @FXML Slider happySlider;
    @FXML ListView nameList;
    @FXML ListView averageList;
    @FXML Button submitButton;
    @FXML Button saveButton;

    private AppController controller;
    public Collection<Subject> presaved_subs = new ArrayList<>();

    @Override
    public void start(final Stage stage) throws Exception {
    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
    final Parent parent = fxmlLoader.load();
    controller = fxmlLoader.getController();
    stage.setScene(new Scene(parent));
    stage.show();
    // In order not to overwrite the presaved subjects, its saved here and loaded after the tests
    presaved_subs = controller.getSubjects();
    }

    @Test
    public void testController() {
        Assertions.assertNotNull(this.controller);
    }


    @Test
    public void testSubject() {

        Collection<Subject> subjects = new ArrayList<>();

        Subject sub = new Subject("ALGDAT");
        subjects.add(sub);

        Assertions.assertNotNull(subjects);
        Assertions.assertEquals(subjects.size(), 1);

    }

    @Test
    public void testReset(){

        Collection<Subject> subjects = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Subject sub = new Subject("ALGDAT" + Integer.toString(i));
            subjects.add(sub);
            sub.updateDifficulty(15+i);
            sub.updateTimeconsumption(15+i);
            sub.updateEntertainment(15+i);
        }        
        
        controller.setSubjects(subjects);
        Assertions.assertEquals(subjects.size(), 5);
        controller.onReset();
        Assertions.assertEquals(subjects.size(), 0);

    }
/* Work in prosess
/* Test save function without overwriting the subjects thats already saved
    @Test
    public void testSave() throws Exception{

        Collection<Subject> subjects = new ArrayList<>();
        

        
            Subject sub = new Subject("ALGDAT");
            subjects.add(sub);
            sub.updateDifficulty(15);
            sub.updateTimeconsumption(15);
            sub.updateEntertainment(15);
        
        
        controller.setSubjects(subjects);
        controller.onSave();

        File file = new File("./json/ALGDAT.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Subject testSub = objectMapper.readValue(file, Subject.class);
        String jsonString = "{\"name\":\"ALGDAT\",\"difficulty\":15.0,\"timeconsumption\":15.0,\"entertainment\":15.0,\"numTimeconsumption\":1.0,\"numEntertainment\":1.0,\"numDifficulty\":1.0,\"diffRatings\":[15],\"timeRatings\":[15],\"entRatings\":[15]}";
        Subject testSub2 = objectMapper.readValue(jsonString, Subject.class);
    

        assertTrue(testSub.equals(testSub2));      


    }

    @Test
    public void testLoad() throws JsonProcessingException, IOException{

        Collection<Subject> subjects = new ArrayList<>();

        controller.onLoad();
        subjects = controller.getSubjects();
        Assertions.assertEquals(subjects.size(), 1);

        controller.setSubjects(presaved_subs);
        controller.onSave();

    }


    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }*/



}
