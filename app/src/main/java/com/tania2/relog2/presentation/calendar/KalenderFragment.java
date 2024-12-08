package com.tania2.relog2.presentation.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.fragment.app.Fragment;

import com.tania2.relog2.R;
import com.tania2.relog2.relog.MainActivity2;

import java.time.LocalDate;
import java.util.Calendar;

public class KalenderFragment extends Fragment {

    private CalendarView calendarView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kalender, container, false);
        calendarView = view.findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener((calendarView, year, month, dayOfMonth) -> {
            String selectedDate =  String.format("%02d", dayOfMonth)+"-"+(month + 1)+"-"+year;

            // Kirim tanggal ke fragment bawah (DiaryFragment)
            if (getActivity() instanceof MainActivity2) {
                Bundle bundle = new Bundle();
                bundle.putString("date", selectedDate);
                ((MainActivity2) getActivity()).getSupportFragmentManager()
                        .setFragmentResult("selectedDate", bundle);
            }
        });

        calendarView.setDate(Calendar.getInstance().getTimeInMillis(), true, true);

        return view;
    }
}
