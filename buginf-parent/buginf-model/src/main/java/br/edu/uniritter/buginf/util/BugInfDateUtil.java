package br.edu.uniritter.buginf.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BugInfDateUtil {
	/**
	 * Formata a data para o formato ISO8601 usado 
	 * pelo elastic search
	 * @param date
	 * @return
	 */
	public static String formatToISO8601(Date date) {
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }

}
