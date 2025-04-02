package com.empresasjw.hot_mania

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Wrlcome_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_wrlcome)

        // Find the button by its ID
        val iniciarSesionButton: Button = findViewById(R.id.iniciarSesionButton)

        // Set an OnClickListener on the button
        iniciarSesionButton.setOnClickListener {
            // Create an Intent to start the LoginActivity
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent) // Start the activity
        }

        // Set up edge-to-edge behavior (existing code)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun goRegistration_Activity(view: View?) {

        val intent = Intent(
            this,
            Registration_Activity::class.java
        )
        startActivity(intent)
    }
}