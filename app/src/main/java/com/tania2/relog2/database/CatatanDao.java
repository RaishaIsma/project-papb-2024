package com.tania2.relog2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tania2.relog2.model.Catatan;

import java.util.List;

@Dao
public interface CatatanDao {

    @Insert
    void insert(Catatan catatan);

    @Query("SELECT * FROM catatan")
    List<Catatan> getAll();

    @Query("SELECT * FROM catatan WHERE tanggal = :date")
    List<Catatan> getCatatanByDate(String date);

    @Delete
    void delete(Catatan catatan);

    @Update
    void update(Catatan catatan);
}

