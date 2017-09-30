package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.graphics.Color;
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

// Trouver le mot
public class ExerciceType2 extends RelativeLayout implements ExerciceType, View.OnClickListener {
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

    private String reponse;
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


    ArrayList<Button> buttons = new ArrayList<>();
    private void init() {
        inflate(getContext(), R.layout.exercicetype2_layout, this);
        this.enoncerView = (TextView) findViewById(R.id.enoncer);
        this.motFRView = (TextView) findViewById(R.id.phrase);
        this.btn1 = (Button) findViewById(R.id.rep1);
        this.btn2 = (Button) findViewById(R.id.rep2);
        this.btn3 = (Button) findViewById(R.id.rep3);
        this.btn4 = (Button) findViewById(R.id.rep4);
        enoncerView.setText(enoncer);
        motFRView.setText(motFR);
        if (!LeconDuJour.modeRelecture) {
            ArrayList<String> listMots = new ArrayList<>();
            listMots.add(bonMot);
            listMots.add(mauvaisMot1);
            listMots.add(mauvaisMot2);
            listMots.add(mauvaisMot3);
            long seed = System.nanoTime();
            Collections.shuffle(listMots, new Random(seed));
            buttons.add(btn1);
            buttons.add(btn2);
            buttons.add(btn3);
            buttons.add(btn4);
            for (int i = 0; i < buttons.size(); i++) {
                buttons.get(i).setOnClickListener(this);
                buttons.get(i).setText(listMots.get(i));
            }
        }else{
                btn1.setText(bonMot);
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
            if (reponse.matches(bonMot)){
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
