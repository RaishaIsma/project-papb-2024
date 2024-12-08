package com.tania2.relog2.relog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tania2.relog2.R;
import com.tania2.relog2.presentation.article.ArticleFragment;
import com.tania2.relog2.presentation.calendar.CalendarMainFragment;
import com.tania2.relog2.presentation.diary.DiaryMainFragment;

public class MainActivity2 extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    DiaryMainFragment diary = new DiaryMainFragment();
    ArticleFragment article = new ArticleFragment();
    CalendarMainFragment calendar = new CalendarMainFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
        bottomNavigationView.setSelectedItemId(R.id.bn_calendar);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.bn_article) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, article)
                    .commit();
            return true;
        } else if (itemId == R.id.bn_diary) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, diary)
                    .commit();
            return true;
        } else if (itemId == R.id.bn_calendar) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.flFragment, calendar)
                    .commit();
            return true;
        }
        return false;
    }
}
