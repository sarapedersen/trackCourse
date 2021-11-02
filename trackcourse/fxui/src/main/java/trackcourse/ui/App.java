package trackcourse.ui;

import javafx.application.Application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class App extends Application {
    
    @Override
	public void start(final Stage primaryStage) throws IOException{
		primaryStage.setTitle("Trackcourse");
		primaryStage.setScene(new Scene(FXMLLoader.load(App.class.getResource("App.fxml"))));
		primaryStage.getIcons().add(new Image("https://i.imgur.com/BDdmb8n.png"));
		primaryStage.show();
	}

	public static void main(String[] args) {
		App.launch(args);
		}
}
