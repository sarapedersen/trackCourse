package trackcourse.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trackcourse.core.Subject;

public class SubjectTest {

    @Test
    public void testConstructor() {
        Subject sub = new Subject("ITGK");
        Assertions.assertEquals(sub.getName(), "ITGK");
        Assertions.assertEquals(sub.getDifficulty(), 0.0);
        Assertions.assertEquals(sub.getEntertainment(), 0.0);
        Assertions.assertEquals(sub.getTimeconsumption(), 0.0);
        Assertions.assertEquals(sub.getNumDifficulty(), 0);
        Assertions.assertEquals(sub.getNumEntertainment(), 0);
        Assertions.assertEquals(sub.getNumTimeconsumption(), 0);
    }
    
    
    @Test
    public void testUpdateFunctions() {
    Subject sub = new Subject("Math");
    Assertions.assertEquals(sub.getName(), "Math");
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
    public void testSetName(){

        Subject sub = new Subject("Math");
        sub.setName("ALGDAT");
        Assertions.assertFalse(sub.getName() == "Math");
        Assertions.assertEquals(sub.getName(), "ALGDAT");
        
    }

    @Test
    public void testSetDiff(){

        Subject sub = new Subject("Math");
        sub.setDifficulty(3);
        Assertions.assertEquals(sub.getDifficulty(), 3);
        
    }

    @Test
    public void testSetTime(){

        Subject sub = new Subject("Math");
        sub.setTimeconsumption(5);
        Assertions.assertEquals(sub.getTimeconsumption(), 5);

    }

    @Test
    public void testSetEnt(){

        Subject sub = new Subject("Math");
        sub.setEntertainment(9);
        Assertions.assertEquals(sub.getEntertainment(), 9);

    }

    @Test
    public void testAverage(){
        Subject sub = new Subject("IT1901");
    
        sub.updateDifficulty(16);
        sub.updateTimeconsumption(15);
        sub.updateEntertainment(20);
        Assertions.assertEquals(sub.average(), 17);

    }


    

}

