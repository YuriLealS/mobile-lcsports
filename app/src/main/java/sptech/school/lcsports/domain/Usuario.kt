package sptech.school.lcsports.domain

import java.io.Serializable

class Usuario(
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val cep: String,
    val cpf: String,
    val cidade: String,
    val uf: String,
    val isPremium: Boolean,
    val postagens: MutableList<Post>,
    val fotoUsuario: String,
    val biografia: String
) : Serializable