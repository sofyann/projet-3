package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Sofyann on 16/09/2017.
 */

// Choisir Phrase
public class ExerciceType1 extends RelativeLayout{
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

    public String getType() {
        return type;
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
//        randomSetText();
        btn1.setText(phraseVrai);
        btn2.setText(phraseFausse1);
        btn3.setText(phraseFausse2);
        btn4.setText(phraseFausse3);
    }
    private void randomSetText(){
        ArrayList<String> ar1 = new ArrayList<>();
        ar1.add(phraseVrai);
        ar1.add(phraseFausse1);
        ar1.add(phraseFausse2);
        ar1.add(phraseFausse3);
        ArrayList<String> arRandomlist = new ArrayList<>();

        Log.i("List 1", ar1.toString());
        Log.i("Random list", arRandomlist.toString());



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
