package com.stressless.stressless.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.stressless.stressless.R;

public class EscolhaActivity extends AppCompatActivity {

    private Button btnCadastrar;
    private Button btnVizualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolha);

        btnCadastrar =  (Button) findViewById(R.id.btnCadastrar);
        btnVizualizar =  (Button) findViewById(R.id.btnVizualizar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EscolhaActivity.this, PrincipalActivity.class);
                startActivity(i);
            }
        });

        btnVizualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EscolhaActivity.this, VerActivity.class);
                startActivity(i);
            }
        });
    }
}
