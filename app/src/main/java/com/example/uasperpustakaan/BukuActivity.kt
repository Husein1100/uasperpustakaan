package com.example.uasperpustakaan

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityBukuBinding

class BukuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBukuBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBukuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)
        displayData()

        binding.btnSave.setOnClickListener {
            val db = dbHelper.writableDatabase
            val v = ContentValues().apply {
                put("judul", binding.etJudul.text.toString())
                put("pengarang", binding.etPengarang.text.toString())
                put("penerbit", binding.etPenerbit.text.toString())
                put("tahun", binding.etTahun.text.toString())
            }
            db.insert("buku", null, v)
            Toast.makeText(this, "Data Disimpan", Toast.LENGTH_SHORT).show()
            displayData()
            clearFields()
        }

        binding.btnUpdate.setOnClickListener {
            val db = dbHelper.writableDatabase
            val v = ContentValues().apply {
                put("judul", binding.etJudul.text.toString())
                put("pengarang", binding.etPengarang.text.toString())
                put("penerbit", binding.etPenerbit.text.toString())
                put("tahun", binding.etTahun.text.toString())
            }
            db.update("buku", v, "id=?", arrayOf(binding.etIdBuku.text.toString()))
            displayData()
            clearFields()
        }

        binding.btnDelete.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("buku", "id=?", arrayOf(binding.etIdBuku.text.toString()))
            displayData()
            clearFields()
        }
    }

    private fun displayData() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM buku", null)
        var dataText = ""
        while (cursor.moveToNext()) {
            dataText += "ID: ${cursor.getInt(0)} | ${cursor.getString(1)}\n"
        }
        binding.tvDataBuku.text = dataText
        cursor.close()
    }

    private fun clearFields() {
        binding.etIdBuku.text.clear()
        binding.etJudul.text.clear()
        binding.etPengarang.text.clear()
        binding.etPenerbit.text.clear()
        binding.etTahun.text.clear()
    }
}