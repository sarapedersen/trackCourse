package trackcourse.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackcourse.core.Subject;

public class SubjectTest {
    private Subject testSubject;
    private String testCode = "IT1901";

    @BeforeEach
    public void setup() {
        testSubject = new Subject(testCode);
    }

    @Test
    public void testConstructor() {
        testSubject = new Subject("TDT4106");
        Assertions.assertEquals(testSubject.getName(), "TDT4106");
        Assertions.assertEquals(testSubject.getDifficulty(), 0.0);
        Assertions.assertEquals(testSubject.getEntertainment(), 0.0);
        Assertions.assertEquals(testSubject.getTimeconsumption(), 0.0);
        Assertions.assertEquals(testSubject.getNumDifficulty(), 0);
        Assertions.assertEquals(testSubject.getNumEntertainment(), 0);
        Assertions.assertEquals(testSubject.getNumTimeconsumption(), 0);
    }
    
    @Test
    public void testSetName(){
        testSubject.setName("IT2805");
        Assertions.assertFalse(testSubject.getName() == testCode);
        Assertions.assertEquals(testSubject.getName(), "IT2805");
        
    }
    
    @Test
    public void testUpdateFunctions() {
    testSubject.updateDifficulty(5);
    Assertions.assertEquals(testSubject.getDifficulty(), 5);
    testSubject.updateDifficulty(6);
    Assertions.assertFalse(testSubject.getDifficulty() == 5);
    testSubject.updateTimeconsumption(3);
    Assertions.assertEquals(testSubject.getTimeconsumption(), 3);
    testSubject.updateEntertainment(3);
    Assertions.assertEquals(testSubject.getEntertainment(), 3);
    
    }

    @Test
    public void testSetDiff(){
        testSubject.setDifficulty(3);
        Assertions.assertEquals(testSubject.getDifficulty(), 3);
        
    }

    @Test
    public void testSetTime(){
        testSubject.setTimeconsumption(5);
        Assertions.assertEquals(testSubject.getTimeconsumption(), 5);

    }

    @Test
    public void testSetEnt(){
        testSubject.setEntertainment(9);
        Assertions.assertEquals(testSubject.getEntertainment(), 9);

    }

    @Test
    public void testAverage(){
        testSubject.updateDifficulty(16);
        testSubject.updateTimeconsumption(15);
        testSubject.updateEntertainment(20);
        Assertions.assertEquals(testSubject.average(), 17);

    }
}

