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
public class Osoba {

    private String jmeno;
    private Calendar narozeniny;

    public String getJmeno() {
        return jmeno;
    }

    public Calendar getNarozeniny() {
        return narozeniny;
    }

    public Osoba(String jmeno, Calendar narozeniny) throws IllegalArgumentException {
        Datum.nastavPulnoc(narozeniny);
        if (jmeno.length() < 3) {
            throw new IllegalArgumentException("Jméno je příliš krátké");
        }
        if (narozeniny.after(Calendar.getInstance())) {
            throw new IllegalArgumentException("Datum narození nesmí být v budoucnosti");
        }

        this.jmeno = jmeno;
        this.narozeniny = narozeniny;
    }

    @Override
    public String toString() {
        return jmeno;
    }
}
