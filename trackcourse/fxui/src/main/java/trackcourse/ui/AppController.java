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
import trackcourse.core.FileHandlerApp;

public class AppController {

    private Collection<Subject> subjects = new ArrayList<>();

    @FXML
    TextField nameInput;
    @FXML
    Slider diffSlider;
    @FXML
    Slider timeSlider;
    @FXML
    Slider happySlider;
    @FXML
    ListView nameList;
    @FXML
    ListView averageList;

    public Collection<Subject> getSubjects() {
        return this.subjects;
    }

    public void setSubjects(Collection<Subject> subs) {
        this.subjects = subs;
    }

    @FXML
    void onSave() throws JsonProcessingException, IOException {

        FileHandlerApp saver = new FileHandlerApp(subjects);
        saver.deleteCurrentFiles();
        saver.writeToJson(subjects);

        URI uri = null;
        try {
            uri = new URI("http://localhost:8080/courses");
        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }

        HttpRequest req = HttpRequest.newBuilder(uri).header("Accept", "application/json")
                .header("Content-Type", "application/json").POST(BodyPublishers.ofString("WHOOOHOOOOO")).build();

        try {
            HttpClient.newBuilder().build().send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            System.out.println("EROOR!");
            throw new RuntimeException(e);
        }

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
    void submit() {
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
            subjectNames.add(subject.getName());
            subjectAverage.add(String.valueOf(df.format(subject.average())));
        }

        // Adding the names to the list view
        nameList.setItems(subjectNames);

        // Addomg the average score to the list view
        averageList.setItems(subjectAverage);

    }

    @FXML
    void onDetails() {
    }

    @FXML
    void subjectSelected() {
    }

}
