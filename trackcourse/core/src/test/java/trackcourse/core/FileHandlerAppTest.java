package trackcourse.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class FileHandlerAppTest {

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
        handler.writeToJson(subs);

        File file = new File("../core/src/json/ALGDAT.json");
        //Checks if file exists
        if (file.exists()) {
            //Check if file has content
            assertFalse(file.length()==0);
        }
    }

    @Test
    public void testDeleteCurrentFiles(){

        FileHandlerApp handler = new FileHandlerApp();
        handler.deleteCurrentFiles();

        File file = new File("../core/src/json");
        assertTrue(checkIfDirectoryIsEmpty(file)); 

    }

    public Boolean checkIfDirectoryIsEmpty(File file) {
        if (file.isDirectory()) {
            if (file.list().length>0) {
                return false;
            } else {
                return true;
            }
        } else {
            System.out.println("Path is not a directory!");
            return false;
        }
    }
    

    
}
