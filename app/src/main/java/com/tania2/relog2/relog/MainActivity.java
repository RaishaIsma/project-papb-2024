package com.tania2.relog2.relog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.tania2.relog2.R;
import com.tania2.relog2.presentation.article.ArticleMainActivity;
import com.tania2.relog2.presentation.calendar.CalendarMainActivity;
import com.tania2.relog2.presentation.diary.DiaryMainActivity;

public class MainActivity extends AppCompatActivity {


    private Button btnMyDiary,btnArtikel,btnCalendar,btnMoodTracker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnArtikel = findViewById(R.id.btnArtikel);
        this.btnMyDiary = findViewById(R.id.btnMyDiary);
        this.btnCalendar = findViewById(R.id.btnCalendar);
        this.btnMoodTracker = findViewById(R.id.btnMoodTracker);

        btnMyDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke WriteDiaryActivity
                Intent intent = new Intent(MainActivity.this, DiaryMainActivity.class);
                startActivity(intent);
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke WriteDiaryActivity
                Intent intent = new Intent(MainActivity.this, CalendarMainActivity.class);
                startActivity(intent);
            }
        });
        btnArtikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke WriteDiaryActivity
                Intent intent = new Intent(MainActivity.this, ArticleMainActivity.class);
                startActivity(intent);
            }
        });

        btnMoodTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Berpindah ke WriteDiaryActivity
                Intent intent = new Intent(MainActivity.this, ArticleMainActivity.class);
                startActivity(intent);
            }
        });






    }
}