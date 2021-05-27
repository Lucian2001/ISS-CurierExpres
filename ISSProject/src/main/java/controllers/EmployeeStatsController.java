package controllers;

import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import models.Curier;
import models.Pachet;

public class EmployeeStatsController implements Controller {

    Service service;

    @FXML
    Label name;
    @FXML
    ListView<Pachet> details;

    private ObservableList<Pachet> pachete = FXCollections.observableArrayList();

    public EmployeeStatsController(Service service) {
        this.service = service;
    }

    void showStats(Curier curier){
        name.setText(curier.getNume() + " \n" + "Ai livrat pachetele:");
        details.setItems(pachete);
        pachete.addAll(service.getDeliveredPackages(curier));
    }
}
