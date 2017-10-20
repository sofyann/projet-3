package fr.fahim.sofyann.multilinguaprojet3.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import fr.fahim.sofyann.multilinguaprojet3.Model.Vignette;
import fr.fahim.sofyann.multilinguaprojet3.R;

/**
 * Created by Sofyann on 16/09/2017.
 */

public class VignetteAdapter extends ArrayAdapter<Vignette>{
    private ArrayList<Vignette> vignettes;
    private int listItemResLayout;
    private Context context;


    public VignetteAdapter(Context context, int resource, ArrayList<Vignette> vignettes){
        super(context, resource, vignettes);
        this.context = context;
        this.listItemResLayout = resource;
        this.vignettes = vignettes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.vignette_layout, null);
        }
        Vignette p = getItem(position);
        if (p != null){
            TextView mot = (TextView)v.findViewById(R.id.motVignette1);
            TextView phrase = (TextView)v.findViewById(R.id.phraseExemple);
            TextView phraseTrad = (TextView)v.findViewById(R.id.phraseExempleTrad);
            if (mot != null){
                mot.setText(p.getMot());
            }
            if (phrase != null){
                phrase.setText(p.getPhrase());
            }
            if (phraseTrad != null){
                phraseTrad.setText(p.getPhraseTrad());
            }
        }
        return v;
    }

    @Override
    public int getCount() {
        if (vignettes != null) return vignettes.size();
        return 0;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
