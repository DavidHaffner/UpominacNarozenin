/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package upominacnarozenin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ShangTzu
 */
public class Datum {

    private static DateFormat formatData = new SimpleDateFormat("d.M.yyyy");

    public static void nastavPulnoc(Calendar datum) {
        datum.set(Calendar.HOUR_OF_DAY, 0);
        datum.set(Calendar.MINUTE, 0);
        datum.set(Calendar.SECOND, 0);
        datum.set(Calendar.MILLISECOND, 0);
    }

    public static String zformatuj(Calendar datum) {
        String datumText = formatData.format(datum.getTime());
        return datumText;
    }

    public static Calendar naparsuj(String datumText) throws ParseException {
        Calendar datum = Calendar.getInstance();
        datum.setTime(formatData.parse(datumText));
        return datum;
    }
}
