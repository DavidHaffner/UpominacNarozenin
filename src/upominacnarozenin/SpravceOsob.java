/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ShangTzu
 */
public class SpravceOsob {

    private ObservableList<Osoba> osoby = FXCollections.observableArrayList();
    private String cesta = "osoby.csv";

    public ObservableList<Osoba> getOsoby() {
        return osoby;
    }

    public void pridej(Osoba osoba) {
        osoby.add(osoba);
    }

    public void odeber(Osoba osoba) {
        osoby.remove(osoba);
    }

    public Osoba najdiNejblizsi() {
        FXCollections.sort(osoby);
        return osoby.get(0);
    }

    public void uloz() throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(cesta))) {
            for (Osoba o : (getOsoby())) {
                bw.write(o.getJmeno() + ";" + Datum.zformatuj(o.getNarozeniny()));
                bw.newLine();
            }
        }
    }

    public void nacti() throws IOException, ParseException {
        File file = new File(cesta);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(cesta))) {
                List<Osoba> osobyDocasne = new ArrayList<>();
                String radek;
                while ((radek = br.readLine()) != null) {
                    String[] casti = radek.split(";");
                    String jmeno = casti[0];
                    Calendar narozeniny = Datum.naparsuj(casti[1]);
                    osobyDocasne.add(new Osoba(jmeno, narozeniny));
                }
                osoby.clear();
                osoby.addAll(osobyDocasne);
            }
        } else {
            osoby.clear();
        }
    }
}
