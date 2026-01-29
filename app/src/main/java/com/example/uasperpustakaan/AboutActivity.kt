package com.example.uasperpustakaan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.uasperpustakaan.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    // Inisialisasi binding agar bisa memanggil ID di layout activity_about.xml
    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Memasang layout menggunakan binding
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fungsi tombol kembali untuk menutup halaman ini dan balik ke Menu Utama
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}