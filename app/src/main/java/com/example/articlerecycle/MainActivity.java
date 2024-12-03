package com.example.articlerecycle;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvArticles;
    private ArtikelAdapter ArtikelAdapter;
    private List<Artikel> articles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvArticles = findViewById(R.id.rvArticles);

        // Data artikel
        articles = new ArrayList<>();
        articles.add(new Artikel("Ketahui 5 Manfaat Meditasi untuk Kesehatan Mental", R.drawable.g1, "Meditasi membantu mengurangi stres, meningkatkan konsentrasi, dan memperbaiki kualitas tidur."));
        articles.add(new Artikel("Mengenali Penyebab Bad Mood dan Cara Mengatasinya", R.drawable.g2, "Bad mood bisa disebabkan oleh berbagai faktor seperti kurang tidur, stres, atau pola makan yang buruk."));

        ArtikelAdapter = new ArtikelAdapter(this, articles);
        rvArticles.setAdapter(ArtikelAdapter);
        rvArticles.setLayoutManager(new LinearLayoutManager(this));
    }
}

