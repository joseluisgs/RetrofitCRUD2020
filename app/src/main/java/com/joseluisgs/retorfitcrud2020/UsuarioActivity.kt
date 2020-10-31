package com.joseluisgs.retorfitcrud2020

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.Usuario
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosAPI
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosREST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_usuario.*

class UsuarioActivity : AppCompatActivity() {
    lateinit var modo: String
    lateinit var usuario: Usuario
    lateinit var usuariosREST: UsuariosREST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        initUI()
    }

    private fun initUI() {
        if (Utils.isOnline(applicationContext)) {
            // Si lo hay nos conectamos a la Api Rest para probar
            usuariosREST = UsuariosAPI.service
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }
        initModo()
    }

    private fun initModo() {
        modo = intent.getStringExtra("MODO").toString()
        Toast.makeText(applicationContext, "Modo de Operación: $modo", Toast.LENGTH_SHORT)
            .show()
        if (modo == "NUEVO") {
            cargarDatosNuevo()
        } else {
            cargarDatosVer()
        }
    }

    private fun cargarDatosNuevo() {
        usuarioBtnEliminar.visibility = View.INVISIBLE
        usuarioEditID.isFocusable = false
        usuarioEditID.isEnabled = false;
        //usuarioEditID.setText(System.currentTimeMillis().toString())
        Picasso.get()
            .load("https://eu.ui-avatars.com/api/?background=random")
            .resize(250, 250).into(usuarioIvAvatar)
    }

    private fun cargarDatosVer() {
        usuario = (intent.getSerializableExtra("VALOR") as Usuario)
        usuarioEditID.setText(usuario.id)
        usuarioEditNombre.setText(usuario.name)
        usuarioEditNick.setText(usuario.nick)
        usuarioEditEmail.setText(usuario.email)
        Picasso.get()
            .load(usuario.avatar)
            .resize(250, 250).into(usuarioIvAvatar)
    }
}