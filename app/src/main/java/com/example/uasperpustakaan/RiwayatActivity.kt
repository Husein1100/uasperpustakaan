package com.example.uasperpustakaan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityRiwayatBinding

class RiwayatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRiwayatBinding
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRiwayatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)

        muatSemuaRiwayat()

        binding.btnBackRiwayat.setOnClickListener {
            finish()
        }
    }

    private fun muatSemuaRiwayat() {
        val db = dbHelper.readableDatabase

        // 1. Ambil Data Buku
        val curBuku = db.rawQuery("SELECT * FROM buku", null)
        var txtBuku = ""
        while (curBuku.moveToNext()) {
            txtBuku += "ID: ${curBuku.getInt(0)} | ${curBuku.getString(1)} (${curBuku.getString(4)})\n"
        }
        binding.tvRiwayatBuku.text = if (txtBuku.isEmpty()) "Tidak ada data buku" else txtBuku
        curBuku.close()

        // 2. Ambil Data Anggota
        val curAnggota = db.rawQuery("SELECT * FROM anggota", null)
        var txtAnggota = ""
        while (curAnggota.moveToNext()) {
            txtAnggota += "ID: ${curAnggota.getInt(0)} | ${curAnggota.getString(1)}\n"
        }
        binding.tvRiwayatAnggota.text = if (txtAnggota.isEmpty()) "Tidak ada data anggota" else txtAnggota
        curAnggota.close()

        // 3. Ambil Data Transaksi
        val curTrans = db.rawQuery("SELECT * FROM transaksi", null)
        var txtTrans = ""
        while (curTrans.moveToNext()) {
            txtTrans += "ID: ${curTrans.getInt(0)} | Peminjam: ${curTrans.getString(1)} | Buku: ${curTrans.getString(2)} | Status: ${curTrans.getString(5)}\n"
        }
        binding.tvRiwayatTrans.text = if (txtTrans.isEmpty()) "Tidak ada riwayat transaksi" else txtTrans
        curTrans.close()
    }
}