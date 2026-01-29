package com.example.uasperpustakaan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuku.setOnClickListener {
            startActivity(Intent(this, BukuActivity::class.java))
        }

        binding.btnAnggota.setOnClickListener {
            startActivity(Intent(this, AnggotaActivity::class.java))
        }

        binding.btnPinjam.setOnClickListener {
            startActivity(Intent(this, TransaksiActivity::class.java))
        }

        binding.btnRiwayat.setOnClickListener {
            startActivity(Intent(this, RiwayatActivity::class.java))
        }

        binding.btnTentang.setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() // Agar tidak bisa kembali ke menu utama setelah logout
        }
    }
}