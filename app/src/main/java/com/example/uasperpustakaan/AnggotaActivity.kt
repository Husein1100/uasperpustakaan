package com.example.uasperpustakaan

import android.content.ContentValues
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityAnggotaBinding

class AnggotaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnggotaBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnggotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dbHelper = DatabaseHelper(this)
        displayData()

        binding.btnSaveAnggota.setOnClickListener {
            val db = dbHelper.writableDatabase
            val v = ContentValues().apply {
                put("nama", binding.etNamaAnggota.text.toString())
                put("tgl_daftar", binding.etTglDaftar.text.toString())
                put("alamat", binding.etAlamat.text.toString())
                put("tgl_lahir", binding.etTglLahir.text.toString())
            }
            db.insert("anggota", null, v)
            Toast.makeText(this, "Anggota Tersimpan", Toast.LENGTH_SHORT).show()
            displayData()
            clearFields()
        }

        binding.btnDeleteAnggota.setOnClickListener {
            val db = dbHelper.writableDatabase
            // Menghapus berdasarkan ID yang diinput manual atau terpilih
            val id = binding.etIdAnggota.text.toString()
            if (id.isNotEmpty()) {
                db.delete("anggota", "id=?", arrayOf(id))
                displayData()
                clearFields()
                Toast.makeText(this, "Data Dihapus", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayData() {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM anggota", null)
        var dataText = ""
        while (cursor.moveToNext()) {
            dataText += "ID: ${cursor.getInt(0)} | ${cursor.getString(1)}\n"
        }
        binding.tvDataAnggota.text = if (dataText.isEmpty()) "Belum ada data" else dataText
        cursor.close()
    }

    private fun clearFields() {
        binding.etIdAnggota.text.clear()
        binding.etNamaAnggota.text.clear()
        binding.etTglDaftar.text.clear()
        binding.etAlamat.text.clear()
        binding.etTglLahir.text.clear()
    }
}