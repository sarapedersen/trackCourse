package trackcourse.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import java.util.HashMap;

public class Subject {

    private String name;
    private final Map<String, Double> ratings = new HashMap<>();
    private final Collection<Integer> difficulty = new ArrayList<>();
    private final Collection<Integer> timeconsumption = new ArrayList<>();
    private final Collection<Integer> entertainment = new ArrayList<>();

    public Subject(String name) {
        this.name = name;
        ratings.put("difficulty", 0.0);
        ratings.put("timeconsumption", 0.0);
        ratings.put("entertainment", 0.0);
        ratings.put("numDifficulty", 0.0);
        ratings.put("numTimeconsumption", 0.0);
        ratings.put("numEntertainment", 0.0);
    }

    public Subject() {
        
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public double getDifficulty() {
        return ratings.get("difficulty");
    }
    public void setDifficulty(double diff){
        ratings.put("difficulty", diff);
    }


    public double getTimeconsumption() {
        return ratings.get("timeconsumption");
    }
    public void setTimeconsumption(double time){
        ratings.put("timeconsumption", time);
    }


    public double getEntertainment() {
        return ratings.get("entertainment");
    }
    public void setEntertainment(double entertainment) {
        ratings.put("entertainment", entertainment);
    }
    

    public double getNumDifficulty() {
        return ratings.get("numDifficulty");
    }
    public void setNumDifficulty(double numDiff) {
        ratings.put("numDifficulty", numDiff);
    }

    public double getNumTimeconsumption() {
        return ratings.get("numTimeconsumption");
    }
    public void setNumTimeconsumption(double numTime) {
        ratings.put("numTimeconsumption", numTime);
    }

    public double getNumEntertainment() {
        return ratings.get("numEntertainment");
    }
    public void setNumEntertainment(double numEntertainment) {
        ratings.put("numEntertainment", numEntertainment);
    }

    public Collection<Integer> getDiffRatings() {
        Collection<Integer> returnDiff = new ArrayList<>();
        returnDiff.addAll(difficulty);
        return returnDiff;
    }

    public Collection<Integer> getTimeRatings() {
        Collection<Integer> returnTime = new ArrayList<>();
        returnTime.addAll(timeconsumption);
        return returnTime;
    }

    public Collection<Integer> getEntRatings() {
        Collection<Integer> returnRating = new ArrayList<>();
        returnRating.addAll(entertainment);
        return returnRating;

    }

    public void setDiffRatings(Collection<Integer> diffRatings) {
        difficulty.clear();
        difficulty.addAll(diffRatings);
    }

    public void setTimeRatings(Collection<Integer> timeRatings) {
        timeconsumption.clear();
        timeconsumption.addAll(timeRatings);
    }

    public void setEntRatings(Collection<Integer> entRatings) {
        entertainment.clear();
        entertainment.addAll(entRatings);
    }

    public void updateDifficulty(int grade) {
        if (grade < 1 || grade > 20) {
            throw new IllegalArgumentException("not valid input");
        }
        this.difficulty.add(grade);
        double diff = getDifficulty();
        double num = getNumDifficulty();
        ratings.put("difficulty", ((diff * num) + grade) / (num + 1));
        ratings.put("numDifficulty", num+1);
    }

    public void updateTimeconsumption(int grade) {
        if (grade < 1 || grade > 20) {
            throw new IllegalArgumentException("not valid input");
        }
        this.timeconsumption.add(grade);
        double time = getTimeconsumption();
        double num = getNumTimeconsumption();
        ratings.put("timeconsumption", ((time * num) + grade) / (num + 1));
        ratings.put("numTimeconsumption", num+1);
    }

    public void updateEntertainment(int grade) {
        if (grade < 1 || grade > 20) {
            throw new IllegalArgumentException("not valid input");
        }
        this.entertainment.add(grade);
        double ent = getEntertainment();
        double num = getNumEntertainment();
        ratings.put("entertainment", ((ent * num) + grade) / (num + 1));
        ratings.put("numEntertainment", num + 1);
    }

    public double average() {
        return ((ratings.get("difficulty") + ratings.get("timeconsumption") + ratings.get("entertainment")) / 3);
    }

    

    
}
