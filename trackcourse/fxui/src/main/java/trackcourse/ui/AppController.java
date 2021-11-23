package trackcourse.ui;

import java.util.Collection;
import java.util.logging.FileHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.net.http.HttpRequest.BodyPublishers;
import java.text.DecimalFormat;

import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.fxml.FXML;

import trackcourse.core.Subject;
import trackcourse.core.CourseList;
import trackcourse.core.FileHandlerApp;

public class AppController {

    private Collection<Subject> subjects = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("##.#");

    
    
    @FXML TextField nameInput;
    @FXML Slider diffSlider;
    @FXML Slider timeSlider;
    @FXML Slider happySlider;
    @FXML ListView nameList;
    @FXML ListView averageList;
    @FXML Label courseError, preview;
    @FXML Button submitButton;
    @FXML ListView subjectListView;


    @FXML
    void subjectSelected(){
        //String selected = (String) subjectListView.getSelectionModel().getSelectedItem();
        String selected = (String) subjectListView.getSelectionModel().getSelectedItem();
        String[] splitted = selected.split("\\s+");
        Subject subject = subjectDetails(splitted[0]);
        nameInput.setText(subject.getCourseCode());
        try {
			validate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*subjectDetails
        nameInput.setText(nameList.getItems().toString());*/
    }

    public Collection<Subject> getSubjects() {
        return this.subjects;
    }

    @FXML
    void onDetails() {
        String selected = (String) subjectListView.getSelectionModel().getSelectedItem();
        String[] splitted = selected.split("\\s+");
        Subject subject = subjectDetails(splitted[0]);
        String message = "Average difficulty: " + df.format(subject.getDifficulty()) + "\n\n"
        + "Average timeconsuption: " + df.format(subject.getTimeconsumption()) + "\n\n"
        + "Average joy: " + df.format(subject.getEntertainment()) + "\n\n"
        + "Average overall: " + df.format(subject.average()) + "\n\n";

        JOptionPane.showMessageDialog(null, message, subject.getCourseCode(), JOptionPane.INFORMATION_MESSAGE);

       /* Dialog<Void> subjectPopUp = new Dialog<Void>();
        subjectPopUp.setTitle(subject.getCourseCode());
        subjectPopUp.setContentText("Name: " + subject.getFullName() + "\n" 
        + "Average difficulty: " + df.format(subject.getDifficulty()) + "\n"
        + "Average timeconsuption: " + df.format(subject.getTimeconsumption()) + "\n"
        + "Average joy: " + df.format(subject.getEntertainment()) + "\n"
        + "Average overall: " + df.format(subject.average()));
        subjectPopUp.show();*/  
    }

    @FXML 
    void onSave() throws JsonProcessingException, IOException, InterruptedException {

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
    void validate() throws IOException {
        if (CourseList.validate(nameInput.getText().toUpperCase())) {
            courseError.setText("");
            preview.setText(CourseList.getFullName(nameInput.getText().toUpperCase()));
            submitButton.setVisible(true);
        }
        else {
            courseError.setText("*");
            preview.setText("");
            submitButton.setVisible(false);
            //System.out.println(CourseList.getFullName(nameInput.getText()));

        }

    }


    @FXML
    void onReset() {

        subjects.clear();
        updateLists();
    }

    @FXML
    void submit() throws IOException{
        // Checks if the subject already have been submitted or loaded
        Subject sub = null;
        for (Subject subject : subjects) {
            if (subject.getCourseCode().equals(nameInput.getText().toUpperCase())) {
                sub = subject;
            }
        }

        // If the subject doesnt already exist, add it to the collection of subjects
        if (sub == null) {
            sub = new Subject(nameInput.getText().toUpperCase());
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
    void updateLists() {

        // Makes it write the average score in the format XX,X
        // For example 13,2
        DecimalFormat df = new DecimalFormat("##.#");

        // Creating empty FXCollection.obserableArrayLists for the Names and the average
        // score
        ObservableList<String> subjectNames = FXCollections.observableArrayList();
        ObservableList<String> subjectAverage = FXCollections.observableArrayList();

        // Adding the name and the corresponding average score to the lists
        for (Subject subject : subjects) {
            
            subjects4ListView.add(subject.getCourseCode() + " // " + String.valueOf(df.format(subject.average())));
        }

        // Adding the names to the list view
        nameList.setItems(subjectNames);

        // Addomg the average score to the list view
        averageList.setItems(subjectAverage);

    }

    public Subject subjectDetails(String name) {
        Subject subject = null;
        for (Subject sub : subjects) {
            if (sub.getCourseCode().equals(name.toUpperCase())) {
                subject = sub;
                break;
            }
        }
        return subject;
    } 

}
