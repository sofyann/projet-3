package fr.fahim.sofyann.multilinguaprojet3;

/**
 * Created by Sofyann on 16/09/2017.
 */

// Trouver le mot
public class ExerciceType2 {
    private String type = "trouverMot";
    private String enoncer;
    private String motFR;
    private String bonMot;
    private String mauvaisMot1;
    private String mauvaisMot2;
    private String mauvaisMot3;

    public ExerciceType2(String enoncer, String bonMot,String motFR, String mauvaisMot1, String mauvaisMot2, String mauvaisMot3){
        this.enoncer = enoncer;
        this.bonMot = bonMot;
        this.motFR = motFR;
        this.mauvaisMot1 = mauvaisMot1;
        this.mauvaisMot2 = mauvaisMot2;
        this.mauvaisMot3 = mauvaisMot3;
    }

    public String getEnoncer() {
        return enoncer;
    }

    public String getBonMot() {
        return bonMot;
    }

    public String getMauvaisMot1() {
        return mauvaisMot1;
    }

    public String getMauvaisMot2() {
        return mauvaisMot2;
    }

    public String getMauvaisMot3() {
        return mauvaisMot3;
    }

    public String getType() {
        return type;
    }

    public String getMotFR() {
        return motFR;
    }
}
