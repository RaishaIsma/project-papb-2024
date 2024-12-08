package com.tania2.relog2.presentation.diary;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tania2.relog2.R;
import com.tania2.relog2.model.Catatan;

import java.util.List;

public class KumpulanAdapter extends RecyclerView.Adapter<KumpulanAdapter.VH> {

    private final Context ctx;
    private final List<Catatan> kumpulan;
    private KumpulanListener mListener;

    public interface KumpulanListener{
        void onRemoveClick(Catatan catatan);
    }

    public KumpulanAdapter(Context ctx, List<Catatan> kumpulan) {
        this.ctx = ctx;
        this.kumpulan = kumpulan;
    }
    public void setKumpulanListener(KumpulanListener listener){
        mListener = listener;
    }
    public static class VH extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvKonten;
        private final TextView tvDate;
        private final TextView tvTime;
        private final LinearLayout llRemove;

        public VH(@NonNull View itemView) {
            super(itemView);
            this.tvTitle = itemView.findViewById(R.id.tvTitle);
            this.tvKonten = itemView.findViewById(R.id.tvKonten);
            this.tvDate = itemView.findViewById(R.id.tvDate);
            this.tvTime = itemView.findViewById(R.id.tvTime);
            this.llRemove = itemView.findViewById(R.id.remove_diary);
        }

        public void setCatatan(Catatan c) {
            this.tvTitle.setText(c.getTitle());
            this.tvKonten.setText(c.getKonten());
            this.tvDate.setText(c.getDate());
            this.tvTime.setText(c.getTime());
        }

        public void setKontenClickListener(final Catatan catatan, final KumpulanAdapter adapter) {
            this.llRemove.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    adapter.mListener.onRemoveClick(catatan);
                }
            });
            this.tvKonten.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle maxLines untuk menampilkan seluruh konten
                    if (tvKonten.getMaxLines() == 1) {
                        tvKonten.setMaxLines(Integer.MAX_VALUE);  // Ekspansi konten
                    } else {
                        tvKonten.setMaxLines(1);  // Collaps konten
                    }
                    // Memberi tahu adapter untuk memperbarui item ini
//                    adapter.notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item untuk setiap item di RecyclerView
        View v = LayoutInflater.from(ctx).inflate(R.layout.diary_item_catatan, parent, false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        // Ambil data Catatan dari kumpulan berdasarkan posisi
        Catatan c = this.kumpulan.get(position);
        holder.setCatatan(c);
        holder.setKontenClickListener(c, this);
    }

    @Override
    public int getItemCount() {
        return kumpulan.size();
    }
}
