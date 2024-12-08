package com.tania2.relog2.presentation.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.Catatan;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DiaryListFragment extends Fragment {
    private Button btnAdd;
    private RecyclerView rvNotes;

    private AppDatabase diaryDatabase;
    private ItemCalendarAdapter adapter;
    private List<Catatan> currentDiary;
    private String mDate = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_diary, container, false);

        btnAdd = view.findViewById(R.id.add_note);
        rvNotes = view.findViewById(R.id.rv_list_note);
        // Initialize database
        diaryDatabase = AppDatabase.getInstance(requireContext());
        adapter = new ItemCalendarAdapter();
        rvNotes.setAdapter(adapter);
        rvNotes.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter.setListener(new ItemCalendarAdapter.CalendarItemListener() {
            @Override
            public void onViewClick(Catatan data) {
                Intent intent = new Intent(requireContext(), CalendarAddUpdateDialog.class);
                intent.putExtra("id", data.id);
                intent.putExtra("date", data.getDate());
                intent.putExtra("note", data.getKonten());
                intent.putExtra("title", data.title);
                intent.putExtra("waktu", data.waktu);
                startActivity(intent);
            }
        });

        // Set current date
        String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        mDate = currentDate;
        // Load existing note if available
        loadDiary(currentDate);

        // Listen for date change from KalenderFragment
        getParentFragmentManager().setFragmentResultListener("selectedDate", this, (requestKey, result) -> {
            String date = result.getString("date");
//            btnAdd.setText(date);
            mDate = date;
            loadDiary(date);
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), CalendarAddUpdateDialog.class);
                intent.putExtra("date", mDate);
                startActivity(intent);
            }
        });
        return view;
    }


    private void loadDiary(String date) {
        new Thread(() -> {
            currentDiary = diaryDatabase.catatanDao().getCatatanByDate(date);
            adapter.setData(currentDiary);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    adapter.notifyDataSetChanged();
                    // Stuff that updates the UI
                }
            });
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDiary(mDate);
    }
}