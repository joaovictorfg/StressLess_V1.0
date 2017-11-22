package com.stressless.stressless.Users;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.stressless.stressless.DAO.ConfiguraFirebase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class Usuarios {
    private String id;
    private String email;
    private String senha;
    private String nome;
    private String sobrenome;
    private String telefone;

    public Usuarios() {
    }

    public void salvar(){
        DatabaseReference databaseReference = ConfiguraFirebase.getReferenciaDB();
        databaseReference.child("usuario").child(String.valueOf(getId())).setValue(this);
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> hashMapUser = new HashMap<>();

        hashMapUser.put("id", getId());
        hashMapUser.put("email", getEmail());
        hashMapUser.put("senha", getSenha());
        hashMapUser.put("nome", getNome());
        hashMapUser.put("sobrenome", getSobrenome());
        hashMapUser.put("telefone", getTelefone());

        return hashMapUser;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
