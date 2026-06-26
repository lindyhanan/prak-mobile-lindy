package com.example.orangcantkikapps.Home.pertemuan2

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.orangcantkikapps.Home.pertemuan_3.ThirdResultActivity
import com.example.orangcantkikapps.R
import com.example.orangcantkikapps.databinding.ActivitySecondBinding
import com.example.orangcantkikapps.databinding.ActivityThirdBinding
import com.example.orangcantkikapps.utils.NotificationHelper
import com.example.orangcantkikapps.utils.PermissionHelper

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        // Inisialisasi komponen
        val inputNama: EditText = findViewById(R.id.inputNama)
        val btnSubmit: Button = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            //Mengambil value dari inputNama dan menampilkan di Logcat
            val nama = inputNama.text
            Log.e("Klik btnSubmit", "Tombol berhasil di tekan. Isi dari inputNama = $nama")

            Toast.makeText(this, "inAnda telah melakukan klik pada tombol Submit" , Toast.LENGTH_SHORT)
                .show()
            val intent = Intent(this, ThirdResultActivity::class.java)
            NotificationHelper.showNotification(
                this, //Jika panggil di fragment maka requireContext()
                "Pesanan Anda",
                "Halo $nama, Pesanan Anda Sedang Diproses",
                intent
            )
        }
    }
}