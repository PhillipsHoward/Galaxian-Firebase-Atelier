package fr.wcs.galaxian_atelier_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class RankAdapter extends ArrayAdapter<Player> {


    public RankAdapter(Context context, ArrayList<Player> players) {
        super(context, 0, players);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Player player = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_player, parent, false);
        }
        // Lookup view for data population
        TextView name = convertView.findViewById(R.id.text_name);
        TextView score = convertView.findViewById(R.id.text_best_score);

        // Populate the data into the template view using the data object
        name.setText("Nom : " + player.getName());
        score.setText("Best Score : " + String.valueOf(player.getBestScore()));

        // Return the completed view to render on screen
        return convertView;
    }


}