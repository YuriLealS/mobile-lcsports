package sptech.school.lcsports.domain

import sptech.school.lcsports.Postagem
import java.io.Serializable


class Imagem(
    val id: Number,
    val codigoImagem: String,
    val postagem: Postagem
) : Serializable