package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
    // Déclaration des éléments des vignettes
    Button buttonPasserALexercice;
    ArrayList<TextView> listMotVignette = new ArrayList<>();
    ArrayList<TextView> listPhraseExemple = new ArrayList<>();
    ArrayList<TextView> listPhraseExempleTrad = new ArrayList<>();
    String lecon ="";
    String exercice = "";
    ImageView fond;
    int idFond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecon_du_jour);
        constructUI();
        Intent intent = getIntent();
        lecon = intent.getStringExtra("leconDuJour");
        idFond = intent.getIntExtra("fond",0);
        fond.setImageResource(idFond);
        affichageLecon(lecon);
    }

    private void constructUI(){
        buttonPasserALexercice = (Button)findViewById(R.id.buttonpasserAlexercice);
        fond = (ImageView)findViewById(R.id.fond);

        listMotVignette.add((TextView) findViewById(R.id.motVignette1));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad));

        listMotVignette.add((TextView) findViewById(R.id.motVignette2));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple2));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad2));

        listMotVignette.add((TextView) findViewById(R.id.motVignette3));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple3));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad3));

        listMotVignette.add((TextView) findViewById(R.id.motVignette4));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple4));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad4));

        listMotVignette.add((TextView) findViewById(R.id.motVignette5));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple5));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad5));

        listMotVignette.add((TextView) findViewById(R.id.motVignette6));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple6));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad6));

        listMotVignette.add((TextView) findViewById(R.id.motVignette7));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple7));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad7));

        listMotVignette.add((TextView) findViewById(R.id.motVignette8));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple8));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad8));

        listMotVignette.add((TextView) findViewById(R.id.motVignette9));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple9));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad9));

        listMotVignette.add((TextView) findViewById(R.id.motVignette10));
        listPhraseExemple.add((TextView)findViewById(R.id.phraseExemple10));
        listPhraseExempleTrad.add((TextView)findViewById(R.id.phraseExempleTrad10));


    }
    public void passerAlexercice(View view){
        Intent intent = new Intent(getApplicationContext(),Exercice.class);
        intent.putExtra("exercice", exercice);
        intent.putExtra("img", idFond);
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

                  listMotVignette.get(j).setText(mot+" = "+trad);
                  listPhraseExemple.get(j).setText(exemple);
                  listPhraseExempleTrad.get(j).setText(exempleTrad);
                    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
