/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.util.Calendar;

/**
 *
 * @author ShangTzu
 */
public class Osoba implements Comparable<Osoba> {

    private String jmeno;
    private Calendar narozeniny;

    public Osoba(String jmeno, Calendar narozeniny) throws IllegalArgumentException {
        Datum.nastavPulnoc(narozeniny);
        if (jmeno.length() < 3) {
            throw new IllegalArgumentException("Jméno je příliš krátké");
        }
        if (narozeniny.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Datum narození nesmí být v budoucnosti");
        }
        if (jmeno.contains(";")) {
            throw new IllegalArgumentException("Jméno nesmí obsahovat ;");
        }

        this.jmeno = jmeno;
        this.narozeniny = narozeniny;
    }

    public String getJmeno() {
        return jmeno;
    }

    public Calendar getNarozeniny() {
        return narozeniny;
    }

    public int spoctiVek() {
        Calendar dnes = Calendar.getInstance();
        Datum.nastavPulnoc(dnes);
        int vek = dnes.get(Calendar.YEAR) - narozeniny.get(Calendar.YEAR);
        Calendar dalsiNarozeniny = (Calendar) narozeniny.clone();
        dalsiNarozeniny.add(Calendar.YEAR, vek);
        if (dnes.before(dalsiNarozeniny)) {
            vek--;
        }
        return vek;
    }

    public int zbyvaDni() {
        Calendar dnes = Calendar.getInstance();
        Datum.nastavPulnoc(dnes);
        Calendar dalsiNarozeniny = (Calendar) narozeniny.clone();
        dalsiNarozeniny.add(Calendar.YEAR, spoctiVek() + 1);

        int rozdil = -1;
        while (!dnes.after(dalsiNarozeniny)) {
            dnes.add(Calendar.DAY_OF_MONTH, 1);
            rozdil++;
        }
        return rozdil;
    }

    @Override
    public String toString() {
        return jmeno;
    }

    @Override
    public int compareTo(Osoba osoba) {
        return this.zbyvaDni() - osoba.zbyvaDni();
    }
}
