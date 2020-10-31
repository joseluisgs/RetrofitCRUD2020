package com.joseluisgs.retorfitcrud2020.modelos.usuarios

/**
 * Mapea entre DTO y Clase Modelo
 */
object UsuarioMapper {
    /**
     * Una lista de DTO a Modelos
     * @param items List<UsuarioDTO>
     * @return List<Usuario>
     */
    fun fromDTO(items: List<UsuarioDTO>): List<Usuario> {
        return items.map { fromDTO(it) }
    }

    /**
     * Una lista de Modelos a DTO
     * @param items List<Usuario>
     * @return List<UsuarioDTO>
     */
    fun toDTO(items: List<Usuario>): List<UsuarioDTO> {
        return items.map { toDTO(it) }
    }

    /**
     * DTO a Modelo
     * @param dto UsuarioDTO
     * @return Usuario
     */
    fun fromDTO(dto: UsuarioDTO): Usuario {
        return Usuario(
            dto.id,
            dto.email,
            dto.name,
            dto.nick,
            dto.avatar
        )
    }

    /**
     * Modelo a DTO
     * @param model Usuario
     * @return UsuarioDTO
     */
    fun toDTO(model: Usuario): UsuarioDTO {
        return UsuarioDTO(
            model.id!!,
            model.email!!,
            model.name!!,
            model.nick!!,
            model.avatar!!
        )
    }
}


