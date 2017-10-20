package fr.fahim.sofyann.multilinguaprojet3.Model;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.fahim.sofyann.multilinguaprojet3.Model.ItemProgression;
import fr.fahim.sofyann.multilinguaprojet3.R;

/**
 * Created by Sofyann on 18/09/2017.
 */

public class ProgressionAdapter extends ArrayAdapter<ItemProgression>{
    private ArrayList<ItemProgression> itemProgressions;
    private Context context;

    public ProgressionAdapter(Context context, int resource, ArrayList<ItemProgression> itemProgressions){
        super(context, resource, itemProgressions);
        this.itemProgressions = itemProgressions;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.itemprogression_layout, null);
        }
        ItemProgression itemProgression = getItem(position);
        if (itemProgression != null){
            TextView nomLeconView = (TextView)v.findViewById(R.id.nomLeconView);
            TextView noteView = (TextView)v.findViewById(R.id.noteView);
            if (nomLeconView != null){
                nomLeconView.setText(itemProgression.getNomLecon());
                if (itemProgression.isValiderOuNon()){
                    v.setBackgroundColor(Color.parseColor("#56CA4A"));
                }else {
                    v.setBackgroundColor(Color.parseColor("#F25960"));
                }
            }
            if (noteView != null){
                noteView.setText(itemProgression.getNote());
            }
        }
        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
