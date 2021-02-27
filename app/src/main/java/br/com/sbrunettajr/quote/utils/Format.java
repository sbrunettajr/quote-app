package br.com.sbrunettajr.quote.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Format {

    private static final Locale locale = new Locale("pt", "BR");

    public static String currency(double value) {
        return NumberFormat.getCurrencyInstance(locale).format(value);
    }

}
