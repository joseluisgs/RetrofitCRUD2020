package com.joseluisgs.retorfitcrud2020.services.usuarios

import com.joseluisgs.retorfitcrud2020.modelos.usuarios.UsuarioDTO
import retrofit2.Call
import retrofit2.http.*


interface UsuariosREST {
    // Obtener todos
    // https://my-json-server.typicode.com/joseluisgs/APIRESTFake/users
    @GET("users/")
    fun findAll(): Call<List<UsuarioDTO>>

    // Obtener por ID
    // GET: https://my-json-server.typicode.com/joseluisgs/APIRESTFake/users/{id}
    @GET("users/{id}")
    fun findById(@Path("id") id: Long?): Call<UsuarioDTO>

    // Crear un item
    //POST: https://my-json-server.typicode.com/joseluisgs/APIRESTFake/users/
    @POST("users/")
    fun create(@Body user: UsuarioDTO): Call<UsuarioDTO?>?

    // Elimina un item
    // DELETE: https://my-json-server.typicode.com/joseluisgs/APIRESTFake/users/{id}
    @DELETE("users/{id}")
    fun delete(@Path("id") id: Long?): Call<UsuarioDTO>

    // Actualiza un producto
    // PUT: https://my-json-server.typicode.com/joseluisgs/APIRESTFake/users/{id}
    @PUT("users/{id}")
    fun update(@Path("id") id: Long?, @Body producto: UsuarioDTO?): Call<UsuarioDTO>
}