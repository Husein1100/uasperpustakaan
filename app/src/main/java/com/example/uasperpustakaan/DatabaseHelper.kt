package com.example.uasperpustakaan

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "Perpus.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE buku (id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, pengarang TEXT, penerbit TEXT, tahun TEXT)")
        db.execSQL("CREATE TABLE anggota (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, tgl_daftar TEXT, alamat TEXT, tgl_lahir TEXT)")
        db.execSQL("CREATE TABLE transaksi (id INTEGER PRIMARY KEY AUTOINCREMENT, nama_anggota TEXT, judul_buku TEXT, tgl_pinjam TEXT, tgl_kembali TEXT, status TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS buku")
        db.execSQL("DROP TABLE IF EXISTS anggota")
        db.execSQL("DROP TABLE IF EXISTS transaksi")
        onCreate(db)
    }
}