package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Exercice extends AppCompatActivity {
    ImageView fond;
    ArrayList exercices = new ArrayList();
    RelativeLayout relativeLayoutBackground;
    int exerciceNumber = 0;
    int noteInt = 0;
    String exerciceRelecture = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        constructUI();
        Intent intent = getIntent();
        byte[] bytes = intent.getByteArrayExtra("imageFond");
        Bitmap imageFond = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        fond.setImageBitmap(imageFond);
        System.out.println(intent.getStringExtra("exercice"));
        if(LeconDuJour.modeRelecture ==true){

            exerciceRelecture = intent.getStringExtra("exercice");

        }
        if (exerciceRelecture == ""){
            Log.i("exercicerelecture", "null");
        }
        getJson();
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
        relativeLayoutBackground = (RelativeLayout)findViewById(R.id.relativeLayoutBackground);
    }

    private void getJson(){

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Exercice");
        if (LeconDuJour.modeRelecture == false){
            query.whereEqualTo("name", "exercice"+MainActivity.numChapitre);
        }else {
            query.whereEqualTo("name", exerciceRelecture);
        }
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
                                convertJsonToExercice(jsonStr);
                            }
                        }
                    });
                }
            }
        });
    }

    private void convertJsonToExercice(String s){
        try{
            JSONArray jsonArray = new JSONArray(s);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String type = jsonObject.getString("type");
                String enoncer = jsonObject.getString("enoncer");
                if (type.matches("choisirPhrase")){
                    String phraseVrai = jsonObject.getString("phraseVrai");
                    String phraseFR = jsonObject.getString("phraseFR");
                    String phraseFausse1 = jsonObject.getString("phraseFausse1");
                    String phraseFausse2 = jsonObject.getString("phraseFausse2");
                    String phraseFausse3 = jsonObject.getString("phraseFausse3");
                    ExerciceType1 exercice = new ExerciceType1(enoncer,phraseFR,phraseVrai,phraseFausse1,phraseFausse2,phraseFausse3, this);
                    exercices.add(exercice);
                } else if (type.matches("trouverMot")){
                    String motFR = jsonObject.getString("motFR");
                    String bonMot = jsonObject.getString("bonMot");
                    String mauvaisMot1 = jsonObject.getString("mauvaisMot1");
                    String mauvaisMot2 = jsonObject.getString("mauvaisMot2");
                    String mauvaisMot3 = jsonObject.getString("mauvaisMot3");
                    ExerciceType2 exercice = new ExerciceType2(enoncer, bonMot,motFR, mauvaisMot1, mauvaisMot2, mauvaisMot3, this);
                    exercices.add(exercice);
                } else if (type.matches("traduirePhrase")){
                    String phraseCorrect = jsonObject.getString("phraseCorrect");
                    String phraseFR = jsonObject.getString("phraseFR");
                    ExerciceType3 exercice = new ExerciceType3(enoncer, phraseCorrect,phraseFR, this);
                    exercices.add(exercice);
                }
            }
            Log.i("exercices size", String.valueOf(exercices.size()));
            generateExercisesLayoutsRandom();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    private void generateExercisesLayoutsRandom(){
        if (LeconDuJour.modeRelecture == true){
            for (int i = 0; i<exercices.size(); i++){
                if (((ExerciceType)exercices.get(i)).getType() == "choisirPhrase"){
                    ExerciceType1 exerciceType1 = (ExerciceType1) exercices.get(i);
                    exerciceType1.getBtn2().setVisibility(View.INVISIBLE);
                    exerciceType1.getBtn3().setVisibility(View.INVISIBLE);
                    exerciceType1.getBtn4().setVisibility(View.INVISIBLE);
                } else if (((ExerciceType)exercices.get(i)).getType() == "trouverMot"){
                    ExerciceType2 exerciceType2 = (ExerciceType2) exercices.get(i);
                    exerciceType2.getBtn2().setVisibility(View.INVISIBLE);
                    exerciceType2.getBtn3().setVisibility(View.INVISIBLE);
                    exerciceType2.getBtn4().setVisibility(View.INVISIBLE);
                }
            }
        }
        for (int i = 0; i < exercices.size(); i++){
            relativeLayoutBackground.addView((View) exercices.get(i));
            if (i != 0){
               ((View) exercices.get(i)).setTranslationX(2500f);
            }
        }
    }


    public void next(View view){
        int exercicesSize = exercices.size();
        // Tant qu'il y a des exercices on passe au suivant
        if (exercicesSize > exerciceNumber && exerciceNumber < exercicesSize -1){
            if(LeconDuJour.modeRelecture != true){
                ExerciceType exerciceCourant = (ExerciceType) exercices.get(exerciceNumber);
                boolean bonneOuMauvaiseReponse = exerciceCourant.resultat();
                if (bonneOuMauvaiseReponse == true){
                    noteInt++;
                    Log.i("reponse", "correct");
                }else{
                    Log.i("reponse", "fausse");
                }
            }


            ((View) exercices.get(exerciceNumber)).animate().translationXBy(-2500f).setDuration(1000);
            ((View) exercices.get(exerciceNumber+1)).animate().translationXBy(-2500f).setDuration(1000);

        } else if (exerciceNumber == exercicesSize-1){
            if(LeconDuJour.modeRelecture != true){
                ExerciceType exerciceCourant = (ExerciceType) exercices.get(exerciceNumber);
                boolean bonneOuMauvaiseReponse = exerciceCourant.resultat();
                if (bonneOuMauvaiseReponse == true){
                    noteInt++;
                    Log.i("reponse", "correct");
                }else{
                    Log.i("reponse", "fausse");
                }
                // quand on a fait tous les exercices on passe a la soumission de l'exercice
                Note note = new Note(this, noteInt, exercicesSize);
                relativeLayoutBackground.addView((View) note);
                note.setTranslationX(2500f);
                ((View) exercices.get(exerciceNumber)).animate().translationXBy(-2500f).setDuration(1000);
                note.animate().translationXBy(-2500f).setDuration(1000);
            } else {
                Button btn = new Button(getApplicationContext());
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
                layoutParams.addRule(RelativeLayout.CENTER_VERTICAL);
                btn.setLayoutParams(layoutParams);
                relativeLayoutBackground.addView(btn);
                btn.setTranslationX(2500f);
                btn.setText("Retour à l'accueil");
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                });
                ((View) exercices.get(exerciceNumber)).animate().translationXBy(-2500f).setDuration(1000);
                btn.animate().translationXBy(-2500f).setDuration(1000);
            }

        }
        exerciceNumber +=1;

    }

}
