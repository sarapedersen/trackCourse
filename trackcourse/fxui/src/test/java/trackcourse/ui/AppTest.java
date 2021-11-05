package trackcourse.ui;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppTest extends ApplicationTest{

    private AppController controller;

  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("App.fxml"));
    final Parent parent = fxmlLoader.load();
    controller = fxmlLoader.getController();
    stage.setScene(new Scene(parent));
    stage.show();
  }

  @Test
  public void testController() {
    Assertions.assertNotNull(this.controller);
  }

}
