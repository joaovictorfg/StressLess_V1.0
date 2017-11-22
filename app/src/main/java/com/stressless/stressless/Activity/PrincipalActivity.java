package com.stressless.stressless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stressless.stressless.R;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnSensor;
    private Button btnManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnSensor = (Button) findViewById(R.id.btnSensor);
        btnManual = (Button) findViewById(R.id.btnManual);

        btnSensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrincipalActivity.this, SesnoresActivity.class);
                startActivity(i);
            }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PrincipalActivity.this, ManuaisActivity.class);
                startActivity(i);
            }
        });

    }
}
