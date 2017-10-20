package fr.fahim.sofyann.multilinguaprojet3.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import fr.fahim.sofyann.multilinguaprojet3.R;

public class Login extends AppCompatActivity implements View.OnKeyListener {
    EditText emailView;
    EditText passwordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        constructUI();
        if (ParseUser.getCurrentUser() != null){
            login();
        }
        passwordView.setOnKeyListener(this);
        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
    private void constructUI(){
        emailView = (EditText) findViewById(R.id.identifiantEditText);
        passwordView = (EditText)findViewById(R.id.passwordEditText);
    }

    public void loginOnClick(View view){
        String email = emailView.getText().toString();
        final String password = passwordView.getText().toString();
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("email", email);
        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(ParseUser object, ParseException e) {
                if(e == null && object != null){
                    String actualUsername = object.getUsername();
                    String role = object.getString("etudiantOuFormateur");
                    if (role.matches("etudiant")) {
                        ParseUser.logInInBackground(actualUsername, password, new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if (e == null && user != null) {
                                    login();
                                } else {
                                    Toast.makeText(Login.this, "Identifiant ou mot de passe incorrect", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Login.this, "Vous n'êtes pas étudiant", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Login.this, "Adresse email invalide", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void login(){
      Intent intent = new Intent(getApplicationContext(), MainActivity.class);
      startActivity(intent);
    }

    @Override
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        if (i == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
            loginOnClick(view);
        }
        return false;
    }
}
