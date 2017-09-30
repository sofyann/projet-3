package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Sofyann on 18/09/2017.
 */

public class Note extends RelativeLayout {
    private int note;
    private float numberOfExercises;
    private TextView messageView;
    private TextView noteView;
    private boolean validerOuNon;

    public Note(Context context, int note, int numberOfExercises) {
        super(context);
        this.note = note;
        this.numberOfExercises = numberOfExercises;
        init();
    }

    private void init(){
        inflate(getContext(), R.layout.note_layout, this);
        messageView = (TextView)findViewById(R.id.messageResultat);
        noteView = (TextView)findViewById(R.id.note);
        if (note < (float)(numberOfExercises/2)){
            messageView.setText("Dommage ! Vous n'avez pas réussi cette\nleçon avec la note de :");
            noteView.setText(note+"/"+(int)numberOfExercises);
            validerOuNon = false;
        } else {
            messageView.setText("Bravo ! Vous avez réussi cette leçon\navec la note de :");
            noteView.setText(note+"/"+(int)numberOfExercises);
            validerOuNon = true;
        }
        ParseObject progression = new ParseObject("Progression");
        progression.put("nameLecon", MainActivity.nomLecon);
        progression.put("nameLeconInParse", "lecon"+MainActivity.numChapitre);
        progression.put("nameExerciceInParse", "exercice"+Exercice.numExerciceInParse);
        progression.put("validerOuNon", validerOuNon);
        progression.put("note", note+"/"+(int)numberOfExercises);
        progression.put("username", ParseUser.getCurrentUser().getUsername());
        progression.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null){
                    Toast.makeText(getContext(), "Problème de connexion, veuillez réessayer", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LatestLecon");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if (objects.size() >0){
                        objects.get(0).put("latestLecon", (MainActivity.numChapitre+1));
                        Date date = Calendar.getInstance().getTime();
                        Log.i("date", String.valueOf(date));
                        date.setMinutes(date.getMinutes()+2);
                        Log.i("date + 24", String.valueOf(date));
                        objects.get(0).put("nextLeconUnlocked", date);
                        objects.get(0).saveInBackground();
                    }
                }
            }
        });

    }

}
