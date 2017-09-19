package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MaProgression extends AppCompatActivity {
    ListView listView;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch (item.getItemId()){
            case R.id.maProgression:
                intent = new Intent(getApplicationContext(),MaProgression.class);
                startActivity(intent);
                return true;
            case R.id.meslangues:
                intent = new Intent(getApplicationContext(),MesLangues.class);
                startActivity(intent);
                return true;
            case R.id.datesDeCours:
                intent = new Intent(getApplicationContext(),DatesDeCours.class);
                startActivity(intent);
                return true;
            case R.id.contacterMonFormateur:
                intent = new Intent(getApplicationContext(),ContacterMonFormateur.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ma_progression);
        setTitle("Progression");
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Progression");
        query.orderByDescending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    if (objects.size()>0){
                        ArrayList<ItemProgression> itemProgressions = new ArrayList<>();
                        for (ParseObject object : objects){
                            String nameLecon = object.getString("nameLecon");
                            String nameLeconInParse = object.getString("nameLeconInParse");
                            String nameExerciceInParse = object.getString("nameExerciceInParse");
                            boolean validerOuNon = object.getBoolean("validerOuNon");
                            String note = object.getString("note");
                            ItemProgression itemProgression = new ItemProgression(nameLecon, note, nameLeconInParse,nameExerciceInParse,validerOuNon);
                            itemProgressions.add(itemProgression);
                        }
                        displayProgress(itemProgressions);
                    }
                } else {
                    Toast.makeText(MaProgression.this, "Problème de connexion, veuillez réessayer", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void displayProgress(ArrayList<ItemProgression> itemProgressions){
        listView = (ListView)findViewById(R.id.listViewProgression);
        ProgressionAdapter progressionAdapter = new ProgressionAdapter(this, R.layout.itemprogression_layout, itemProgressions);
        listView.setAdapter(progressionAdapter);
        setListeners(itemProgressions);
    }

    private void setListeners(final ArrayList<ItemProgression> itemProgressions){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ItemProgression itemSelected = itemProgressions.get(i);

            }
        });
    }
}
