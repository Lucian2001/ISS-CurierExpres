
import controllers.ConfigProject;
import controllers.LoginController;
import controllers.ScreenManager;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

public class MainFXSpring extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {


        try {
            ScreenManager.get_instance().addScreen("loginScreen", getClass().getResource("loginView.fxml"));
            ScreenManager.get_instance().addScreen("bossMainScreen", getClass().getResource("BossMainView.fxml"));
            ScreenManager.get_instance().addScreen("employeeWelcomePageScreen", getClass().getResource("EmployeeWelcomePage.fxml"));
            ScreenManager.get_instance().addScreen("employeeMainScreen", getClass().getResource("EmployeeMainView.fxml"));
            ScreenManager.get_instance().addScreen("employeeDeliveryScreen", getClass().getResource("EmployeeDeliveryView.fxml"));
            ScreenManager.get_instance().addScreen("packageDetailsScreen", getClass().getResource("PackageDetailsView.fxml"));
            ScreenManager.get_instance().addScreen("employeeStatsScreen", getClass().getResource("EmployeeStatsView.fxml"));


            ScreenManager.get_instance().activate("loginScreen", LoginController.class);
            ScreenManager.get_instance().setMainStage(primaryStage);

            primaryStage.setScene(ScreenManager.get_instance().getMainScene());
            primaryStage.setTitle("Aplicatie smechera");
            primaryStage.setWidth(630);
            primaryStage.setHeight(500);
            primaryStage.show();
        }catch(IOException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error ");
            alert.setContentText("Error while starting app "+e.getMessage());
            alert.showAndWait();
        }
    }

    public static void main(String[] args) {
       Application.launch(args);

    }




}

