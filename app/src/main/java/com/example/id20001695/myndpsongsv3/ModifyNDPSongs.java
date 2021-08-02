package com.example.id20001695.myndpsongsv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class ModifyNDPSongs extends AppCompatActivity {
    RatingBar ratingBar;
    EditText etID, etTitle, etSinger, etYear;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_ndpsongs);

        etID = findViewById(R.id.etSongID2);
        etTitle = findViewById(R.id.etTitle2);
        etSinger = findViewById(R.id.etSingers2);
        etYear = findViewById(R.id.etYear2);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);
        ratingBar = findViewById(R.id.ratingBar);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText(String.valueOf(data.getId()));
        etID.setEnabled(false);
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));
        ratingBar.setRating(data.getStars());

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = Integer.parseInt(etYear.getText().toString());
                DBHelper dbh = new DBHelper(ModifyNDPSongs.this);
                data.setTitle(etTitle.getText().toString()); //Note Class Method
                data.setSingers(etSinger.getText().toString());
                data.setYear(year);
                data.setStars((int) ratingBar.getRating());
                dbh.updateSong(data); //DBHelper Class Method
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ModifyNDPSongs.this);
                dbh.deleteSong(data.getId());
                dbh.close();
            }
        });
    }
}