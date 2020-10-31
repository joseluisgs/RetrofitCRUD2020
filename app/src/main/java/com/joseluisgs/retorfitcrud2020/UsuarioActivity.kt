package com.joseluisgs.retorfitcrud2020

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.Usuario
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.UsuarioDTO
import com.joseluisgs.retorfitcrud2020.modelos.usuarios.UsuarioMapper
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosAPI
import com.joseluisgs.retorfitcrud2020.services.usuarios.UsuariosREST
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_usuario.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UsuarioActivity : AppCompatActivity() {
    lateinit var modo: String
    lateinit var usuario: Usuario
    lateinit var usuariosREST: UsuariosREST

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usuario)

        initUI()
    }

    /**
     * Inicia la Interfaz
     */
    private fun initUI() {
        if (Utils.isOnline(applicationContext)) {
            // Si lo hay nos conectamos a la Api Rest para probar
            usuariosREST = UsuariosAPI.service
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }
        initModo()
    }

    /**
     * Inicia el Modo
     */
    private fun initModo() {
        modo = intent.getStringExtra("MODO").toString()
        usuarioEditID.isFocusable = false
        usuarioEditID.isEnabled = false;
        Toast.makeText(applicationContext, "Modo de Operación: $modo", Toast.LENGTH_SHORT)
            .show()
        if (modo == "NUEVO") {
            cargarDatosNuevo()
        } else {
            cargarDatosVer()
        }
    }

    /**
     * Carga los datos de nuevo
     */
    private fun cargarDatosNuevo() {
        usuarioBtnEliminar.visibility = View.INVISIBLE
        //usuarioEditID.setText(System.currentTimeMillis().toString())
        // Voy a inevntarme unos datos para no escribir
        usuarioEditNombre.setText("Pepe Perez")
        usuarioEditNick.setText("pepito")
        usuarioEditEmail.setText("pepe@pepe.com")
        Picasso.get()
            .load("https://eu.ui-avatars.com/api/?name=" + usuarioTxtNick.text.toString() + "&background=random")
            .resize(250, 250).into(usuarioIvAvatar)
        // Evento del botón
        usuarioBtnSalvar.setOnClickListener { salvar() }
    }


    /**
     * Carga los datos Ver
     */
    private fun cargarDatosVer() {
        usuario = (intent.getSerializableExtra("VALOR") as Usuario)
        usuarioEditID.setText(usuario.id)
        usuarioEditNombre.setText(usuario.name)
        usuarioEditNick.setText(usuario.nick)
        usuarioEditEmail.setText(usuario.email)
        Picasso.get()
            .load(usuario.avatar)
            .resize(250, 250).into(usuarioIvAvatar)

        // Evento del Botón
        usuarioBtnEliminar.setOnClickListener { eliminar() }
        usuarioBtnSalvar.setOnClickListener { actualizar() }
    }

    /**
     * Salva un usuario
     */
    private fun salvar() {
        if (Utils.isOnline(applicationContext)) {
            if (datosValidados()) {
                val usuario: Usuario = Usuario(
                    id = System.currentTimeMillis().toString(),
                    email = usuarioTxtEmail.text.toString(),
                    name = usuarioTxtNombre.text.toString(),
                    nick = usuarioTxtNick.text.toString(),
                    avatar = "https://eu.ui-avatars.com/api/?name=" + usuarioTxtNick.text.toString() + "&background=random"
                )
                salvarUsuario(UsuarioMapper.toDTO(usuario))
                // volver()
            }
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Salva un usuario
     * @param usuarioDTO UsuarioDTO
     */
    private fun salvarUsuario(usuarioDTO: UsuarioDTO) {
        val call: Call<UsuarioDTO> = usuariosREST.create(usuarioDTO)
        call.enqueue((object : Callback<UsuarioDTO> {
            override fun onResponse(call: Call<UsuarioDTO>, response: Response<UsuarioDTO>) {
                // Si ok
                if (response.isSuccessful) {
                    // Simulamos que lo salvamos de la lista local, pues de la remota se ha hecho, pero como no cambia porque es un Fake API REST
                    // por eso no vemos los cambios. De ahí que haga todo esto
                    // Lo importante es que si llegamos aquí es que lo hemos conseguido, porque el código es correcto
                    Toast.makeText(
                        applicationContext,
                        "Usuario insertado. Código Respuesta: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error al insertar. Código Respuesta : " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //Si error
            override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al eliminar: " + t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }))
    }

    /**
     * Elimina un usuario
     */
    private fun eliminar() {
        if (Utils.isOnline(applicationContext)) {
            eliminarUsuario(usuarioEditID.text.toString())
            // volver()
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Elimina un usuario
     * @param id String
     */
    private fun eliminarUsuario(id: String) {
        val call: Call<UsuarioDTO> = usuariosREST.delete(id)
        call.enqueue((object : Callback<UsuarioDTO> {
            override fun onResponse(call: Call<UsuarioDTO>, response: Response<UsuarioDTO>) {
                // Si ok
                if (response.isSuccessful) {
                    // Simulamos que lo borramos de la lista local, pues de la remota se ha hecho, pero como no cambia porque es un Fake API REST
                    // por eso no vemos los cambios. De ahí que haga todo esto
                    // Lo importante es que si llegamos aquí es que lo hemos conseguido, porque el código es correcto
                    Toast.makeText(
                        applicationContext,
                        "Usuario eliminado. Código Respuesta: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Usuario no encontrado. Código Respuesta : " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            //Si error
            override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al eliminar: " + t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }))
    }


    /**
     * Actualizar
     */
    private fun actualizar() {
        if (Utils.isOnline(applicationContext)) {
            if (datosValidados()) {
                val usuario: Usuario = Usuario(
                    id = usuarioEditID.text.toString(),
                    email = usuarioTxtEmail.text.toString(),
                    name = usuarioTxtNombre.text.toString(),
                    nick = usuarioTxtNick.text.toString(),
                    avatar = "https://eu.ui-avatars.com/api/?name=" + usuarioTxtNick.text.toString() + "&background=random"
                )
                actualizarUsuario(usuarioEditID.text.toString(), UsuarioMapper.toDTO(usuario))
                // volver()
            }
        } else {
            Toast.makeText(this, "Es necesaria una conexión a internet para funcionar", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Actualiza un usuario
     * @param id String
     * @param usuarioDTO UsuarioDTO
     */
    private fun actualizarUsuario(id: String, usuarioDTO: UsuarioDTO) {
        val call: Call<UsuarioDTO> = usuariosREST.update(id, usuarioDTO)
        call.enqueue((object : Callback<UsuarioDTO> {
            override fun onResponse(call: Call<UsuarioDTO>, response: Response<UsuarioDTO>) {
                // Si ok
                if (response.isSuccessful) {
                    // Simulamos que lo actualizamos de la lista local, pues de la remota se ha hecho, pero como no cambia porque es un Fake API REST
                    // por eso no vemos los cambios. De ahí que haga todo esto
                    // Lo importante es que si llegamos aquí es que lo hemos conseguido, porque el código es correcto
                    Toast.makeText(
                        applicationContext,
                        "Usuario actualizado. Código Respuesta: " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Error al actualizar. Código Respuesta : " + response.code(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<UsuarioDTO>, t: Throwable) {
                Toast.makeText(applicationContext, "Error al actualizar: " + t.localizedMessage, Toast.LENGTH_SHORT)
                    .show()
            }
        }))
    }


    private fun volver() {
        // Le pasamos la lista para simular que la hemos modificado en el servidor
        // Esto es porque estoy usando una fake api
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    /**
     * Valida los datos
     * @return Boolean
     */
    private fun datosValidados(): Boolean {
        return (usuarioEditNombre.text.isNotBlank() && usuarioEditNick.text.isNotBlank() &&
                usuarioEditEmail.text.isNotBlank())
    }
}