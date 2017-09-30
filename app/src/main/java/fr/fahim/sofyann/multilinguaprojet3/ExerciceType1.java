package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Sofyann on 16/09/2017.
 */

// Choisir Phrase
public class ExerciceType1 extends RelativeLayout implements ExerciceType, View.OnClickListener{
    private String type = "choisirPhrase";
    private String enoncer;
    private String phraseFR;
    private String phraseVrai;
    private String phraseFausse1;
    private String phraseFausse2;
    private String phraseFausse3;

    private TextView enoncerView;
    private TextView phraseFRView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;

    private String reponse;

    public ExerciceType1(String enoncer, String phraseFR, String phraseVrai, String phraseFausse1, String phraseFausse2, String phraseFausse3, Context context){
        super(context);
        this.enoncer = enoncer;
        this.phraseFR = phraseFR;
        this.phraseVrai = phraseVrai;
        this.phraseFausse1 = phraseFausse1;
        this.phraseFausse2 = phraseFausse2;
        this.phraseFausse3 = phraseFausse3;
        init();
    }

    ArrayList<Button> buttons = new ArrayList<>();
    private void init(){
        inflate(getContext(), R.layout.exercicetype1_layout,this);
        this.enoncerView = (TextView)findViewById(R.id.enoncer);
        this.phraseFRView = (TextView)findViewById(R.id.phraseFR);
        this.btn1 = (Button)findViewById(R.id.rep1);
        this.btn2 = (Button)findViewById(R.id.rep2);
        this.btn3 = (Button)findViewById(R.id.rep3);
        this.btn4= (Button)findViewById(R.id.rep4);
        enoncerView.setText(enoncer);
        phraseFRView.setText(phraseFR);
        if (!LeconDuJour.modeRelecture){
            ArrayList<String> listPhrases = new ArrayList<>();
            listPhrases.add(phraseVrai);
            listPhrases.add(phraseFausse1);
            listPhrases.add(phraseFausse2);
            listPhrases.add(phraseFausse3);
            long seed = System.nanoTime();
            Collections.shuffle(listPhrases,new Random(seed));
            buttons.add(btn1);
            buttons.add(btn2);
            buttons.add(btn3);
            buttons.add(btn4);
            for (int i = 0; i < buttons.size(); i++){
                buttons.get(i).setOnClickListener(this);
                buttons.get(i).setText(listPhrases.get(i));
            }
        } else {
            btn1.setText(phraseVrai);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rep1:
                if (reponse != null){
                    for (int i = 0; i < buttons.size(); i++){
                        if (reponse.matches(String.valueOf(buttons.get(i).getText())));
                        buttons.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                reponse = btn1.getText().toString();
                btn1.setTextColor(Color.parseColor("#85CDC1"));
                break;
            case R.id.rep2:
                if (reponse != null){
                    for (int i = 0; i < buttons.size(); i++){
                        if (reponse.matches(String.valueOf(buttons.get(i).getText())));
                        buttons.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                reponse = btn2.getText().toString();
                btn2.setTextColor(Color.parseColor("#85CDC1"));
                break;
            case R.id.rep3:
                if (reponse != null){
                    for (int i = 0; i < buttons.size(); i++){
                        if (reponse.matches(String.valueOf(buttons.get(i).getText())));
                        buttons.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                reponse = btn3.getText().toString();
                btn3.setTextColor(Color.parseColor("#85CDC1"));
                break;
            case R.id.rep4:
                if (reponse != null){
                    for (int i = 0; i < buttons.size(); i++){
                        if (reponse.matches(String.valueOf(buttons.get(i).getText())));
                        buttons.get(i).setTextColor(Color.parseColor("#FFFFFF"));
                    }
                }
                reponse = btn4.getText().toString();
                btn4.setTextColor(Color.parseColor("#85CDC1"));
                break;
        }
    }

    @Override
    public boolean resultat() {
      if (reponse != null){
          if (reponse.matches(phraseVrai)){
              return true;
          } else return false;
      }else return false;
    }

    @Override
    public String getType() {
        return type;
    }

    public Button getBtn2() {
        return btn2;
    }

    public Button getBtn3() {
        return btn3;
    }

    public Button getBtn4() {
        return btn4;
    }
}
