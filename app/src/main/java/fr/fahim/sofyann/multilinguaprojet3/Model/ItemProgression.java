package fr.fahim.sofyann.multilinguaprojet3.Model;

/**
 * Created by Sofyann on 18/09/2017.
 */

public class ItemProgression {
    private String nomLecon;
    private String note;
    private String nameLeconInParse;
    private String nameExerciceInParse;
    private boolean validerOuNon;

    public ItemProgression(String nomLecon, String note, String nameLeconInParse, String nameExerciceInParse, boolean validerOuNon){
        this.nomLecon = nomLecon;
        this.note = note;
        this.nameLeconInParse = nameLeconInParse;
        this.nameExerciceInParse = nameExerciceInParse;
        this.validerOuNon = validerOuNon;
    }

    public String getNomLecon() {
        return nomLecon;
    }

    public String getNote() {
        return note;
    }

    public String getNameLeconInParse() {
        return nameLeconInParse;
    }

    public String getNameExerciceInParse() {
        return nameExerciceInParse;
    }

    public boolean isValiderOuNon() {
        return validerOuNon;
    }
}
