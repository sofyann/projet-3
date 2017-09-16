package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

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
import java.util.Scanner;

public class Exercice extends AppCompatActivity {
    ImageView fond;
    ArrayList exercices = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        constructUI();
        Intent intent = getIntent();
        fond.setImageResource(intent.getIntExtra("img",0));
        getJson();
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
    }

    private void getJson(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Exercice");
        query.whereEqualTo("name", "exercice"+MainActivity.numChapitre);
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
                                Log.i("json", jsonStr);
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
            ArrayList<JSONObject> jsonObjects = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                String type = jsonObject.getString("type");
                String enoncer = jsonObject.getString("enoncer");
                if (type.matches("choisirPhrase")){
                    String phraseVrai = jsonObject.getString("PhraseVrai");
                    String phraseFR = jsonObject.getString("PhraseFR");
                    String phraseFausse1 = jsonObject.getString("PhraseFausse1");
                    String phraseFausse2 = jsonObject.getString("PhraseFausse2");
                    String phraseFausse3 = jsonObject.getString("PhraseFausse3");
                    ExerciceType1 exercice = new ExerciceType1(enoncer,phraseFR,phraseVrai,phraseFausse1,phraseFausse2,phraseFausse3);
                    exercices.add(exercice);
                } else if (type.matches("trouverMot")){
                    String bonMot = jsonObject.getString("bonMot");
                    String mauvaisMot1 = jsonObject.getString("mauvaisMot1");
                    String mauvaisMot2 = jsonObject.getString("mauvaisMot2");
                    String mauvaisMot3 = jsonObject.getString("mauvaisMot3");
                    ExerciceType2 exercice = new ExerciceType2(enoncer, bonMot, mauvaisMot1, mauvaisMot2, mauvaisMot3);
                    exercices.add(exercice);
                } else if (type.matches("traduirePhrase")){
                    String phraseCorrect = jsonObject.getString("phraseCorrect");
                    ExerciceType3 exercice = new ExerciceType3(enoncer, phraseCorrect);
                    exercices.add(exercice);
                }
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void next(View view){

    }
}
