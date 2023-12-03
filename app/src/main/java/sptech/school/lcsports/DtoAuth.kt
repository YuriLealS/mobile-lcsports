package sptech.school.lcsports

import java.io.Serializable


data class DtoAuth(
var id: Int? = null,
var email: String? = null,
var token: Token? = null,
) : Serializable
