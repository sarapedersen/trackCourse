package trackcourse.core;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.HashMap;
import java.util.List;




public class CourseList {

    final static Map<String, String> courseList = new HashMap<>();
    


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
            //System.out.println(courseList);

        }
    }





}

