package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sofyann on 16/09/2017.
 */

// Trouver le mot
public class ExerciceType2 extends RelativeLayout {
    private String type = "trouverMot";
    private String enoncer;
    private String motFR;
    private String bonMot;
    private String mauvaisMot1;
    private String mauvaisMot2;
    private String mauvaisMot3;

    private TextView enoncerView;
    private TextView motFRView;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    public ExerciceType2(String enoncer, String bonMot, String motFR, String mauvaisMot1, String mauvaisMot2, String mauvaisMot3, Context context){
        super(context);
        this.enoncer = enoncer;
        this.bonMot = bonMot;
        this.motFR = motFR;
        this.mauvaisMot1 = mauvaisMot1;
        this.mauvaisMot2 = mauvaisMot2;
        this.mauvaisMot3 = mauvaisMot3;
        init();
    }


    private void init(){
        inflate(getContext(), R.layout.exercicetype2_layout, this);
        this.enoncerView = (TextView)findViewById(R.id.enoncer);
        this.motFRView = (TextView)findViewById(R.id.phrase);
        this.btn1 = (Button)findViewById(R.id.rep1);
        this.btn2 = (Button)findViewById(R.id.rep2);
        this.btn3 = (Button)findViewById(R.id.rep3);
        this.btn4= (Button)findViewById(R.id.rep4);
        enoncerView.setText(enoncer);
        motFRView.setText(motFR);
//        randomSetText();
        btn1.setText(bonMot);
        btn2.setText(mauvaisMot1);
        btn3.setText(mauvaisMot2);
        btn4.setText(mauvaisMot3);
    }

}
