package com.tania2.relog2.presentation.diary;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.tania2.relog2.R;
import com.tania2.relog2.model.Catatan;

public class WriteDiary extends Fragment {

    private EditText etTitle, etKonten;
    private Button btnSimpan;
    private OnDiaryListener onDiaryListener; // Deklarasi interface

    // Interface untuk mengirim data ke MainActivity
    WriteDiary(
            OnDiaryListener  listener
    ){
        super();
        onDiaryListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_write_diary, container, false);

        etTitle = rootView.findViewById(R.id.etTitle);
        etKonten = rootView.findViewById(R.id.etKonten);
        btnSimpan = rootView.findViewById(R.id.btnSimpan);

        // Set click listener for Save button
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String konten = etKonten.getText().toString();

                // Validate the inputs
                if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(konten)) {
                    // Create a new note object
                    Catatan newCatatan = new Catatan(title, konten);

                    // Call the method in MainActivity through the interface
                    onDiaryListener.onSaveDiary(newCatatan);

                    // Optionally, clear the input fields
                    etTitle.setText("");
                    etKonten.setText("");
                }
            }
        });

        return rootView;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        // Ensure that the parent activity implements the interface
//        if (context instanceof OnDiaryListener) {
//            onDiaryListener = (OnDiaryListener) context;
//        } else {
//            throw new RuntimeException(context.toString() + " must implement OnDiaryListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        onDiaryListener = null;
    }
}
