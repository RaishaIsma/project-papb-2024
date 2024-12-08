package com.tania2.relog2.presentation.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.Catatan;

public class CalendarAddUpdateDialog extends AppCompatActivity {
    private TextView tvDate;
    private EditText etNote;
    private Button saveBtn;
    private EditText etTitle;

    private Intent intent;

    private AppDatabase db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_diary);
        intent = getIntent();

        db = AppDatabase.getInstance(this);
        int id = intent.getIntExtra("id", 0);
        String date = intent.getStringExtra("date");
        String note = intent.getStringExtra("note");

        String title = intent.getStringExtra("title");
        String waktu = intent.getStringExtra("waktu");

        tvDate = findViewById(R.id.textViewDate);
        etNote = findViewById(R.id.editTextNote);
        etTitle = findViewById(R.id.editTextTitle);
        saveBtn = findViewById(R.id.buttonSave);
        tvDate.setText(date);
        etNote.setText(note);
        etTitle.setText(title);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newNote = etNote.getText().toString();
                String newTitle = etTitle.getText().toString();
                if (id == 0) {
                    db.catatanDao().insert(new Catatan(newTitle, newNote, date));
                } else {
                    db.catatanDao().update(new Catatan(id,newTitle, newNote, date, waktu));
                }
                finish();
            }
        });


    }

}
