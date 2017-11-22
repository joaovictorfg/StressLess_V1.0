package com.stressless.stressless.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.stressless.stressless.Adapter.SensoresAdapter;
import com.stressless.stressless.DAO.ConfiguraFirebase;
import com.stressless.stressless.R;
import com.stressless.stressless.Users.ManuaisEnt;
import com.stressless.stressless.Users.SensoresEnt;

import java.util.ArrayList;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class Sensores_ListActivity extends AppCompatActivity {

    private ListView listViewSensores;
    private ArrayAdapter<SensoresEnt> adapter;
    private ArrayList<SensoresEnt> sensoresEnts;
    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    private Button btnVoltarTela3;
    private AlertDialog alertDialog;
    private SensoresEnt sensoresEntExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sensores_list);

        sensoresEnts = new ArrayList<>();
        listViewSensores = (ListView) findViewById(R.id.listViewSensores);
        adapter = new ArrayAdapter<SensoresEnt>(Sensores_ListActivity.this, android.R.layout.simple_list_item_1 , sensoresEnts);
        listViewSensores.setAdapter(adapter);


        databaseReference = ConfiguraFirebase.getReferenciaDB().child("adddadossensores");
        valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                sensoresEnts.clear();
                for (DataSnapshot dados : dataSnapshot.getChildren()){
                    SensoresEnt sensoresEntNovo = dados.getValue(SensoresEnt.class);
                    sensoresEnts.add(sensoresEntNovo);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        btnVoltarTela3 = (Button) findViewById(R.id.btnVoltarTela3);
        btnVoltarTela3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela3();
            }
        });

        listViewSensores.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sensoresEntExcluir = adapter.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Sensores_ListActivity.this);
                builder.setTitle("Confirmar exclusão?");
                builder.setMessage("Você deseja excluir os dados?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference = ConfiguraFirebase.getReferenciaDB().child("adddadossensores");
                        databaseReference.child(sensoresEntExcluir.getNumeroBrinco().toString()).removeValue();
                        Toast.makeText(Sensores_ListActivity.this, "Exclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Sensores_ListActivity.this, "Exclusão cancelada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }
    private void voltarTela3(){
        Intent i = new Intent(Sensores_ListActivity.this, EscolhaActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference.removeEventListener(valueEventListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(valueEventListener);
    }

}
