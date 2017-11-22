package com.stressless.stressless.Helper;

import android.util.Base64;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class Base64Custom {

    public static String codificaBase64(String texto){
        return Base64.encodeToString(texto.getBytes(), Base64.DEFAULT).replaceAll("(\\n|\\r)", "");
    }

    public static String decodificaBase64(String textoDecodifica){
        return new String(Base64.decode(textoDecodifica, Base64.DEFAULT));
    }
}
