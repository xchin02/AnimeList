package com.example.id20001695.myndpsongsv3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    RatingBar ratingBar;
    EditText etTitle, etSingers, etYear;
    Button btnInsert, btnShow;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.etTitle2);
        etSingers = findViewById(R.id.etSingers2);
        etYear = findViewById(R.id.etYear2);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShow = findViewById(R.id.buttonShow);
        ratingBar = findViewById(R.id.ratingBar);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertSong(etTitle.getText().toString(), etSingers.getText().toString(), Integer.parseInt(etYear.getText().toString()), (int) ratingBar.getRating());
                if(inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ShowNDPSongs.class);
                startActivity(intent);
            }
        });

    }

}