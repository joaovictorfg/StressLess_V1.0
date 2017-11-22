package com.stressless.stressless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.stressless.stressless.DAO.ConfiguraFirebase;
import com.stressless.stressless.R;
import com.stressless.stressless.Users.SensoresEnt;

public class SesnoresActivity extends AppCompatActivity {

    private EditText editBrinco;
    private EditText editFR;
    private EditText editTR;
    private Button btnSalvar;
    private Button btnVoltarTela;
    private SensoresEnt sensoresEnt;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesnores);

        editBrinco = (EditText) findViewById(R.id.editBrinco);
        editFR = (EditText) findViewById(R.id.editFR);
        editTR = (EditText) findViewById(R.id.editTR);
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnVoltarTela = (Button) findViewById(R.id.btnVoltarTela);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensoresEnt = new SensoresEnt();
                sensoresEnt.setNumeroBrinco(editBrinco.getText().toString());
                sensoresEnt.setFrequenciaRespiratoria(editFR.getText().toString());
                sensoresEnt.setTemperaturaRetal(editTR.getText().toString());

                salvarDadosSensor(sensoresEnt);
                finish();
            }
        });

        btnVoltarTela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela();
            }
        });
    }

    private boolean salvarDadosSensor(SensoresEnt sensoresEnt){
        try {
            databaseReference = ConfiguraFirebase.getReferenciaDB().child("adddadossensores");
            databaseReference.child(sensoresEnt.getNumeroBrinco()).setValue(sensoresEnt);
            Toast.makeText(SesnoresActivity.this, "Dados cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void voltarTela(){
        Intent i = new Intent(SesnoresActivity.this, PrincipalActivity.class);
        startActivity(i);
        finish();
    }
}
