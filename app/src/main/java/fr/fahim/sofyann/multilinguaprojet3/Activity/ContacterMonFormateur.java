package fr.fahim.sofyann.multilinguaprojet3.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

import fr.fahim.sofyann.multilinguaprojet3.R;


public class ContacterMonFormateur extends AppCompatActivity {
    String formateur = "";
    String formateurNumber = "";
    ArrayList<String> messages = new ArrayList<>();
    ArrayAdapter arrayAdapter;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.appeler, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.appeler:
                Intent appel = new Intent(Intent.ACTION_DIAL, Uri.parse(formateurNumber));
                startActivity(appel);

                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacter_mon_formateur);

        ParseQuery<ParseObject> queryEtudiant = ParseQuery.getQuery("EtudiantFormateur");
        queryEtudiant.whereEqualTo("etudiant", ParseUser.getCurrentUser().getUsername());
        queryEtudiant.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null){
                    ParseObject object = objects.get(0);
                    updateFormateur(object.getString("formateur"));
                }
            }
        });



    }

    private void updateFormateur(final String formateur){
        this.formateur = formateur;
        setTitle(formateur);

        ParseQuery<ParseObject> queryNumber = ParseQuery.getQuery("Contact");
        queryNumber.whereEqualTo("username", formateur);
        queryNumber.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null){
                    String formateurNumber = "tel:"+objects.get(0).getString("tel");
                    Log.i("tel formateur", formateurNumber);
                    updateNum(formateurNumber);
                }
            }
        });


        ListView chatListView = (ListView)findViewById(R.id.chatListView);
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, messages);
        chatListView.setAdapter(arrayAdapter);
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Message");
        query.whereEqualTo("sender",ParseUser.getCurrentUser().getUsername());
        query.whereEqualTo("receiver", this.formateur);

        ParseQuery<ParseObject> query1 = new ParseQuery<ParseObject>("Message");
        query1.whereEqualTo("receiver", ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo("sender", this.formateur);
        Log.i("formateur", this.formateur);
        Log.i("etudiant", ParseUser.getCurrentUser().getUsername());
        List<ParseQuery<ParseObject>> queries = new ArrayList<ParseQuery<ParseObject>>();
        queries.add(query);
        queries.add(query1);

        ParseQuery<ParseObject> query2 = ParseQuery.or(queries);
        query2.orderByAscending("createdAt");

        query2.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null){
                    if (objects.size() >0){
                        messages.clear();
                        for (ParseObject message : objects){
                            String messageContent = message.getString("message");
                            if (!message.getString("sender").equals(ParseUser.getCurrentUser().getUsername())){
                                messageContent = formateur+"> "+messageContent;
                            }else {
                                messageContent = "Vous> "+messageContent;
                            }
                            messages.add(messageContent);
                        }
                        arrayAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }

    public void sendMessage(View view){
        final EditText chatEditText = (EditText)findViewById(R.id.chatEditText);
        ParseObject message = new ParseObject("Message");
        message.put("sender", ParseUser.getCurrentUser().getUsername());
        message.put("receiver", formateur);
        message.put("message", chatEditText.getText().toString());
        message.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e == null){
                    Toast.makeText(ContacterMonFormateur.this, "Le message est envoyé", Toast.LENGTH_SHORT).show();
                    messages.add("Vous> "+chatEditText.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    chatEditText.setText("");
                }
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    private void updateNum(String formateurNumber){
        this.formateurNumber = formateurNumber;
    }
}
