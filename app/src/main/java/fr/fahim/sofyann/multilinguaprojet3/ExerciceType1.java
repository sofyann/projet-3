package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
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
        ArrayList<String> listPhrases = new ArrayList<>();
        listPhrases.add(phraseVrai);
        listPhrases.add(phraseFausse1);
        listPhrases.add(phraseFausse2);
        listPhrases.add(phraseFausse3);
        long seed = System.nanoTime();
        Collections.shuffle(listPhrases,new Random(seed));
        btn1.setText(listPhrases.get(0));
        btn2.setText(listPhrases.get(1));
        btn3.setText(listPhrases.get(2));
        btn4.setText(listPhrases.get(3));
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rep1:
                reponse = btn1.getText().toString();
                break;
            case R.id.rep2:
                reponse = btn2.getText().toString();
                break;
            case R.id.rep3:
                reponse = btn3.getText().toString();
                break;
            case R.id.rep4:
                reponse = btn4.getText().toString();
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

    public Button getBtn1() {
        return btn1;
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
