package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

public class LeconDuJour extends AppCompatActivity {
    Button buttonPasserALexercice;
    ArrayList<Vignette> vignettes= new ArrayList<>();
    String lecon ="";
    String exercice = "";
    ImageView fond;
    byte[] bytes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecon_du_jour);
        constructUI();
        Intent intent = getIntent();
        lecon = intent.getStringExtra("leconDuJour");
        byte[]bytes = intent.getByteArrayExtra("imageFond");
        this.bytes = bytes;
        Bitmap bmp;
        if (bytes != null){
            bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            fond.setImageBitmap(bmp);
            if (bmp != null){
                Log.i("conversion", "succes");
            }
        }else{
            Log.i("bytes ", "null");
        }


        affichageLecon(lecon);
    }

    private void constructUI(){
        buttonPasserALexercice = (Button)findViewById(R.id.buttonpasserAlexercice);
        fond = (ImageView)findViewById(R.id.fond);


    }
    public void passerAlexercice(View view){
        Intent intent = new Intent(getApplicationContext(),Exercice.class);
        intent.putExtra("exercice", exercice);
        intent.putExtra("imageFond", bytes);
        startActivity(intent);
    }

    private void affichageLecon(String s){
        JSONArray jsonObjectStr;

        try {
            jsonObjectStr = new JSONArray(s);
            Log.i("exercice", exercice);
            for (int j = 0; j < jsonObjectStr.length(); j++){
                JSONObject temp3 = jsonObjectStr.getJSONObject(j);
                String mot = temp3.getString("mot");
                String trad = temp3.getString("trad");
                String exemple = temp3.getString("exemple");
                String exempleTrad = temp3.getString("exempleTrad");
                String motAvecTrad = mot + " = "+trad;
                Vignette vignette = new Vignette(motAvecTrad, exemple, exempleTrad);
                vignettes.add(vignette);
                }
            ListView listView = (ListView)findViewById(R.id.listTest);
            VignetteAdapter adapter = new VignetteAdapter(this, R.layout.vignette_layout, vignettes);
            listView.setAdapter(adapter);
            // add button at the end of the ListView
            LayoutInflater layoutInflater = LayoutInflater.from(this);
            View v = layoutInflater.inflate(R.layout.footer, null);
            listView.addFooterView(v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
