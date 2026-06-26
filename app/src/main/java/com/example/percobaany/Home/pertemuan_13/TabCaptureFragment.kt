package com.example.percobaany.Home.pertemuan_13

import android.Manifest
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.percobaany.utils.PermissionHelper

class TabCaptureFragment : Fragment() {

    // 1. Daftarkan permission launcher untuk kamera di paling atas kelas fragment
    private val permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Izin kamera diberikan", Toast.LENGTH_SHORT).show()
                openCamera()
            } else {
                Toast.makeText(requireContext(), "Izin kamera ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // 2. Menggunakan LinearLayout (Vertikal) agar teks dan tombol bisa disusun ke bawah
        val rootLayout = LinearLayout(requireContext()).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
        }

        // 3. Membuat Tombol Capture langsung lewat kode Kotlin (Pengganti binding.btnCapture)
        val btnCapture = Button(requireContext()).apply {
            text = "Capture Image"

            // --- MASUKKAN KODE KAMU DI SINI ---
            setOnClickListener {
                if (!PermissionHelper.hasPermission(
                        requireActivity(),
                        Manifest.permission.CAMERA
                    )
                ) {
                    PermissionHelper.requestPermission(
                        permissionLauncher,
                        Manifest.permission.CAMERA
                    )
                } else {
                    openCamera()
                }
            }
            // ----------------------------------
        }

        // 4. Masukkan tombol ke dalam layout utama
        rootLayout.addView(btnCapture)

        return rootLayout
    }

    // 5. Buat fungsi openCamera() agar kode kamu tidak error / merah
    private val openCameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            Toast.makeText(requireContext(), "Foto berhasil diambil!", Toast.LENGTH_SHORT).show()
            // Di sini kamu bisa memproses file gambar (bitmap) jika diperlukan
        }
    }

    private fun openCamera() {
        Log.e("Camera", "Membuka kamera...")
        try {
            openCameraLauncher.launch(null)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Gagal membuka kamera: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}