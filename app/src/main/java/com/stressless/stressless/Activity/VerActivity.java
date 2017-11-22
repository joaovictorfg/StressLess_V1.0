package com.stressless.stressless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stressless.stressless.R;

public class VerActivity extends AppCompatActivity {

    private Button btnVerSensores;
    private Button btnVerManuais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        btnVerSensores =  (Button) findViewById(R.id.btnVerSensores);
        btnVerManuais =  (Button) findViewById(R.id.btnVerManuais);

        btnVerSensores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerActivity.this, Sensores_ListActivity.class);
                startActivity(i);
            }
        });

        btnVerManuais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VerActivity.this, Manuais_ListActivity.class);
                startActivity(i);
            }
        });
    }
}
