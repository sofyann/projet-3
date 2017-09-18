package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by Sofyann on 16/09/2017.
 */
// Traduire phrase
public class ExerciceType3 extends RelativeLayout{
    private String type = "traduirePhrase";
    private String enoncer;
    private String phraseCorrect;
    private String phraseFR;

    public ExerciceType3(String enoncer, String phraseCorrect, String phraseFR, Context context) {
        super(context);
        this.enoncer = enoncer;
        this.phraseCorrect = phraseCorrect;
        this.phraseFR = phraseFR;
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.exercicetype3_layout, this);

    }
}
