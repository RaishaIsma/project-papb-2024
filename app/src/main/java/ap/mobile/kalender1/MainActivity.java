package ap.mobile.kalender1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editTextNote;
    private TextView textViewNote;
    private TextView textViewDate;
    private Button buttonSave;
    private Button buttonEdit;
    private SharedPreferences sharedPreferences;
    private int year, month, day;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        editTextNote = findViewById(R.id.editTextNote);
        textViewNote = findViewById(R.id.textViewNote);
        textViewDate = findViewById(R.id.textViewDate);
        buttonSave = findViewById(R.id.buttonSave);
        buttonEdit = findViewById(R.id.buttonEdit);
        sharedPreferences = getSharedPreferences("Notes", Context.MODE_PRIVATE);

        // Listener untuk CalendarView
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> updateDate(year, month, dayOfMonth));

        // Listener untuk tombol simpan
        buttonSave.setOnClickListener(v -> {
            String newNote = editTextNote.getText().toString().trim();
            if (!newNote.isEmpty()) {
                String dateKey = year + "_" + month + "_" + day;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(dateKey, newNote);
                editor.apply(); // Simpan data ke SharedPreferences

                // Update UI setelah disimpan
                textViewNote.setText(newNote);
                textViewDate.setText("Tanggal: " + day + "/" + (month + 1) + "/" + year);
                editTextNote.setText(""); // Kosongkan EditText
                buttonEdit.setVisibility(View.VISIBLE); // Tampilkan tombol edit
            }
        });

        // Listener untuk tombol edit
        buttonEdit.setOnClickListener(v -> {
            String updatedNote = editTextNote.getText().toString().trim();
            if (!updatedNote.isEmpty()) {
                String dateKey = year + "_" + month + "_" + day;
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(dateKey, updatedNote);
                editor.apply(); // Simpan perubahan ke SharedPreferences

                // Update UI setelah perubahan
                textViewNote.setText(updatedNote);
                editTextNote.setText(""); // Kosongkan EditText
            }
        });
    }

    // Fungsi untuk memperbarui tanggal yang dipilih
    private void updateDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;

        String dateKey = year + "_" + month + "_" + day;
        String savedNotes = sharedPreferences.getString(dateKey, null);

        if (savedNotes != null) {
            // Tampilkan catatan yang sudah disimpan
            textViewNote.setText(savedNotes);
            textViewDate.setText("Tanggal: " + day + "/" + (month + 1) + "/" + year);
            editTextNote.setText(savedNotes); // Tampilkan catatan dalam EditText untuk editing
            buttonEdit.setVisibility(View.VISIBLE); // Tampilkan tombol edit
        } else {
            // Tidak ada catatan untuk tanggal tersebut
            textViewNote.setText("Tidak ada catatan yang disimpan");
            textViewDate.setText("Tanggal: " + day + "/" + (month + 1) + "/" + year);
            buttonEdit.setVisibility(View.GONE); // Sembunyikan tombol edit
        }
    }
}
