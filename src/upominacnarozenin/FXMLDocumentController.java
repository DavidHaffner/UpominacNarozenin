/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

/**
 *
 * @author ShangTzu
 */
public class FXMLDocumentController implements Initializable {

    private SpravceOsob spravceOsob = new SpravceOsob();

    @FXML
    private ListView<Osoba> osobyListView;
    @FXML
    private Label dnesLabel;
    @FXML
    private Label nejblizsiLabel;
    @FXML
    private Label narozeninyLabel;
    @FXML
    private Label vekLabel;

    @FXML
    public void handlePridatButtonAction(ActionEvent event) {
        OsobaDialog dialog = new OsobaDialog(dnesLabel.getScene().getWindow());
        dialog.showAndWait();

        Osoba nova = dialog.getOsoba();
        if (nova != null) {
            spravceOsob.pridej(nova);
        }
    }

    @FXML
    private void handleOdebratButtonAction(ActionEvent event) {
        Osoba vybrana = (Osoba) osobyListView.getSelectionModel().getSelectedItem();
        if (vybrana != null) {
            spravceOsob.odeber(vybrana);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dnesLabel.setText(Datum.zformatuj(Calendar.getInstance()));
        osobyListView.setItems(spravceOsob.getOsoby());
        if (!spravceOsob.getOsoby().isEmpty()) {
            osobyListView.getSelectionModel().select(0);
        }
    }

}
