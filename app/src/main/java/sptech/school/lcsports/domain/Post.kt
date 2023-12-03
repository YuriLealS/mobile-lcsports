package sptech.school.lcsports.domain

import java.io.Serializable


class Post(
    val id: Number,
    val titulo: String,
    val descricao: String,
    val usuario: Usuario,
    val imagens: ArrayList<Imagem>
) : Serializable