package controllers;

import Service.Service;
import javafx.fxml.FXML;
import models.Curier;

import java.io.IOException;

public class EmployeeWelcomePageController implements Controller{
    Curier user;
    Service service;

    public EmployeeWelcomePageController(Service service) {
        this.service = service;
    }

    public void setUser(Curier user){
        this.user = user;
    }

    @FXML
    void markPresent(){
        user = service.markPresent(user);
        try {
            ScreenManager.get_instance().activate("employeeMainScreen", EmployeeMainController.class);
            ((EmployeeMainController)ScreenManager.get_instance().getCurrentController()).setUser((Curier) user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
