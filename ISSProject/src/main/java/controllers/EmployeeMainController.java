package controllers;

import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import models.Curier;
import models.Pachet;
import models.Sef;

public class EmployeeMainController implements Controller{
    Curier user;
    Service service;
    boolean isSelecting = true;

    @FXML
    ListView<Pachet> availablePackagesListView;

    @FXML
    ListView<Pachet> selectedPackagesListView;

    private ObservableList<Pachet> pachete = FXCollections.observableArrayList();
    private ObservableList<Pachet> selectedPachete = FXCollections.observableArrayList();


    public EmployeeMainController(Service service) {
        this.service = service;
    }

    public void initialize(){
        this.availablePackagesListView.setItems(this.pachete);
        this.selectedPackagesListView.setItems(this.selectedPachete);
        this.pachete.addAll(service.getAllAvaiblePackagesForCurrentDay());

    }

    public void setUser(Curier user){
        this.user = user;
    }

    @FXML
    void addPackage(){
        if(isSelecting) {
            Pachet pachet = availablePackagesListView.getSelectionModel().getSelectedItem();
            service.choosePackageForDelivery(user, pachet);
            availablePackagesListView.getItems().remove(pachet);
            selectedPackagesListView.getItems().add(pachet);
        } else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Eroare");
            alert.setContentText("Ai selectat deja pachetele!");
            alert.show();
        }
    }

    @FXML
    void endSelectPackages(){
        if(selectedPackagesListView.getItems().size() > 0){
            isSelecting = false;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Info");
            alert.setContentText("Ai terminat selectarea pachetelor!");
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("Eroare");
            alert.setContentText("Selecteaza cel putin un pachet!");
            alert.show();
        }
    }

}
