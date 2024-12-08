package com.tania2.relog2.presentation.calendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tania2.relog2.R;
import com.tania2.relog2.model.Catatan;

import java.util.List;

public class ItemCalendarAdapter extends RecyclerView.Adapter<ItemCalendarAdapter.ViewHolder> {

    private List<Catatan> listData;
    private CalendarItemListener mListener;

    void setListener(CalendarItemListener listener) {
        mListener = listener;
    }

    void setData(List<Catatan> data) {
        listData = data;
    }

    interface CalendarItemListener {
        void onViewClick(Catatan data);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDate;
        TextView tvNote;
        TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNote = itemView.findViewById(R.id.editTextNote);
            tvDate = itemView.findViewById(R.id.textViewDate);
            tvTitle = itemView.findViewById(R.id.editTextTitle);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Catatan data = listData.get(position);
        holder.tvDate.setText(data.getDate()+" | "+data.waktu);
        holder.tvNote.setText(data.getKonten());
        holder.tvTitle.setText(data.title);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onViewClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        int data;
        if (listData != null) {
            data = listData.size();
        } else {
            data = 0;
        }
        return data;
    }


}
