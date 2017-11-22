package com.stressless.stressless.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.stressless.stressless.R;
import com.stressless.stressless.Users.ManuaisEnt;
import com.stressless.stressless.Users.SensoresEnt;

import java.util.ArrayList;

/**
 * Created by João Victor Firmino on 21/11/2017.
 */

public class ManuaisAdapter extends ArrayAdapter<ManuaisEnt> {

    ArrayList<ManuaisEnt> manuaisEnts;
    private Context context;

    public ManuaisAdapter(Context c, ArrayList<ManuaisEnt> objects) {
        super(c, 0, objects);
        this.context = c;
        this.manuaisEnts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (manuaisEnts != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lista_manuais, parent, false);

            TextView textBrinco2 = (TextView) view.findViewById(R.id.textBrinco2);
            TextView textFR2 = (TextView) view.findViewById(R.id.textFR2);
            TextView textTR2 = (TextView) view.findViewById(R.id.textTR2);

            ManuaisEnt manuaisEnts3 = manuaisEnts.get(position);

            textBrinco2.setText(manuaisEnts3.getNumeroBrinco2().toString());
            textFR2.setText(manuaisEnts3.getFrequenciaRespiratoria2().toString());
            textTR2.setText(manuaisEnts3.getTemperaturaRetal2().toString());
        }
        return view;
    }
}
