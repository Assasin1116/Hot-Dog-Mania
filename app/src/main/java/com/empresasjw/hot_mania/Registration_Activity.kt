package com.empresasjw.hot_mania

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject

class Registration_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        // Aplicar márgenes solo en la parte superior e inferior
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.registerCard)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(0, systemBars.top, 0, systemBars.bottom)
            insets
        }

        // Redirigir a Login_Activity
        val goToLogin: TextView = findViewById(R.id.goToLogin)
        goToLogin.setOnClickListener {
            startActivity(Intent(this, Login_Activity::class.java))
        }

        // Capturar botón de registro
        val registerButton = findViewById<Button>(R.id.registerButton)
        registerButton.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.nameEditText).text.toString().trim()
            val email = findViewById<EditText>(R.id.emailEditText).text.toString().trim()
            val contrasena = findViewById<EditText>(R.id.passwordEditText).text.toString().trim()

            if (nombre.isEmpty() || email.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                registrarUsuario(nombre, email, contrasena)
            }
        }
    }

    private fun registrarUsuario(nombre: String, email: String, contrasena: String) {
        val url = "http://localhost/hot_mania/register.php"

        val stringRequest = object : StringRequest(Request.Method.POST, url,
            { response ->
                try {
                    val jsonResponse = JSONObject(response)
                    if (jsonResponse.has("success")) {
                        Toast.makeText(this, jsonResponse.getString("success"), Toast.LENGTH_LONG).show()
                        finish() // Cerrar registro y volver a login
                    } else if (jsonResponse.has("error")) {
                        Toast.makeText(this, jsonResponse.getString("error"), Toast.LENGTH_LONG).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al procesar respuesta", Toast.LENGTH_SHORT).show()
                }
            },
            { error ->
                Toast.makeText(this, "Error de conexión: ${error.message}", Toast.LENGTH_LONG).show()
            }) {

            override fun getParams(): MutableMap<String, String> {
                return hashMapOf("nombre" to nombre, "email" to email, "contrasena" to contrasena)
            }
        }

        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }
}
