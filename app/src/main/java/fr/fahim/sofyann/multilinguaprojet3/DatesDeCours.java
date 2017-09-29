package fr.fahim.sofyann.multilinguaprojet3;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import java.util.Date;
import java.util.List;


public class DatesDeCours extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates_de_cours);
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Rdv");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.whereEqualTo("rdvPasser", false);
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
        generateNotifications(itemDates);
    }

    private void generateNotifications(ArrayList<ItemDate> itemDates){
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        for(int i = 0; i < itemDates.size(); i++){
            String dateString = itemDates.get(i).getMonth()+"/"+itemDates.get(i).getDay()+"/"+itemDates.get(i).getYear()+" "+itemDates.get(i).getHour()+":"+itemDates.get(i).getMinutes()+":00";
            try {
                Date date = sdf.parse(dateString);
                Date date1 = Calendar.getInstance().getTime();
                Log.i("date1", String.valueOf(date1));
                
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }
    }

    private Notification getNotification(String content) {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 1, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentTitle("Multilingua");
        builder.setContentText(content);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        return builder.build();
    }
}
