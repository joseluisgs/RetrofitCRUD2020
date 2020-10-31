package com.joseluisgs.retorfitcrud2020.modelos.usuarios

import java.io.Serializable

/**
 * Clase Modelo de Usuario
 * @property id String?
 * @property email String?
 * @property name String?
 * @property  nick String?
 * @property avatar String?
 * @constructor
 */
data class Usuario(
    val id: String?,
    val email: String?,
    val name: String?,
    val nick: String?,
    val avatar: String?,
) : Serializable