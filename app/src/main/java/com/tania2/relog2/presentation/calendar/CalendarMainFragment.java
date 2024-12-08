package com.tania2.relog2.presentation.calendar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.tania2.relog2.R;

public class CalendarMainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calendar_activity_main, container,false);
        // Load fragment atas (kalender) dan bawah (scroll view)
        refresh();
        return view;
    }

    private void refresh() {
        loadFragment(new KalenderFragment(), R.id.fragmentTopContainer);
        loadFragment(new DiaryListFragment(), R.id.fragmentBottomContainer);
    }


    private void loadFragment(Fragment fragment, int containerId) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(containerId, fragment);
        transaction.commit();
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }
}