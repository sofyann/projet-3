package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
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
        btn1.setText(phraseVrai);
        btn2.setText(phraseFausse1);
        btn3.setText(phraseFausse2);
        btn4.setText(phraseFausse3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        Log.i("bonne rep", phraseVrai);
        Log.i("rep du bouton", btn1.getText().toString());
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
        Log.i("Comparaison entre ", reponse +" et "+ phraseVrai);
        if (reponse.matches(phraseVrai)){
            return true;
        } else return false;
    }


}
