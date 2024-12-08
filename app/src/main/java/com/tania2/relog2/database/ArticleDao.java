package com.tania2.relog2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tania2.relog2.model.ArticleModel;

import java.util.List;

@Dao
public interface ArticleDao {
    @Insert
    void insertAll(List<ArticleModel> articles);

    @Insert
    void insert(ArticleModel data);

    @Query("SELECT * FROM articles")
    List<ArticleModel> getAllArticles();
}