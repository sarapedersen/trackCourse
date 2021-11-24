package trackcourse.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SubjectTest {
    private Subject testSubject;
    private String testCode = "TDT4105";
    private String testName = "ITGK";

    private Collection<Integer> integers = new ArrayList<>();

    
    @BeforeEach
    public void setup() throws IOException {
        testSubject = new Subject(testCode);
        testSubject.setFullName(testName);
    }

    @Test
    public void testConstructor() throws IOException {
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
    public void testSubjectEmptyContructor(){
        Subject sub = new Subject();
        Assertions.assertNotNull(sub);
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

        
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateDifficulty(-5);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateDifficulty(25);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateTimeconsumption(-5);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateTimeconsumption(25);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateEntertainment(-5);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            testSubject.updateEntertainment(25);
        });
    
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
    public void testSetNumDiff(){
        testSubject.setNumDifficulty(4.0);
        Assertions.assertEquals(testSubject.getNumDifficulty(), 4.0);
    }

    @Test
    public void testSetNumTime(){
        testSubject.setNumTimeconsumption(4.0);
        Assertions.assertEquals(testSubject.getNumTimeconsumption(), 4.0);
    }

    @Test
    public void testSetNumEntertainment(){
        testSubject.setNumEntertainment(4.0);
        Assertions.assertEquals(testSubject.getNumEntertainment(), 4.0);
    }

    @Test
    public void testSetDiffRatings(){
        integers.add(5);
        integers.add(5);
        integers.add(6);
        
        testSubject.setDiffRatings(integers);
        Assertions.assertEquals(testSubject.getDiffRatings(), integers);
    }

    @Test
    public void testSetTimeRatings(){
        testSubject.setTimeRatings(integers);
        Assertions.assertEquals(testSubject.getTimeRatings(), integers);
    }

    @Test
    public void testSetEntRatings(){
        testSubject.setEntRatings(integers);
        Assertions.assertEquals(testSubject.getEntRatings(), integers);
    }

    @Test
    public void testAverage(){
        testSubject.updateDifficulty(16);
        testSubject.updateTimeconsumption(15);
        testSubject.updateEntertainment(20);
        Assertions.assertEquals(testSubject.average(), 17);

    }
}

