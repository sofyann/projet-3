package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Exercice extends AppCompatActivity {
    ImageView fond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        constructUI();
        Intent intent = getIntent();
        fond.setImageResource(intent.getIntExtra("img",0));
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
    }


    public void next(View view){

    }
}
