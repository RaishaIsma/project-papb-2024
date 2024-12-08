package com.tania2.relog2.presentation.diary;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.Catatan;

import java.util.ArrayList;
import java.util.List;

public class DiaryMainFragment extends Fragment implements OnDiaryListener  {

    private ArrayList<Catatan> kumpulan;
    private KumpulanAdapter kumpulanAdapter;
    private RecyclerView recyclerView;
    private Button btnAdd;
    private AppDatabase db;  // Room Database

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.diary_activity_main, container, false);

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.rvKumpulan);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Initialize list of notes
        kumpulan = new ArrayList<>();
        kumpulanAdapter = new KumpulanAdapter(requireContext(), kumpulan);
        recyclerView.setAdapter(kumpulanAdapter);


        // Initialize Room Database
        db = AppDatabase.getInstance(requireContext());

        kumpulanAdapter.setKumpulanListener(new KumpulanAdapter.KumpulanListener() {
            @Override
            public void onRemoveClick(Catatan catatan) {
                deleteDiary(catatan);
            }
        });
        // Set up button to show WriteDiaryFragment
        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show WriteDiaryFragment when the button is clicked
                showWriteDiaryFragment();
            }
        });

        // Load data from Room database when the activity starts
        loadCatatan();

        return view;
    }

    private void deleteDiary(Catatan catatan){

        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage("Apakah yakin akan menghapus catatan berjudul : "+catatan.title+"?");
        builder.setTitle("Konfirmasi Hapus?");
        builder.setPositiveButton("Ya", (DialogInterface.OnClickListener) (dialog, whick)->{
            new Thread(() -> {
                db.catatanDao().delete(catatan);

                // Reload the list of catatan from Room database
                loadCatatan();
            }).start();
            dialog.dismiss();
            // Show a Toast message to confirm that the note was saved
            Toast.makeText(requireContext(), "Catatan berhasil dihapus", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Tidak", (DialogInterface.OnClickListener) (dialog, which)->{
            dialog.cancel();
        });
        builder.show();

    }
    private void showWriteDiaryFragment() {
        // Create a new instance of WriteDiaryFragment
        WriteDiary writeDiaryFragment = new WriteDiary(this::onSaveDiary);

        // Begin the FragmentTransaction to replace the current fragment with WriteDiaryFragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
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
        Toast.makeText(requireContext(), "Catatan berhasil disimpan", Toast.LENGTH_SHORT).show();

        // Optionally, you can return to the previous fragment after saving the note
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private void loadCatatan() {
        // Load data from Room database and update the RecyclerView
        new Thread(() -> {
            List<Catatan> data = db.catatanDao().getAll();
            kumpulan.clear();
            kumpulan.addAll(data);

            // Update the RecyclerView on the main thread
            getActivity().runOnUiThread(() -> kumpulanAdapter.notifyDataSetChanged());
        }).start();
    }
}
