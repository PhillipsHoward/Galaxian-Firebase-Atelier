package fr.wcs.galaxian_atelier_firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreatePlayerActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_player);

        final EditText nameInput = findViewById(R.id.input_name);
        final EditText scoreInput = findViewById(R.id.input_score);
        Button buttonCreate = findViewById(R.id.button_create_profile);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name  = nameInput.getText().toString();
                String bestScore = scoreInput.getText().toString();


                if(name.equals("") || bestScore.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.please_fill,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else if (!checkIfInteger(bestScore)) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            R.string.please_valid_score,
                            Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference playerRef = database.getReference("player");
                    playerRef.push().setValue(new Player(name, Integer.valueOf(bestScore)));
                    Toast toast = Toast.makeText(getApplicationContext(),
                            getString(R.string.the_player) + name + getString(R.string.has_been_registered),
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });


    }



    public static boolean checkIfInteger(String str) {
        boolean isInteger = false;
        try {
            Integer.parseInt(str);
            isInteger = true;
        } catch (NumberFormatException e) {

        }
        return isInteger;
    }






}
