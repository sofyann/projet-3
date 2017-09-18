package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
    private Button phraseVraiView;
    private Button phraseFausseView1;
    private Button phraseFausseView2;
    private Button phraseFausseView3;


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
        this.phraseVraiView = (Button)findViewById(R.id.rep1);
        this.phraseFausseView1 = (Button)findViewById(R.id.rep2);
        this.phraseFausseView2 = (Button)findViewById(R.id.rep3);
        this.phraseFausseView3 = (Button)findViewById(R.id.rep4);
        enoncerView.setText(enoncer);
        phraseFRView.setText(phraseFR);
        phraseVraiView.setText(phraseVrai);
        phraseFausseView1.setText(phraseFausse1);
        phraseFausseView2.setText(phraseFausse2);
        phraseFausseView3.setText(phraseFausse3);
    }
}
