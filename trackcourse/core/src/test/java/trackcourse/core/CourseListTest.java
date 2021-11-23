package trackcourse.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class CourseListTest {
    private String courseCode = "SMF2295";
    private String fullName = "Makroøkonomi";

    @Test
    public void testValidate() throws IOException {
        //tests if course is in courseList.txt
        assertTrue(CourseList.validate(courseCode));
    }

    @Test
    public void testNonExistingCourse() throws IOException {
        //test if function throws exception on wrong course code 
        assertFalse(CourseList.validate(("SØK5001")));
    }

    @Test
    public void testGetFullName() throws IOException {
        //if test does not fail, updateMap function is also working
        assertEquals(CourseList.getFullName(courseCode), fullName);
    }


}
