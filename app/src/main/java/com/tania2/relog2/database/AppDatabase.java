package com.tania2.relog2.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tania2.relog2.model.ArticleModel;
import com.tania2.relog2.model.Catatan;

@Database(entities = {Catatan.class,ArticleModel.class}, version = 3,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CatatanDao catatanDao();

    public abstract ArticleDao articleDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "application_database"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
