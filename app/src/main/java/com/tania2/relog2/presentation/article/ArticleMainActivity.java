package com.tania2.relog2.presentation.article;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.tania2.relog2.R;

public class ArticleMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new ArticleFragment())
                    .commit();
        }
    }
}
