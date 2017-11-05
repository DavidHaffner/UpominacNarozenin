/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
            obnovNejblizsi();
            try {
                spravceOsob.uloz();
            } catch (IOException ex) {
                System.out.println("Chyba: " + ex.getMessage());
            }
        }
    }

    @FXML
    public void handleOdebratButtonAction(ActionEvent event) {
        Osoba vybrana = (Osoba) osobyListView.getSelectionModel().getSelectedItem();
        if (vybrana != null) {
            spravceOsob.odeber(vybrana);
            try {
                spravceOsob.uloz();
            } catch (IOException ex) {
                System.out.println("Chyba: " + ex.getMessage());
            }
            obnovNejblizsi();
        }
    }

    private void obnovNejblizsi() {
        if (!spravceOsob.getOsoby().isEmpty()) {
            Osoba nejblizsi = spravceOsob.najdiNejblizsi();
            int vek = nejblizsi.spoctiVek();
            Calendar dnes = Calendar.getInstance();
            if (dnes.get(Calendar.DAY_OF_MONTH) != nejblizsi.getNarozeniny().get(Calendar.DAY_OF_MONTH)
                    || dnes.get(Calendar.MONTH) != nejblizsi.getNarozeniny().get(Calendar.MONTH)
                    || dnes.get(Calendar.YEAR) != nejblizsi.getNarozeniny().get(Calendar.YEAR)) {
                vek++;
            }
            nejblizsiLabel.setText(nejblizsi.getJmeno() + " (" + vek + " let) za "
                    + nejblizsi.zbyvaDni() + " dní");
        } else {
            nejblizsiLabel.setText("Žádné osoby v seznamu");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Načtení dat
        try {
            spravceOsob.nacti();
        } catch (IOException | ParseException ex) {
            System.out.println("Chyba: " + ex.getMessage());
        }

        dnesLabel.setText(Datum.zformatuj(Calendar.getInstance()));
        osobyListView.setItems(spravceOsob.getOsoby());

        // Nastavení listeneru
        osobyListView.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Osoba>() {

            @Override
            public void changed(ObservableValue<? extends Osoba> ov, Osoba oldValue, Osoba newValue) {

                String narozeniny = "?", vek = "?";
                if (newValue != null) {
                    narozeniny = Datum.zformatuj(newValue.getNarozeniny());
                    vek = newValue.spoctiVek() + "";
                }
                narozeninyLabel.setText(narozeniny);
                vekLabel.setText(vek);
            }
        });

        if (!spravceOsob.getOsoby().isEmpty()) {
            osobyListView.getSelectionModel().select(0);
        }
        obnovNejblizsi();
    }
}
