package fr.fahim.sofyann.multilinguaprojet3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        Intent intent;
        switch (item.getItemId()){
            case R.id.maProgression:
                intent = new Intent(getApplicationContext(),MaProgression.class);
                startActivity(intent);
                return true;
            case R.id.datesDeCours:
                intent = new Intent(getApplicationContext(),DatesDeCours.class);
                startActivity(intent);
                return true;
            case R.id.contacterMonFormateur:
                intent = new Intent(getApplicationContext(),ContacterMonFormateur.class);
                startActivity(intent);
                return true;
            case R.id.logout:
                ParseUser.logOut();
                intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            default:
                return false;
        }
    }
    ImageView fond;
    TextView numLecon;
    TextView titreLecon;
    static int numChapitre = 0;
    String leconDuJour = "";
    Bitmap imageFond;
    static String nomLecon;
    byte[] bytes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constructUI();
        getLatestLecon();

    }
    public void toLeconDuJour(View view){
        Intent intent = new Intent(getApplicationContext(),LeconDuJour.class);
        intent.putExtra("leconDuJour", leconDuJour);
        intent.putExtra("imageFond",bytes);
        LeconDuJour.modeRelecture = false;
        startActivity(intent);
    }

    private void constructUI(){
        fond = (ImageView)findViewById(R.id.fond);
        numLecon = (TextView)findViewById(R.id.numLecon);
        titreLecon = (TextView)findViewById(R.id.titreLecon);
    }

    private void getJson(int numChapitre){
        this.numChapitre = numChapitre;
        Log.i("latestLecon", String.valueOf(this.numChapitre));
        ParseQuery<ParseObject> query = new  ParseQuery<ParseObject>("Lecon");
        query.whereEqualTo("name","lecon"+(this.numChapitre));
        query.setLimit(1);
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null && objects != null){
                    ParseFile file = (ParseFile) objects.get(0).get("json");
                    file.getDataInBackground(new GetDataCallback() {
                        @Override
                        public void done(byte[] data, ParseException e) {
                            if (e == null && data != null){
                                String jsonStr = "";
                                InputStream in = new ByteArrayInputStream(data);
                                Scanner reader = new Scanner(in);
                                while (reader.hasNext()){
                                    String line = reader.nextLine();
                                    jsonStr += line;
                                }
                                miseAJourTitreNum(jsonStr);
                            }
                        }
                    });
                }
            }
        });

    }

    private void miseAJourTitreNum(String s){
        try {
            JSONObject jsonObject = new JSONObject(s);
            String img = jsonObject.getString("img");

            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
            query.whereEqualTo("name", img);
            query.setLimit(1);
            query.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {
                    if (e == null){
                        if(objects.size() > 0){
                            for (ParseObject object : objects){
                                ParseFile file = (ParseFile)object.get("image");
                                file.getDataInBackground(new GetDataCallback() {
                                    @Override
                                    public void done(byte[] data, ParseException e) {
                                        if (e == null && data != null){
                                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                            addImageFond(bitmap, data);

                                        }
                                    }
                                });
                            }
                        }
                    }
                }
            });
            numLecon.setText("Leçon "+(numChapitre+1));
            nomLecon = jsonObject.getString("nom");
            titreLecon.setText(nomLecon);
            leconDuJour = jsonObject.getString("lecon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    private void addImageFond(Bitmap bitmap, byte[] bytes){
        imageFond = bitmap;
        fond.setImageBitmap(imageFond);
        if (imageFond == null){
            Log.i("image FOnd", "null");
        }
        this.bytes = bytes;
    }

    private void getLatestLecon(){
        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("LatestLecon");
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if (objects != null && objects.size()>0){
                        int latestLecon = 0;
                        for (ParseObject object : objects){
                            Date dateNextLeconUnlocked = object.getDate("nextLeconUnlocked");
                            Date currentDate = Calendar.getInstance().getTime();
                            if (currentDate.compareTo(dateNextLeconUnlocked) <= 0) {
                                int temp = object.getInt("latestLecon");
                                latestLecon = temp -1;
                            }else {
                                int temp = object.getInt("latestLecon");
                                latestLecon = temp;
                            }
                        }
                        getJson(latestLecon);
                    }
                }
            }
        });
    }

}
