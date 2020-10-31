package com.joseluisgs.retorfitcrud2020.modelos.usuarios

import com.google.gson.annotations.SerializedName

/**
 * Esta clase nos sirve para parsear el JSON
 * @property id String
 * @property email String
 * @property firstName String
 * @property lastName String
 * @property avatar String
 * @constructor
 */
class UsuarioDTO(
    @SerializedName("id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    @SerializedName("avatar") val avatar: String,
)

