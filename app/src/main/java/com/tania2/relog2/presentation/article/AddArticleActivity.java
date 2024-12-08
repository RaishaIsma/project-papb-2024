package com.tania2.relog2.presentation.article;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.ArticleModel;

public class AddArticleActivity extends AppCompatActivity {

    private EditText etTitle;
    private EditText etContent;
    private Button btnSave;

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_article);
        db = AppDatabase.getInstance(this);
        etTitle = findViewById(R.id.inputTitle);
        etContent = findViewById(R.id.inputContent);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String content = etContent.getText().toString();

                if (title.isEmpty()||content.isEmpty()){
                    Toast.makeText(AddArticleActivity.this, "Cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    db.articleDao().insert(new ArticleModel(title, content));
                    finish();
                }
            }
        });

    }
}