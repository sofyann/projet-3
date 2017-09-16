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

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
            case R.id.logout:
                ParseUser.logOut();
                intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            default:
                return false;
        }
    }
    ImageView fond;
    TextView numLecon;
    TextView titreLecon;
    static int numChapitre = 1;
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
        intent.putExtra("fond",imgID);
        startActivity(intent);
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
        numLecon = (TextView)findViewById(R.id.numLecon);
        titreLecon = (TextView)findViewById(R.id.titreLecon);
    }

    private void recupererJSON(){

        ParseQuery<ParseObject> query = new  ParseQuery<ParseObject>("Lecon");
        query.whereEqualTo("name","lecon"+numChapitre);
        query.setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null){
                    ParseFile file = (ParseFile) objects.get(0).get("json");
                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e == null && data != null){
                                String jsonStr = "";
                                InputStream in = new ByteArrayInputStream(data);
                                Scanner reader = new Scanner(in);
                                while (reader.hasNext()){
                                    String line = reader.nextLine();
                                    jsonStr += line;
                                }
                                miseAJourTitreNum(jsonStr);
                            }
                        }
                    });
                }
            }
        });

    }

    private void miseAJourTitreNum(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            String temp = jsonObject.getString("img");
            imgID = getResources().getIdentifier(temp, "drawable", getPackageName());
            fond.setImageResource(imgID);
            numLecon.setText("Leçon "+(numChapitre+1));
            temp = jsonObject.getString("nom");
            titreLecon.setText(temp);
            leconDuJour = jsonObject.getString("lecon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
