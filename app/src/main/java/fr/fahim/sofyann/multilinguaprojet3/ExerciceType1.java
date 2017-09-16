package fr.fahim.sofyann.multilinguaprojet3;

/**
 * Created by Sofyann on 16/09/2017.
 */

// Choisir Phrase
public class ExerciceType1 {
    private String enoncer;
    private String phraseFR;
    private String phraseVrai;
    private String phraseFausse1;
    private String phraseFausse2;
    private String phraseFausse3;

    public ExerciceType1(String enoncer, String phraseFR, String phraseVrai, String phraseFausse1, String phraseFausse2, String phraseFausse3){
        this.enoncer = enoncer;
        this.phraseFR = phraseFR;
        this.phraseVrai = phraseVrai;
        this.phraseFausse1 = phraseFausse1;
        this.phraseFausse2 = phraseFausse2;
        this.phraseFausse3 = phraseFausse3;
    }

    public String getEnoncer() {
        return enoncer;
    }

    public String getPhraseFR() {
        return phraseFR;
    }

    public String getPhraseVrai() {
        return phraseVrai;
    }

    public String getPhraseFausse1() {
        return phraseFausse1;
    }

    public String getPhraseFausse2() {
        return phraseFausse2;
    }

    public String getPhraseFausse3() {
        return phraseFausse3;
    }
}
