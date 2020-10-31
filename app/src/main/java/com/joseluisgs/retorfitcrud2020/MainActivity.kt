package com.joseluisgs.retorfitcrud2020

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.Usuario
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.UsuarioDTO
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.UsuarioMapper
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosAPI
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosREST
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    // Para manejar los elementos de la API REST
    lateinit var usuariosREST: UsuariosREST
    var CONECTADO = false
    lateinit var usuariosList: List<Usuario>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        // Comprobamos la conexión a internet
        if (Utils.isOnline(applicationContext)) {
            Toast.makeText(this, "Sí estás conectado a Internet", Toast.LENGTH_SHORT).show()
            // Si lo hay nos conectamos a la Api Rest para probar
            usuariosREST = UsuariosAPI.service
            CONECTADO = true
            listarProductos()

        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }

        // Barra de progreso
        mainProgressBar.visibility = View.INVISIBLE

        // Probamos un botón para abri la actividad
        mainBtnAñadir.setOnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)
            // Comenzamos la actividad
            startActivity(intent)
        }
    }

    private fun listarProductos() {
        mainProgressBar.visibility = View.VISIBLE
        // Creamos la tarea que llamará al servicio rest y la encolamos
        val call: Call<List<UsuarioDTO>> = usuariosREST.findAll()
        // Creamos una cola asincrona
        call.enqueue((object : Callback<List<UsuarioDTO>> {
            // Si fallamos
            override fun onFailure(call: Call<List<UsuarioDTO>>, t: Throwable) {
                Log.e("ERROR: ", t.localizedMessage!!)
            }

            // Si tenemos exito
            override fun onResponse(call: Call<List<UsuarioDTO>>, response: Response<List<UsuarioDTO>>) {
                if (response.isSuccessful) {
                    // Hacemos el parser de JSON a nuestro Modelo
                    usuariosList = UsuarioMapper.DTOToModel(response.body() as List<UsuarioDTO>)
                    Toast.makeText(applicationContext, "Datos Obtenidos: " + usuariosList.size, Toast.LENGTH_SHORT)
                        .show()
                    usuariosList.forEach {
                        Log.e("REST", it.toString())
                    }
                }
            }

        }))
        mainProgressBar.visibility = View.INVISIBLE
    }


}