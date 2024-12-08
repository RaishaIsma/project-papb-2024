package com.tania2.relog2.presentation.calendar;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tania2.relog2.R;

public class CalendarMainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity_main);

        // Load fragment atas (kalender) dan bawah (scroll view)
        loadFragment(new KalenderFragment(), R.id.fragmentTopContainer);
        loadFragment(new DiaryListFragment(), R.id.fragmentBottomContainer);
    }

    private void loadFragment(Fragment fragment, int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }
}