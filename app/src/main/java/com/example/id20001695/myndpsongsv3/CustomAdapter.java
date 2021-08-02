package com.example.id20001695.myndpsongsv3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Song> songList;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        songList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // "Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textViewSongTitle);
        TextView tvYear = rowView.findViewById(R.id.textViewYear);
        TextView tvSinger = rowView.findViewById(R.id.textViewSinger);
        ImageView imgNew = rowView.findViewById(R.id.imageViewNew);
        RatingBar ratingStar = rowView.findViewById(R.id.ratingBar);

        // Obtain the Android Version information based on the position
        Song currentVersion = songList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvYear.setText((currentVersion.getYear() + "  "));
        tvSinger.setText(currentVersion.getSingers());
        if(currentVersion.getYear() >= 2019) {
            imgNew.setVisibility(View.VISIBLE);
        }
        else {
            imgNew.setVisibility(View.INVISIBLE);
        }

        ratingStar.setRating(currentVersion.getStars());

        return rowView;
    }
}

