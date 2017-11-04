/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.text.ParseException;
import java.util.Calendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

/**
 *
 * @author ShangTzu
 */
public class OsobaDialog extends Stage {

    private Osoba osoba = null;

    public OsobaDialog(Window okno) {
        setTitle("Nová osoba");
        setWidth(350);
        setHeight(250);

        initStyle(StageStyle.UTILITY);
        initModality(Modality.WINDOW_MODAL);
        initOwner(okno);
        setScene(vytvorScenu());
    }

    private Scene vytvorScenu() {
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);

        // Mřížka s TextFieldy a Labely
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        // Komponenty
        final TextField jmenoTextField = new TextField();
        final TextField datumTextField = new TextField();
        Label jmenoLabel = new Label("Jméno");
        Label datumLabel = new Label("Datum narození");

        grid.add(jmenoLabel, 0, 0);
        grid.add(jmenoTextField, 1, 0);
        grid.add(datumLabel, 0, 1);
        grid.add(datumTextField, 1, 1);

        // Tlačítko
        Button tlacitko = new Button("OK");
        tlacitko.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    Calendar narozeniny = Datum.naparsuj(datumTextField.getText());
                    osoba = new Osoba(jmenoTextField.getText(), narozeniny);
                    hide();
                } catch (ParseException | IllegalArgumentException ex) {
                    System.out.println("Chyba: " + ex.getMessage());
                }
            }
        });

        box.getChildren().addAll(grid, tlacitko);
        return new Scene(box);
    }

    public Osoba getOsoba() {
        return osoba;
    }
}
