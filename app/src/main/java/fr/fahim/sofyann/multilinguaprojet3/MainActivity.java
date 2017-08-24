package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
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

    ImageView fond;
    TextView numLecon;
    TextView titreLecon;
    static int numChapitre = 1;
    String exercice = "";
    String leconDuJour = "";
    int imgID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        constructUI();
        recupererJSON();

    }
    public void toLeconDuJour(View view){
        Intent intent = new Intent(getApplicationContext(),LeconDuJour.class);
        intent.putExtra("leconDuJour", leconDuJour);
        intent.putExtra("exercice", exercice);
        intent.putExtra("fond",imgID);
        startActivity(intent);
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
        numLecon = (TextView)findViewById(R.id.numLecon);
        titreLecon = (TextView)findViewById(R.id.titreLecon);
    }

    private void recupererJSON(){
        String jsonStr = "";
        InputStream in;
        try {
            in = getAssets().open("jsonStr.json");
            Scanner reader = new Scanner(in);
            while (reader.hasNext()){
                String line = reader.nextLine();
                if (line != null) {
                    jsonStr += "\n"+line;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        miseAJourTitreNum(jsonStr);
    }

    private void miseAJourTitreNum(String s){
        JSONObject jsonObjectStr;
        try {
            jsonObjectStr = new JSONObject(s);
            JSONObject chapitres = jsonObjectStr.getJSONObject("chapitres");
            JSONArray chapitre = chapitres.getJSONArray("chapitre");
            JSONObject temp = chapitre.getJSONObject(numChapitre);
            numLecon.setText("Leçon "+(numChapitre+1));
            String titre= temp.getString("nom");
            titreLecon.setText(titre);
            String cheminImage = temp.getString("img");
            Log.i("image", cheminImage);
            imgID = getResources().getIdentifier(cheminImage, "drawable", getPackageName());
            fond.setImageResource(imgID);
            leconDuJour = temp.getString("lecon");
            exercice = temp.getString("exercice");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
