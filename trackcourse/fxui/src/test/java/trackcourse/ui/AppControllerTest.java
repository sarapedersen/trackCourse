package trackcourse.ui;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobotContext;
import org.testfx.api.FxRobotException;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.ListViewMatchers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import trackcourse.core.Subject;
import trackcourse.ui.AppController;

public class AppControllerTest extends ApplicationTest {

    @FXML TextField nameInput;
    @FXML Slider diffSlider;
    @FXML Slider timeSlider;
    @FXML Slider happySlider;
    @FXML Button submitButton;
    @FXML Button saveButton;

    private AppController controller;
    public Collection<Subject> presaved_subs;
    private Subject sub;
    private Collection<Subject> subjects;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
        final Parent parent = fxmlLoader.load();
        controller = fxmlLoader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void init() throws IOException{
        sub = new Subject("TDT4100");
        subjects = new ArrayList<>();
        subjects.add(sub);
    }

    @Test
    public void testController() {
        Assertions.assertNotNull(this.controller);
    }

    @Test
    public void testAddSubject() {
        clickOn("#nameInput").write("TDT4100");
        clickOn("#submitButton");
        FxAssert.verifyThat("#subjectListView", ListViewMatchers.hasListCell(("TDT4100 // 5")));
        //Default value for sliders are 5, so avg. will always be 5 in this case
    }

    @Test 
    public void testSliders(){
        clickOn("#nameInput").write("TDT4100");
        controller.diffSlider.setValue(6);
        controller.timeSlider.setValue(6);
        controller.happySlider.setValue(6);

        clickOn("#submitButton");

        FxAssert.verifyThat("#subjectListView", ListViewMatchers.hasListCell(("TDT4100 // 6")));

        clickOn("#saveButton"); 
        

        
    }

    @Test 
    public void testUpdate(){
        clickOn("#loadButton"); 
        clickOn("#nameInput").write("TDT4100");
        controller.diffSlider.setValue(8);
        controller.timeSlider.setValue(8);
        controller.happySlider.setValue(8);
        clickOn("#submitButton");
        clickOn("#saveButton");

        FxAssert.verifyThat("#subjectListView", ListViewMatchers.hasListCell(("TDT4100 // 11.5")));
    }

    @Test
    public void testLoad() throws JsonProcessingException, IOException{
        clickOn("#loadButton");
        FxAssert.verifyThat("#subjectListView", ListViewMatchers.hasListCell(("TDT4100 // 15")));

    }

   
    @Test
    public void testIncorrectSub(){
        clickOn("#nameInput").write("ABC1234");
        Button btn = submitButton;
        assertNull(btn);

    }

   /*
    @BeforeEach
    public void init() throws IOException{
        sub = new Subject("TDT4100");
        subjects = new ArrayList<>();
        subjects.add(sub);
        presaved_subs = new ArrayList<>();
        controller.setSubjects(subjects);
    }

    @Test
    public void testSubject() throws IOException {

        subjects.add(sub);

        Assertions.assertNotNull(subjects);
        Assertions.assertEquals(subjects.size(), 1);

        
    }


    @Test
    public void testReset() throws IOException {
        subjects.add(sub); 
        System.out.println("Før reset: " + controller.getSubjects().size());
        controller.onReset();
        System.out.println("Etter reset: " + controller.getSubjects().size());
        Assertions.assertEquals(controller.getSubjects().size(), 0);
    }


    @Test
    public void testValidation() throws IOException {

        nameInput.setText("TDT4100");
        controller.submit();
        subjects.add(sub);

        Assertions.assertNotNull(subjects);
        Assertions.assertEquals(subjects.size(), 1);

    }


    //Test save function without overwriting the subjects thats already saved
    @Test
    public void testSave() throws Exception{
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
    
        Assertions.assertEquals(testSub, testSub2); 

    }




    public static String readFileAsString(String file)throws Exception
    {
        return new String(Files.readAllBytes(Paths.get(file)));
    }*/



}
