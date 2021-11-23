package trackcourse.core;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trackcourse.core.Subject;

public class SubjectTest {

    @Test
    public void testConstructor() throws IOException {
        Subject sub = new Subject("TDT4100");
        Assertions.assertEquals(sub.getCourseCode(), "TDT4100");
        Assertions.assertEquals(sub.getDifficulty(), 0.0);
        Assertions.assertEquals(sub.getEntertainment(), 0.0);
        Assertions.assertEquals(sub.getTimeconsumption(), 0.0);
        Assertions.assertEquals(sub.getNumDifficulty(), 0);
        Assertions.assertEquals(sub.getNumEntertainment(), 0);
        Assertions.assertEquals(sub.getNumTimeconsumption(), 0);
    }
    
    
    @Test
    public void testUpdateFunctions() throws IOException {
    Subject sub = new Subject("Math");
    Assertions.assertEquals(sub.getCourseCode(), "Math");
    sub.updateDifficulty(5);
    Assertions.assertEquals(sub.getDifficulty(), 5);
    sub.updateDifficulty(6);
    Assertions.assertFalse(sub.getDifficulty() == 5);
    sub.updateTimeconsumption(3);
    Assertions.assertEquals(sub.getTimeconsumption(), 3);
    sub.updateEntertainment(3);
    Assertions.assertEquals(sub.getEntertainment(), 3);
    
    }

    

    @Test
    public void testSetName() throws IOException{

        Subject sub = new Subject("Math");
        sub.setCourseCode("ALGDAT");
        Assertions.assertFalse(sub.getCourseCode() == "Math");
        Assertions.assertEquals(sub.getCourseCode(), "ALGDAT");
        
    }

    @Test
    public void testSetDiff() throws IOException{

        Subject sub = new Subject("Math");
        sub.setDifficulty(3);
        Assertions.assertEquals(sub.getDifficulty(), 3);
        
    }

    @Test
    public void testSetTime() throws IOException{

        Subject sub = new Subject("Math");
        sub.setTimeconsumption(5);
        Assertions.assertEquals(sub.getTimeconsumption(), 5);

    }

    @Test
    public void testSetEnt() throws IOException{

        Subject sub = new Subject("Math");
        sub.setEntertainment(9);
        Assertions.assertEquals(sub.getEntertainment(), 9);

    }

    @Test
    public void testAverage() throws IOException{
        Subject sub = new Subject("IT1901");
    
        sub.updateDifficulty(16);
        sub.updateTimeconsumption(15);
        sub.updateEntertainment(20);
        Assertions.assertEquals(sub.average(), 17);

    }


    

}

