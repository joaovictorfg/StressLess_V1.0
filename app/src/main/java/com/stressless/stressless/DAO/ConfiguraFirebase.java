package com.stressless.stressless.DAO;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class ConfiguraFirebase {
    private static DatabaseReference referenciaDB;
    private static FirebaseAuth autenticacao;


    public static DatabaseReference getReferenciaDB(){
        if (referenciaDB ==  null){
            referenciaDB = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaDB;
    }

    public static FirebaseAuth getAutenticacao(){
        if (autenticacao ==  null){
            autenticacao = FirebaseAuth.getInstance();
        }
        return autenticacao;
    }
}
