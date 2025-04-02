package com.empresasjw.hot_mania

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Productos_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        val nombreUsuario = intent.getStringExtra("nombre")
        val userTextView = findViewById<TextView>(R.id.titleText)

        userTextView.text = "Bienvenido, $nombreUsuario"
    }
}
