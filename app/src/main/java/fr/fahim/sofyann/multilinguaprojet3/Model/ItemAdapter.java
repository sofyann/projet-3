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

import fr.fahim.sofyann.multilinguaprojet3.R;

/**
 * Created by Sofyann on 19/09/2017.
 */

public class ItemAdapter extends ArrayAdapter<ItemDate> {
    private ArrayList<ItemDate> itemDates;
    private Context context;
    private int resource;

    public ItemAdapter(Context context, int resource, ArrayList<ItemDate> itemDates){
        super(context, resource, itemDates);
        this.itemDates = itemDates;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null){
            LayoutInflater vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.itemdate_layout, null);
        }
        ItemDate itemDate = getItem(position);
        if (itemDate != null){
            TextView date = (TextView)v.findViewById(R.id.date);
            TextView heure = (TextView)v.findViewById(R.id.heure);
            TextView duree = (TextView)v.findViewById(R.id.duree);
            TextView event = (TextView)v.findViewById(R.id.event);
            if (date != null){
                date.setText("Le "+itemDate.getDay()+"/"+itemDate.getMonth()+"/"+itemDate.getYear());
            }
            if (heure != null){
                String min = String.valueOf(itemDate.getMinutes());
                if (itemDate.getMinutes() == 0)
                {
                 min = "00";
                }
                heure.setText("à "+itemDate.getHour()+"h"+min);
            }
            if (duree != null){
                duree.setText("durée : "+itemDate.getDuration());
            }
            if (event != null){
                event.setText(itemDate.getEvent());
            }
        }
        return v;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
