package controllers;

import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import models.Curier;
import models.Pachet;

import java.io.IOException;

public class EmployeeDeliveryController implements Controller{
    Curier user;
    Service service;


    @FXML
    ListView<Pachet> packagesListView;

    public EmployeeDeliveryController(Service service) {
        this.service = service;
    }

    private ObservableList<Pachet> pachete = FXCollections.observableArrayList();


    public void initialize(){

    }

    public void setUser(Curier user){
        this.user = user;
        this.packagesListView.setItems(this.pachete);
        this.pachete.addAll(service.getPackagesForDelivery(user));
    }


    @FXML
    public void handleDeliveryPackage(){
        Pachet pachet = packagesListView.getSelectionModel().getSelectedItem();
        service.deliverPackage(user, pachet);
        packagesListView.getItems().remove(pachet);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Livrare");
        alert.setHeaderText("Livrare");
        alert.setContentText("Ai livrat pachetul cu succes!");
        alert.show();
    }

    @FXML
    public void handleShowStats(){
        try {
            EmployeeStatsController controller = (EmployeeStatsController) ScreenManager.get_instance().openNewScene("employeeStatsScreen", EmployeeStatsController.class);
            controller.showStats(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
