package com.example.articlerecycle;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ArtikelAdapter extends RecyclerView.Adapter<ArtikelAdapter.ArticleViewHolder> {

    private final Context context;  // Deklarasi variabel context
    private final List<Artikel> articles;  // Deklarasi variabel articles

    // Konstruktor Adapter
    public ArtikelAdapter(Context context, List<Artikel> articles) {
        this.context = context;
        this.articles = articles;
    }

    // ViewHolder untuk setiap item
    public static class ArticleViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgArticle;
        public TextView tvTitle;
        public Button btnRead;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imgArticle = itemView.findViewById(R.id.imgArticle);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            btnRead = itemView.findViewById(R.id.btnRead);
        }
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rowview, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Artikel article = articles.get(position);
        holder.tvTitle.setText(article.title);
        holder.imgArticle.setImageResource(article.imageResId);

        holder.btnRead.setOnClickListener(v -> {
            // Membuka DetailActivity dengan data artikel
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("title", article.title);
            intent.putExtra("content", article.content);
            intent.putExtra("imageResId", article.imageResId);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
}
