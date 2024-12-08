package com.tania2.relog2.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity (tableName = "catatan")
public class Catatan implements Serializable {

    @PrimaryKey (autoGenerate = true)
    public int id;
    @ColumnInfo(name = "title")
    public String title;
    @ColumnInfo(name = "konten")
    public String konten;
    @ColumnInfo(name = "tanggal")
    public String tanggal;
    @ColumnInfo (name = "waktu")
    public String waktu;


    // Konstruktor tanpa argumen untuk Room
    public Catatan() {}

    public Catatan(String title, String konten, String tanggal) {
        this.title = title;
        this.konten = konten;
        this.tanggal = tanggal;

        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        this.waktu = timeFormat.format(new Date());
    }
    public Catatan(int id,String title, String konten, String tanggal, String waktu) {
        this.id = id;
        this.title = title;
        this.konten = konten;
        this.tanggal = tanggal;
        this.waktu = waktu;
    }

    // Constructor
    public Catatan(String title, String konten) {
        this.title = title;
        this.konten = konten;

        // Tanggal dan jam otomatis dari sistem
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        this.tanggal = dateFormat.format(new Date());
        this.waktu = timeFormat.format(new Date());
    }

    // Getter dan Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String Title) {
        this.title = title;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public String getDate() {
        return tanggal;
    }
    public String getTime() {
        return waktu;
    }
}


