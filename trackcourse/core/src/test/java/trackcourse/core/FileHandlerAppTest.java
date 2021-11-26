package trackcourse.core;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

public class FileHandlerAppTest {

    @Test
    public void testWriteToJson() throws JsonProcessingException, IOException, URISyntaxException{

        Collection<Subject> subjects = new ArrayList<>();

            Subject sub = new Subject("TDT4100");
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
    public void testReadfromJson() throws JsonProcessingException, IOException, URISyntaxException{

        Collection<Subject> subs = new ArrayList<>();

        // Creating an identical subject to compare with Json-file
        Subject sub = new Subject("TDT4100");
        subs.add(sub);
        sub.updateDifficulty(15);
        sub.updateTimeconsumption(15);
        sub.updateEntertainment(15);
    
        
        FileHandlerApp handler = new FileHandlerApp();
        handler.writeToJson(subs);

        File file = new File("../core/src/json/TDT4100.json");
        //Checks if file exists
        if (file.exists()) {
            //Check if file has content
            assertFalse(file.length()==0);
        }
    }

    

    
}
