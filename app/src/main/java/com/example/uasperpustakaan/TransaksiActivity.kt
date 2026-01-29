package com.example.uasperpustakaan

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityTransaksiBinding

class TransaksiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransaksiBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransaksiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)
        displayData()

        binding.btnSaveTrans.setOnClickListener {
            val db = dbHelper.writableDatabase
            val v = ContentValues().apply {
                put("nama_anggota", binding.etNamaPeminjam.text.toString())
                put("judul_buku", binding.etBukuPinjam.text.toString())
                put("tgl_pinjam", binding.etTglPinjam.text.toString())
                put("tgl_kembali", binding.etTglKembali.text.toString())
                put("status", binding.etStatus.text.toString())
            }
            db.insert("transaksi", null, v)
            displayData()
            Toast.makeText(this, "Transaksi Berhasil", Toast.LENGTH_SHORT).show()
        }

        binding.btnDeleteTrans.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("transaksi", "id=?", arrayOf(binding.etIdTrans.text.toString()))
            displayData()
        }
    }

    private fun displayData() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM transaksi", null)
        var dataText = ""
        while (cursor.moveToNext()) {
            dataText += "ID: ${cursor.getInt(0)} | ${cursor.getString(1)} | ${cursor.getString(2)} | Status: ${cursor.getString(5)}\n"
        }
        binding.tvDataTrans.text = dataText
        cursor.close()
    }
}