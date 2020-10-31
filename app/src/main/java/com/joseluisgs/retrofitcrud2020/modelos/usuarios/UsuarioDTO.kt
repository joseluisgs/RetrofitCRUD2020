package com.joseluisgs.retrofitcrud2020.modelos.usuarios

import com.google.gson.annotations.SerializedName

/**
 * Esta clase nos sirve para parsear el JSON cogeriamos los datos  que queremos
 * @property id String
 * @property email String
 * @property name String
 * @property nick String
 * @property avatar String
 * @constructor
 */
class UsuarioDTO(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("username") val nick: String,
    @SerializedName("avatar") val avatar: String,
)

