package com.example.percobaany

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.percobaany.Pertemuan_5.FifthActivity
import com.example.percobaany.pertemuan_2.SecondActivity
import com.example.percobaany.databinding.ActivityMainBinding
import com.example.percobaany.pertemuan_3.ThirdActivity
import com.example.percobaany.pertemuan_4.FourthActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // MENU NAVIGASI
        binding.btnKedua.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }

        binding.btnKetiga.setOnClickListener {
            startActivity(Intent(this, ThirdActivity::class.java))
        }

        binding.btnKeempat.setOnClickListener {
            val intent = Intent(this, FourthActivity::class.java)
            intent.putExtra("nama", "Politeknik Caltex Riau")
            intent.putExtra("asal", "Rumbai")
            intent.putExtra("usia", 25)
            startActivity(intent)
        }

        binding.btnKelima.setOnClickListener {
            startActivity(Intent(this, FifthActivity::class.java))
        }

        // =========================
        // LOGOUT FIX MODUL
        // =========================
        binding.btnLogout.setOnClickListener {

            AlertDialog.Builder(this)
                .setTitle("Logout")
                .setMessage("Apakah Anda yakin ingin logout?")
                .setPositiveButton("Ya") { _, _ ->

                    val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)
                    sharedPref.edit().clear().apply()

                    startActivity(Intent(this, AuthActivity::class.java))
                    finish()
                }
                .setNegativeButton("Batal", null)
                .show()
        }
    }
}