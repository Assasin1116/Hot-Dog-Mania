package com.empresasjw.hot_mania

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recuperar)

        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val sendResetButton: Button = findViewById(R.id.sendResetButton)
        val goToLogin: TextView = findViewById(R.id.goToLogin)

        // Botón para enviar correo de recuperación
        sendResetButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()

            if (email.isEmpty()) {
                Toast.makeText(this, "Ingrese su correo", Toast.LENGTH_SHORT).show()
            } else {
                // Aquí podrías integrar Firebase Auth para recuperar contraseña
                Toast.makeText(this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show()
            }
        }

        // Redirigir al Login
        goToLogin.setOnClickListener {
            val intent = Intent(this, Login_Activity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
