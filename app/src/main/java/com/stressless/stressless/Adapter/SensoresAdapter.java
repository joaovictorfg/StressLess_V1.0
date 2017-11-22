package com.stressless.stressless.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stressless.stressless.R;
import com.stressless.stressless.Users.SensoresEnt;

import java.util.ArrayList;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class SensoresAdapter extends ArrayAdapter<SensoresEnt> {

    ArrayList<SensoresEnt> sensoresEnts;
    private Context context;

    public SensoresAdapter(Context c, ArrayList<SensoresEnt> objects) {
        super(c, 0, objects);
        this.context = c;
        this.sensoresEnts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (sensoresEnts != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_sesnores, parent, false);

            TextView textBrinco = (TextView) view.findViewById(R.id.textBrinco);
            TextView textFR = (TextView) view.findViewById(R.id.textFR);
            TextView textTR = (TextView) view.findViewById(R.id.textTR);

            SensoresEnt sensoresEnt2 = sensoresEnts.get(position);

            textBrinco.setText(sensoresEnt2.getNumeroBrinco().toString());
            textFR.setText(sensoresEnt2.getFrequenciaRespiratoria().toString());
            textTR.setText(sensoresEnt2.getTemperaturaRetal().toString());
        }
        return view;
    }
}
