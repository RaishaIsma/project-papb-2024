package com.tania2.relog2.presentation.article;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.tania2.relog2.R;
import com.tania2.relog2.database.AppDatabase;
import com.tania2.relog2.model.ArticleModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ArticleFragment extends Fragment {
    RecyclerView recyclerView;
    ArticleAdapter articleAdapter;
    List<ArticleModel> articleModelList;
    AppDatabase db;

    private ExtendedFloatingActionButton addBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        addBtn = view.findViewById(R.id.add_article);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), AddArticleActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        articleModelList = new ArrayList<>();
        articleAdapter = new ArticleAdapter(articleModelList, getContext());
        recyclerView.setAdapter(articleAdapter);

        db = AppDatabase.getInstance(requireContext());
//        reloadData();
        return view;
    }

    private void reloadData() {
        new Thread(() -> {
            articleModelList.clear();
            articleModelList.addAll(db.articleDao().getAllArticles());
            if (articleModelList.isEmpty()) {
                fetchData();
            } else {
                getActivity().runOnUiThread(() -> {
                    articleAdapter.notifyDataSetChanged();

                });
            }
        }).start();
    }

    void fetchData() {
        String url = "https://scarlet-gabie-79.tiiny.io/";

        RequestQueue queue = Volley.newRequestQueue(requireContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    articleModelList.clear();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            ArticleModel article = new ArticleModel(
                                    obj.getInt("id"),
                                    obj.getString("title"),
                                    obj.getString("content")
                            );
                            articleModelList.add(article);
                        }
                        new Thread(() -> {
                            db.articleDao().insertAll(articleModelList);
                            getActivity().runOnUiThread(() -> articleAdapter.notifyDataSetChanged());
                        }).start();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> {
                    Toast.makeText(requireContext(), "Failed to load articles", Toast.LENGTH_SHORT).show();
                }
        );

        queue.add(request);
    }

    @Override
    public void onResume() {
        super.onResume();
        reloadData();
    }
}
