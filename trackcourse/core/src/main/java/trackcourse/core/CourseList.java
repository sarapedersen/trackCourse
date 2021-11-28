package trackcourse.core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.List;


public class CourseList {

    //Once initialized (updateMap), this hashmap will contain all the courses avaiable on NTNU, and their respective coursecodes.
    final static Map<String, String> courseList = new HashMap<>();
    

    //The validate function checks whether or not a given coursecode exists in the database (hashmap). 
    public static boolean validate(String course) throws IOException {
       try {
            if (getFullName(course).equals(null)) {
                return false;
            }
            
       }
        catch (Exception e) {
            return false;
        }
        return true;
    }


    public static String getFullName(String key) throws IOException {
        updateMap();
        return courseList.get(key) ;
        }

    private static void updateMap() throws IOException {
         //This code might seem a little confusing at first, but after looking at the courseList.txt file it should be clear.

         //The courseList.txt file is the raw output from a python script that iterated through NTNTus webpage to retrieve coursenames and codes.
         //It is written like this:   COURSECODE /n COURSENAME  and so on.
         //The for loop below simply iterates over it and creates a hashmap from the file
        if (courseList.isEmpty()) {
            Path path = Paths.get("../core/src/main/java/trackcourse/core/courseList.txt");
	        List<String> lines = Files.readAllLines(path);
            
            String type = "code";
            String putcode = "";
            String putname = "";
            int count = 0;
            for (String line : lines) {
                if (type == "code") {
                    putcode = line;
                    type = "name";
                    count += 1;
                }
                else if (type == "name") {
                    putname = line;
                    type = "code";
                    count += 1;
                }
                if (count == 2) {
                    count = 0;
                    courseList.put(putcode, putname);
                }
            }
        }
    }





}

