/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ShangTzu
 */
public class SpravceOsob {

    private ObservableList<Osoba> osoby = FXCollections.observableArrayList();

    public ObservableList<Osoba> getOsoby() {
        return osoby;
    }

    public void pridej(Osoba osoba) {
        osoby.add(osoba);
    }

    public void odeber(Osoba osoba) {
        osoby.remove(osoba);
    }
}
