package com.tania2.relog2.presentation.diary;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.Catatan;

import java.util.ArrayList;
import java.util.List;

public class DiaryMainActivity extends AppCompatActivity implements OnDiaryListener {

    private ArrayList<Catatan> kumpulan;
    private KumpulanAdapter kumpulanAdapter;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private AppDatabase db;  // Room Database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_activity_main);

        // Set up RecyclerView
        recyclerView = findViewById(R.id.rvKumpulan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize list of notes
        kumpulan = new ArrayList<>();
        kumpulanAdapter = new KumpulanAdapter(this, kumpulan);
        recyclerView.setAdapter(kumpulanAdapter);

        // Initialize Room Database
        db = AppDatabase.getInstance(this);

        // Set up button to show WriteDiaryFragment
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show WriteDiaryFragment when the button is clicked
                showWriteDiaryFragment();
            }
        });

        // Load data from Room database when the activity starts
        loadCatatan();
    }

    private void showWriteDiaryFragment() {
        // Create a new instance of WriteDiaryFragment
        WriteDiary writeDiaryFragment = new WriteDiary(this::onSaveDiary);

        // Begin the FragmentTransaction to replace the current fragment with WriteDiaryFragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, writeDiaryFragment);
        transaction.addToBackStack(null);  // Allow back navigation
        transaction.commit();
    }

    // Method from OnDiaryListener interface to handle saving new diary entry
    @Override
    public void onSaveDiary(Catatan catatan) {
        // Save the new note to Room database
        new Thread(() -> {
            db.catatanDao().insert(catatan);

            // Reload the list of catatan from Room database
            loadCatatan();
        }).start();

        // Show a Toast message to confirm that the note was saved
        Toast.makeText(this, "Catatan berhasil disimpan", Toast.LENGTH_SHORT).show();

        // Optionally, you can return to the previous fragment after saving the note
        getSupportFragmentManager().popBackStack();
    }

    private void loadCatatan() {
        // Load data from Room database and update the RecyclerView
        new Thread(() -> {
            List<Catatan> data = db.catatanDao().getAll();
            kumpulan.clear();
            kumpulan.addAll(data);

            // Update the RecyclerView on the main thread
            runOnUiThread(() -> kumpulanAdapter.notifyDataSetChanged());
        }).start();
    }
}
