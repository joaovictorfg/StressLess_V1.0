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
import com.stressless.stressless.Users.ManuaisEnt;
import com.stressless.stressless.Users.SensoresEnt;

public class ManuaisActivity extends AppCompatActivity {

    private EditText editBrinco2;
    private EditText editFR2;
    private EditText editTR2;
    private Button btnSalvar2;
    private Button btnVoltarTela2;
    private ManuaisEnt manuaisEnt;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manuais);

        editBrinco2 = (EditText) findViewById(R.id.editBrinco2);
        editFR2 = (EditText) findViewById(R.id.editFR2);
        editTR2 = (EditText) findViewById(R.id.editTR2);
        btnSalvar2 = (Button) findViewById(R.id.btnSalvar2);
        btnVoltarTela2 = (Button) findViewById(R.id.btnVoltarTela2);

        btnSalvar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manuaisEnt = new ManuaisEnt();
                manuaisEnt.setNumeroBrinco2(editBrinco2.getText().toString());
                manuaisEnt.setFrequenciaRespiratoria2(editFR2.getText().toString());
                manuaisEnt.setTemperaturaRetal2(editTR2.getText().toString());

                salvarDadosSensor(manuaisEnt);
                finish();
            }
        });

        btnVoltarTela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela2();
            }
        });
    }

    private boolean salvarDadosSensor(ManuaisEnt manuaisEnt){
        try {
            databaseReference = ConfiguraFirebase.getReferenciaDB().child("adddadosmanuais");
            databaseReference.child(manuaisEnt.getNumeroBrinco2()).setValue(manuaisEnt);
            Toast.makeText(ManuaisActivity.this, "Dados cadastrado com sucesso!", Toast.LENGTH_LONG).show();
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private void voltarTela2(){
        Intent i = new Intent(ManuaisActivity.this, PrincipalActivity.class);
        startActivity(i);
        finish();

    }
}
