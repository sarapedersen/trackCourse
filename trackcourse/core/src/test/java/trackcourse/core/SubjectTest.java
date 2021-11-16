package trackcourse.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import trackcourse.core.Subject;

public class SubjectTest {
    /* Avventer endringer i Subjects fra Casper
    private Subject testSubject;
    private String testCode = "TDT4105";
    private String testName = "ITGK";

    
    @BeforeEach
    public void setup() {
        testSubject = new Subject(testCode);
        testSubject.setFullName(testName);
    }

    @Test
    public void testConstructor() {
        testSubject = new Subject("TMA4100");
        Assertions.assertEquals(testSubject.getCourseCode(), "TMA4100");
        Assertions.assertEquals(testSubject.getDifficulty(), 0.0);
        Assertions.assertEquals(testSubject.getEntertainment(), 0.0);
        Assertions.assertEquals(testSubject.getTimeconsumption(), 0.0);
        Assertions.assertEquals(testSubject.getNumDifficulty(), 0);
        Assertions.assertEquals(testSubject.getNumEntertainment(), 0);
        Assertions.assertEquals(testSubject.getNumTimeconsumption(), 0);
    }
    
    @Test
    public void testSetCourseCode(){
        testSubject.setCourseCode("IT2805");
        Assertions.assertFalse(testSubject.getCourseCode() == testCode);
        Assertions.assertEquals(testSubject.getCourseCode(), "IT2805");
        
    }

    @Test
    public void testSetFullName(){
        testSubject.setFullName("Matematikk 1");
        Assertions.assertFalse(testSubject.getFullName() == testName);
        Assertions.assertEquals(testSubject.getFullName(), "Matematikk 1");
        
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

    }*/
}

