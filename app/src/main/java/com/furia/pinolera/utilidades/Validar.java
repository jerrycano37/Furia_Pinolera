package com.furia.pinolera.utilidades;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validar {

    public static boolean camposVacios(String campoValidar) {
        return TextUtils.isEmpty(campoValidar);
    }

    public static boolean validarCorreo(String correoValidar) {
        Pattern p = Pattern.compile("[-\\w\\.]+@[\\.\\w]+\\.\\w+");
        Matcher m = p.matcher(correoValidar);
        return m.matches();
    }

    public static boolean passValidacion(String pass1,String pass2) {
            String pattern = "(?=\\S+$).{8,}";
            boolean veriPass1 = pass1.matches(pattern);
            boolean veripass2 = pass2.matches(pattern);
            return veriPass1 || veripass2;
        }
}
