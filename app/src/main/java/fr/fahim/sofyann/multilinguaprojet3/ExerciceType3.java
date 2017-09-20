package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Sofyann on 16/09/2017.
 */
// Traduire phrase
public class ExerciceType3 extends RelativeLayout implements ExerciceType{
    public String type = "traduirePhrase";
    private String enoncer;
    private String phraseCorrect;
    private String phraseFR;

    private TextView enoncerView;
    private TextView phraseFRView;
    private EditText reponseUser;

    public ExerciceType3(String enoncer, String phraseCorrect, String phraseFR, Context context) {
        super(context);
        this.enoncer = enoncer;
        this.phraseCorrect = phraseCorrect;
        this.phraseFR = phraseFR;
        Log.i("Phrase correct", phraseCorrect);
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.exercicetype3_layout, this);
        this.enoncerView = (TextView)findViewById(R.id.enoncer);
        this.phraseFRView = (TextView)findViewById(R.id.phrase);
        this.reponseUser = (EditText)findViewById(R.id.reponse);
        enoncerView.setText(enoncer);
    }


    @Override
    public boolean resultat() {
        if (reponseUser.getText().toString().matches(phraseCorrect)){
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String getType() {
        return type;
    }
}
