package controllers;

import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import models.Pachet;
import models.Sef;

public class BossMainController implements Controller{
    Sef user;
    Service service;
    boolean isSelecting = true;

    @FXML
    ListView<Pachet> availablePackagesListView;

    @FXML
    ListView<Pachet> selectedPackagesListView;

    private ObservableList<Pachet> pachete = FXCollections.observableArrayList();
    private ObservableList<Pachet> selectedPachete = FXCollections.observableArrayList();

    public BossMainController(Service service) {
        this.service = service;
    }

    public void initialize(){
        this.availablePackagesListView.setItems(this.pachete);
        this.selectedPackagesListView.setItems(this.selectedPachete);
        this.pachete.addAll(service.getAllPackages());
    }

    public void setUser(Sef user){

        this.user = user;
    }

    @FXML
    void addPackage(){
        if(isSelecting) {
            Pachet pachet = availablePackagesListView.getSelectionModel().getSelectedItem();
            service.addPackageForTodayDelivery(user, pachet);
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
