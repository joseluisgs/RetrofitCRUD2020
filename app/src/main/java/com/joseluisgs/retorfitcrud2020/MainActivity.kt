package com.joseluisgs.retorfitcrud2020

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        // Comprobamos la conexión a internet
        if (Utils.isOnline(applicationContext)) {
            Toast.makeText(this, "Sí estás conectado a Internet", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show();
        }
    }


}