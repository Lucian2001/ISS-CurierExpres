package controllers;

import Service.Service;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import models.Curier;
import models.MyUser;
import models.Sef;

import java.io.IOException;

public class LoginController implements Controller{
    private Service service;

    @FXML
    TextField textFieldUsername;

    @FXML
    TextField textFieldPassword;

    public LoginController(Service service) {
        this.service = service;
    }


    @FXML
    void handleCheckUser(){
        String username = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        if(username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Login incorect");
            alert.setContentText("Completeaza cu date");
            alert.show();
            return;
        }
        MyUser user = service.login(username, password);
        if(user != null){

            if(user instanceof Sef){
                try {
                    ScreenManager.get_instance().activate("bossMainScreen", BossMainController.class);
                    ((BossMainController)ScreenManager.get_instance().getCurrentController()).setUser((Sef) user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else{
                try {
                    ScreenManager.get_instance().activate("employeeWelcomePageScreen", EmployeeWelcomePageController.class);
                    ((EmployeeWelcomePageController)ScreenManager.get_instance().getCurrentController()).setUser((Curier) user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }





        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Login incorect");
            alert.setContentText("Date incorecte!");
            alert.show();
        }


    }
}
