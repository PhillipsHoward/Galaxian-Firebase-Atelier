package fr.wcs.galaxian_atelier_firebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView mBestPlayerName;
    TextView mBestScore;
    Button mGoToCreatePage;
    Button mGotoRankPage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBestPlayerName = findViewById(R.id.name_best_player);
        mBestScore = findViewById(R.id.highest_player_score);

        mGoToCreatePage = findViewById(R.id.button_create_profile);
        mGotoRankPage = findViewById(R.id.button_ranking);

        mGoToCreatePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, CreatePlayerActivity.class);
                startActivity(intent);
            }
        });

        mGotoRankPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (MainActivity.this, RankingActivity.class);
                startActivity(intent);
            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference playerRef = database.getReference("player");
        playerRef.orderByChild("bestScore").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot playerSnapshot : dataSnapshot.getChildren()) {
                    Player player = playerSnapshot.getValue(Player.class);
                    mBestPlayerName.setText(player.getName());
                    mBestScore.setText(String.valueOf(player.getBestScore()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
