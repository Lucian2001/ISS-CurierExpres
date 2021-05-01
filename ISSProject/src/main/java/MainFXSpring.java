
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFXSpring extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


/*        try {
            ScreenController.get_instance().addScreen("loginScreen", getClass().getResource("loginView.fxml"));
            ScreenController.get_instance().addScreen("mainScreen",getClass().getResource("mainView.fxml"));
            ScreenController.get_instance().activate("loginScreen", LoginController.class);
            ScreenController.get_instance().setMainStage(primaryStage);

            primaryStage.setScene(ScreenController.get_instance().getMainScene());
            primaryStage.setTitle("Aplicatie smechera");
            primaryStage.setWidth(900);
            primaryStage.setHeight(500);
            primaryStage.show();
        }catch(IOException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e.getMessage());
            alert.showAndWait();
        }*/
    }

    public static void main(String[] args) {
        Application.launch(args);
    }




}

