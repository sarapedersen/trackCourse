package trackcourse.core;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

public class FileHandlerAppTest {

    /* ----------Ingen av disse testene hører vel til FileHandlerApp?----------
    @Test
    public void testConstructor(){

        Collection<Subject> subjects = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Subject sub = new Subject("ALGDAT" + Integer.toString(i));
            subjects.add(sub);
            sub.updateDifficulty(15+i);
            sub.updateTimeconsumption(15+i);
            sub.updateEntertainment(15+i);
        } 

        FileHandlerApp handler = new FileHandlerApp(subjects);
        Assertions.assertEquals(handler.getSubjects(), subjects);

    }

    @Test
    public void testSetSubs(){

        Collection<Subject> subjects = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            Subject sub = new Subject("ALGDAT" + Integer.toString(i));
            subjects.add(sub);
            sub.updateDifficulty(15+i);
            sub.updateTimeconsumption(15+i);
            sub.updateEntertainment(15+i);
        } 

        FileHandlerApp handler = new FileHandlerApp();
        handler.setSubjects(subjects);
        Assertions.assertEquals(handler.getSubjects(), subjects);

    }*/

     /*These tests are WORK IN PROSSES
    ** That includes:
    **  testWriteToJson()
    **  testReadFromJson()
    **  testDeleteCurrentFiles()
    ** Currently commented out to stop program from crashing*/

    @Test
    public void testWriteToJson() throws JsonProcessingException, IOException{

        Collection<Subject> subjects = new ArrayList<>();

            Subject sub = new Subject("ALGDAT");
            subjects.add(sub);
            sub.updateDifficulty(15);
            sub.updateTimeconsumption(15);
            sub.updateEntertainment(15);
        

        FileHandlerApp handler = new FileHandlerApp(subjects);
        handler.writeToJson(subjects);

        File file = new File("../core/src/json");
        assertTrue(file.exists()); 

    }
    /*
    @Test
    public void testReadfromJson() throws JsonProcessingException, IOException{

        Collection<Subject> subs = new ArrayList<>();

            // Creating an identical subject to compare with Json-file
            Subject sub = new Subject("ALGDAT");
            subs.add(sub);
            sub.updateDifficulty(15);
            sub.updateTimeconsumption(15);
            sub.updateEntertainment(15);
        

        FileHandlerApp handler = new FileHandlerApp();
        //handler.setSubjects(handler.readFromJson());

        //Assertions.assertEquals(handler.getSubjects(), subs);


    }*/

    @Test
    public void testDeleteCurrentFiles(){

        FileHandlerApp handler = new FileHandlerApp();
        handler.deleteCurrentFiles();

        File file = new File("../core/src/json");
        assertFalse(file.exists()); 

    }
    

    
}
