package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import models.Pachet;

public class PackageDetailsController implements Controller{

    @FXML
    Label title;
    @FXML
    TextArea description;

    void showPackageDetails(Pachet pachet){
        title.setText("Coletul cu id-ul " + pachet.getId());
        description.setText("Pachetul are: \n"
                + "Adresa: " + pachet.getAdresa() + "\n"
                + "Destinatar: " + pachet.getDestinatar() + "\n"
                + "Status: " + pachet.getStatusPachet()
        );
    }
}
