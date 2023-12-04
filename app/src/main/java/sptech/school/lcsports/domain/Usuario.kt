package sptech.school.lcsports.domain

import java.io.Serializable

class Usuario(

    val nome: String,
    val email: String,
    val telefone: String,
    val fotoUsuario: String,
    val biografia: String

) : Serializable