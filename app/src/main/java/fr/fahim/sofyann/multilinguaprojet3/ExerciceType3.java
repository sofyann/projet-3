package fr.fahim.sofyann.multilinguaprojet3;

/**
 * Created by Sofyann on 16/09/2017.
 */
// Traduire phrase
public class ExerciceType3 {
    private String type = "traduirePhrase";
    private String enoncer;
    private String phraseCorrect;
    private String phraseFR;

    public ExerciceType3(String enoncer, String phraseCorrect, String phraseFR) {
        this.enoncer = enoncer;
        this.phraseCorrect = phraseCorrect;
        this.phraseFR = phraseFR;
    }

    public String getEnoncer() {
        return enoncer;
    }

    public String getPhraseCorrect() {
        return phraseCorrect;
    }

    public String getPhraseFR() {
        return phraseFR;
    }

    public String getType() {
        return type;
    }
}
