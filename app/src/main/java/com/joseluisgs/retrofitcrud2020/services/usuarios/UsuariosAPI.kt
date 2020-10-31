package com.joseluisgs.retrofitcrud2020.services.usuarios

object UsuariosAPI {
    // Direccion del Servicio, debe acabar en barra /
    private const val API_URL = "https://my-json-server.typicode.com/joseluisgs/APIRESTFake/"

    // Constructor del servicio con los elementos de la interfaz
    val service: UsuariosREST
        get() = UsuariosRetrofit.getClient(API_URL)!!.create(UsuariosREST::class.java)
}