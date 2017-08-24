package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DatesDeCours extends AppCompatActivity {
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
        setContentView(R.layout.activity_dates_de_cours);
    }
}
