package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

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
        ImageView sendResult = (ImageView)findViewById(R.id.sendResult);
        sendResult.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ParseObject progression = new ParseObject("Progression");
                progression.put("nameLecon", MainActivity.nomLecon);
                progression.put("nameLeconInParse", "lecon"+MainActivity.numChapitre);
                progression.put("nameExerciceInParse", "exercice"+MainActivity.numChapitre);
                progression.put("validerOuNon", validerOuNon);
                progression.put("note", note+"/"+(int)numberOfExercises);
                progression.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e != null){
                            Toast.makeText(getContext(), "Problème de connexion, veuillez réessayer", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
