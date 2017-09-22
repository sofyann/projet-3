package fr.fahim.sofyann.multilinguaprojet3;

import android.app.usage.UsageEvents;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class DatesDeCours extends AppCompatActivity {

    Button listDatesBtn;
    Button calendrierBtn;
    LinearLayout linearLayout;
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_de_cours);
        listDatesBtn = (Button)findViewById(R.id.listProchainesDates);
        calendrierBtn = (Button)findViewById(R.id.calendrier);
        linearLayout = (LinearLayout)findViewById(R.id.layoutListView);
        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendrierBtn.setEnabled(false);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rdv");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    if (objects.size()>0){
                        ArrayList<ItemDate> itemDates = new ArrayList<>();
                        for (ParseObject parseObject : objects){
                            ItemDate itemDate = new ItemDate(parseObject.getInt("jour"),parseObject.getInt("mois"),parseObject.getInt("annee"), parseObject.getInt("heure"),parseObject.getInt("minute"), parseObject.getString("duree"), parseObject.getString("event"));
                            itemDates.add(itemDate);
                        }
                        displayDates(itemDates);
                    }else {
                        Toast.makeText(DatesDeCours.this, "Aucun cours programmer", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

}
    private void displayDates(ArrayList<ItemDate> itemDates){
        ListView listView = (ListView)findViewById(R.id.listViewDates);
        ItemAdapter adapter = new ItemAdapter(this, R.layout.itemdate_layout, itemDates);
        listView.setAdapter(adapter);


    }

    public void listProchainesDates(View view){
        listDatesBtn.setEnabled(false);
        calendrierBtn.setEnabled(true);
        calendar.animate().translationXBy(2500f).setDuration(1000);
        linearLayout.animate().translationXBy(2500f).setDuration(1000);
    }
    public void calendrier(View view){

        listDatesBtn.setEnabled(true);
        calendrierBtn.setEnabled(false);
        calendar.animate().translationXBy(-2500f).setDuration(1000);
        linearLayout.animate().translationXBy(-2500f).setDuration(1000);
    }

}
