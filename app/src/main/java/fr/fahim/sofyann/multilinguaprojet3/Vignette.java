package fr.fahim.sofyann.multilinguaprojet3;

/**
 * Created by Sofyann on 16/09/2017.
 */

public class Vignette {
    private String mot;
    private String phrase;
    private String phraseTrad;

    public Vignette(String mot, String phrase, String phraseTrad){
        this.mot = mot;
        this.phrase = phrase;
        this.phraseTrad = phraseTrad;
    }

    public String getMot() {
        return mot;
    }

    public String getPhrase() {
        return phrase;
    }

    public String getPhraseTrad() {
        return phraseTrad;
    }
}
