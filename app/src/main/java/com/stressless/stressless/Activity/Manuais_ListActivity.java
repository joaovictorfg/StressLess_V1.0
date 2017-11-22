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
import com.stressless.stressless.Adapter.ManuaisAdapter;
import com.stressless.stressless.Adapter.SensoresAdapter;
import com.stressless.stressless.DAO.ConfiguraFirebase;
import com.stressless.stressless.R;
import com.stressless.stressless.Users.ManuaisEnt;
import com.stressless.stressless.Users.SensoresEnt;

import java.util.ArrayList;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class Manuais_ListActivity extends AppCompatActivity {

    private ListView listViewManuais;
    private ArrayAdapter<ManuaisEnt> adapter2;
    private ArrayList<ManuaisEnt> manuaisList;
    private DatabaseReference databaseReference2;
    private ValueEventListener valueEventListener2;
    private Button btnVoltarTela4;
    private AlertDialog alertDialog2;
    private ManuaisEnt manuaisEntExcluir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manuais_list);

        manuaisList = new ArrayList<>();
        listViewManuais = (ListView) findViewById(R.id.listViewManuais);
        adapter2 = new ArrayAdapter<ManuaisEnt>(Manuais_ListActivity.this, android.R.layout.simple_list_item_1 , manuaisList);
        listViewManuais.setAdapter(adapter2);


        databaseReference2 = ConfiguraFirebase.getReferenciaDB().child("adddadosmanuais");
        valueEventListener2 = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                manuaisList.clear();
                for (DataSnapshot dados2 : dataSnapshot.getChildren()){
                    ManuaisEnt manuaisEntNovo = dados2.getValue(ManuaisEnt.class);
                    manuaisList.add(manuaisEntNovo);
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        btnVoltarTela4 = (Button) findViewById(R.id.btnVoltarTela4);
        btnVoltarTela4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                voltarTela4();
            }
        });

        listViewManuais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                manuaisEntExcluir = adapter2.getItem(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(Manuais_ListActivity.this);
                builder.setTitle("Confirmar exclusão?");
                builder.setMessage("Você deseja excluir os dados?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        databaseReference2 = ConfiguraFirebase.getReferenciaDB().child("adddadosmanuais");
                        databaseReference2.child(manuaisEntExcluir.getNumeroBrinco2().toString()).removeValue();
                        Toast.makeText(Manuais_ListActivity.this, "Exclusão realizada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(Manuais_ListActivity.this, "Exclusão cancelada com sucesso!", Toast.LENGTH_LONG).show();
                    }
                });
                alertDialog2 = builder.create();
                alertDialog2.show();
            }
        });
    }
    private void voltarTela4(){
        Intent i = new Intent(Manuais_ListActivity.this, EscolhaActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        databaseReference2.removeEventListener(valueEventListener2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference2.addValueEventListener(valueEventListener2);
    }
}
