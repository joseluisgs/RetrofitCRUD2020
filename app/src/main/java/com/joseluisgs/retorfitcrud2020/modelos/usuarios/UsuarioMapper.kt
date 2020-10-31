package com.joseluisgs.retorfitcrud2020.modelos.usuarios

/**
 * Mapea entre DTO y Clase Modelo
 */
class UsuarioMapper {
    fun dtoToModel(items: List<UsuarioDTO>): List<Usuario> {
        return items.map { transformDto(it) }
    }

    fun modelToDTO(items: List<Usuario>): List<UsuarioDTO> {
        return items.map { transToDto(it) }
    }


    private fun transformDto(dto: UsuarioDTO): Usuario {
        return Usuario(
            dto.id,
            dto.email,
            dto.firstName,
            dto.lastName,
            dto.avatar
        )
    }

    private fun transToDto(model: Usuario): UsuarioDTO {
        return UsuarioDTO(
            model.id!!,
            model.email!!,
            model.firstName!!,
            model.lastName!!,
            model.avatar!!
        )
    }
}


