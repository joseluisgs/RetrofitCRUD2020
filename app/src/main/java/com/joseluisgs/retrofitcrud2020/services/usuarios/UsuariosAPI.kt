package com.joseluisgs.retrofitcrud2020.services.usuarios

object UsuariosAPI {
    // Direccion del Servicio, debe acabar en barra /
    // private const val API_URL = "https://my-json-server.typicode.com/joseluisgs/APIRESTFake/"

    // Si estamos con un servidor propio en el ordenador, desde el emulador la ip es o conodemos su ip
    private const val server: String = "10.0.2.2"

    // Puerto del microservicio
    private const val port = "6969"
    private const val API_URL = "http://$server:$port/"

    // Constructor del servicio con los elementos de la interfaz
    val service: UsuariosREST
        get() = UsuariosRetrofit.getClient(API_URL)!!.create(UsuariosREST::class.java)
}