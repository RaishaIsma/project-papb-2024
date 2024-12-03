package com.example.articlerecycle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private TextView tvTitle, tvContent;
    private ImageView imgArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Inisialisasi View
        tvTitle = findViewById(R.id.tvTitleDetail);
        tvContent = findViewById(R.id.tvContent);
        imgArticle = findViewById(R.id.imgArticleDetail);

        // Ambil data dari Intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        int imageResId = intent.getIntExtra("imageResId", 0);

        // Set data ke View
        tvTitle.setText(title);
        tvContent.setText(content);
        imgArticle.setImageResource(imageResId);
    }
}
