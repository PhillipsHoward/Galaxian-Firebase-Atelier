package fr.wcs.galaxian_atelier_firebase;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    ArrayList<Player> players = new ArrayList<>();
    ListView mRankList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        mRankList = findViewById(R.id.ranking_list);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference playerRef = database.getReference("player");
        playerRef.orderByChild("bestScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                players.clear();
                for (DataSnapshot playerSnapShot: dataSnapshot.getChildren()) {
                    players.add(playerSnapShot.getValue(Player.class));
                }
                RankAdapter adapter = new RankAdapter(getApplicationContext(), players);
                mRankList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
