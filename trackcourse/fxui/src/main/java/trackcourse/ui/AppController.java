package trackcourse.ui;

import java.util.Collection;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.fxml.FXML;

import trackcourse.core.Subject;
import trackcourse.core.FileHandlerApp;

public class AppController{

    private Collection<Subject> subjects = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("##.#");

    
    
    @FXML TextField nameInput;
    @FXML Slider diffSlider;
    @FXML Slider timeSlider;
    @FXML Slider happySlider;
    @FXML ListView nameList;
    @FXML ListView averageList;


    @FXML
    void subjectSelected(){
        String selected = (String) nameList.getSelectionModel().getSelectedItem();
        Subject subject = subjectDetails(selected);
        nameInput.setText(subject.getName());
        /*subjectDetails
        nameInput.setText(nameList.getItems().toString());*/
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
    }

    @FXML
    void onDetails() {
        String selected = (String) nameList.getSelectionModel().getSelectedItem();
        Subject subject = subjectDetails(selected);

        
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame,
        "Average difficulty: " + df.format(subject.getDifficulty()) + "\n\n"
        + "Average timeconsuption: " + df.format(subject.getTimeconsumption()) + "\n\n"
        + "Average joy: " + df.format(subject.getEntertainment()) + "\n\n"
        + "Average overall: " + df.format(subject.average()) + "\n\n",
        subject.getName(),
        JOptionPane.INFORMATION_MESSAGE);
/*
        Dialog<Void> subjectPopUp = new Dialog<Void>();
        subjectPopUp.setTitle(subject.getName());
        subjectPopUp.setContentText("Name: " + subject.getName() + "\n" 
        + "Average difficulty: " + df.format(subject.getDifficulty()) + "\n"
        + "Average timeconsuption: " + df.format(subject.getTimeconsumption()) + "\n"
        + "Average joy: " + df.format(subject.getEntertainment()) + "\n"
        + "Average overall: " + df.format(subject.average()));
        subjectPopUp.showAndWait();*/
    }

    @FXML 
    void onSave() throws JsonProcessingException, IOException {

            FileHandlerApp saver = new FileHandlerApp(subjects); 
            saver.deleteCurrentFiles();    
            saver.writeToJson(subjects);  
    }

    @FXML
    void onLoad() throws FileNotFoundException, IOException {
        FileHandlerApp loader = new FileHandlerApp();
        subjects = loader.readFromJson();
        updateLists();
        System.out.println(subjects);
           
    }

    @FXML
    void onReset() {

        subjects.clear();
        updateLists();    
    }
    
    @FXML
    void submit(){
        // Checks if the subject already have been submitted or loaded
        Subject sub = null;
        for (Subject subject : subjects) {
            if (subject.getName().equals(nameInput.getText())) {
                sub = subject;
            }
        }

        // If the subject doesnt already exist, add it to the collection of subjects
        if (sub == null) {
            sub = new Subject(nameInput.getText());
            subjects.add(sub);
        }

    
        // Updates the parameters
        sub.updateDifficulty((int) diffSlider.getValue());
        sub.updateEntertainment((int) happySlider.getValue());
        sub.updateTimeconsumption((int) timeSlider.getValue());

        // Updates the list views
        updateLists();
        System.out.println(subjects);

    }

    @FXML
    void updateLists(){

        // Makes it write the average score in the format XX,X
        // For example 13,2
        DecimalFormat df = new DecimalFormat("##.#");

        // Creating empty FXCollection.obserableArrayLists for the Names and the average score
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        ObservableList<String> subjectAverage = FXCollections.observableArrayList();

        // Sorts the existing subjects by highest score.
        
        // Adding the name and the corresponding average score to the lists
        for (Subject subject : subjects) {
            subjectNames.add(subject.getName());
            subjectAverage.add(String.valueOf(df.format(subject.average())));
        }
       
        // Adding the names to the list view
        nameList.setItems(subjectNames);

        // Addomg the average score to the list view
        averageList.setItems(subjectAverage);



      

    }

    public Subject subjectDetails(String name) {
        Subject subject = null;
        for (Subject sub : subjects) {
            if (sub.getName().equals(name)) {
                subject = sub;
                break;
            }
        }
        return subject;
    } 

}
