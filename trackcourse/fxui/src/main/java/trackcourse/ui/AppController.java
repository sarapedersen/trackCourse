package trackcourse.ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import trackcourse.core.CourseList;
import trackcourse.core.FileHandlerApp;
import trackcourse.core.Subject;

public class AppController {

    private Collection<Subject> subjects = new ArrayList<>();
    DecimalFormat df = new DecimalFormat("##.#");

    // elements for main stage
    @FXML TextField nameInput;
    @FXML Slider diffSlider;
    @FXML Slider timeSlider;
    @FXML Slider happySlider;
    @FXML ListView nameList;
    @FXML ListView averageList;
    @FXML Label courseError, preview, txtDescription;
    @FXML Button submitButton;
    @FXML ListView subjectListView;

    // elements for details stage
    @FXML Pane detailsPane;
    @FXML Pane sliderPane;
    @FXML Label txtCourseCode;
    @FXML Label detailsDiff, detailsTime, detailsAverage, detailsJoy;
    @FXML Button btnCloseDetails;


    @FXML
    void subjectSelected() {
        String selected = (String) subjectListView.getSelectionModel().getSelectedItem();
        String[] splitted = selected.split("\\s+");
        Subject subject = subjectDetails(splitted[0]);
        nameInput.setText(subject.getCourseCode());
        try {
            validate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setSubjects(Collection<Subject> subs){
        this.subjects = subs;
    }
    public Collection<Subject> getSubjects() {
        return this.subjects;
    }


    @FXML
    void onDetails() throws IOException {
        String selected = (String) subjectListView.getSelectionModel().getSelectedItem();
        if (selected==null){
            System.out.println("Select a course first");
            return;
        }
        String[] splitted = selected.split("\\s+");
        Subject subject = subjectDetails(splitted[0]);

        // hides background panes
        detailsPane.setVisible(true);
        sliderPane.setVisible(false);
        subjectListView.setVisible(false);
        txtDescription.setVisible(false);

        // fills in subject details
        txtCourseCode.setText(subject.getCourseCode());
        detailsDiff.setText(df.format(subject.getDifficulty()));
        detailsTime.setText(df.format(subject.getTimeconsumption()));
        detailsJoy.setText(df.format(subject.getEntertainment()));
        detailsAverage.setText(df.format(subject.average()));
    }


    @FXML
    void onClose() {
        // closes the details pane
        detailsPane.setVisible(false);
        sliderPane.setVisible(true);
        subjectListView.setVisible(true);
        txtDescription.setVisible(true);
    }


    @FXML
    void onSave() throws JsonProcessingException, IOException, URISyntaxException {
        FileHandlerApp saver = new FileHandlerApp();
        saver.Post(subjects);
    }


    @FXML
    void onLoad() throws FileNotFoundException, IOException, URISyntaxException {
        FileHandlerApp loader = new FileHandlerApp();
        subjects = loader.Get();
        sortSubjects();
        updateLists();
    }


    @FXML
    void validate() throws IOException {
        if (CourseList.validate(nameInput.getText().toUpperCase())) {
            courseError.setText("");
            preview.setText(CourseList.getFullName(nameInput.getText().toUpperCase()));
            submitButton.setVisible(true);
        } else {
            courseError.setText("*");
            preview.setText("");
            submitButton.setVisible(false);
        }
    }



    @FXML
    void onReset() {
        subjects.clear();
        updateLists();
    }
    

    public void sortSubjects() {
        ArrayList<Subject> sortedList = new ArrayList<>();
        for (Subject subject : subjects) {
            if (sortedList.isEmpty()) {
                sortedList.add(subject);
            } else {
                int i = 0;
                for (Subject sub : sortedList) {
                    if (sub.average() >= subject.average()) {
                        i++;
                    } else {
                        break;
                    }
                }
                sortedList.add(i, subject);
            }
        }
        this.subjects = sortedList;
    }


    @FXML
    void submit() throws IOException {
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

        sortSubjects();

        // Updates the list views
        updateLists();
        System.out.println(subjects);
    }


    @FXML
    void updateLists() {

        // Creating empty FXCollection.obserableArrayLists for the Names and the average
        // score
        ObservableList<String> subjects4ListView = FXCollections.observableArrayList();

        // Adding the name and the corresponding average score to the lists
        for (Subject subject : subjects) {

            subjects4ListView.add(subject.getCourseCode() + " // " + String.valueOf(df.format(subject.average())));
        }

        // Adding the names to the list view
        subjectListView.setItems(subjects4ListView);

    }

    // Return the subject selected by the User
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
