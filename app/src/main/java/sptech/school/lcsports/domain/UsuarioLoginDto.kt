package sptech.school.lcsports.domain

data class UsuarioLoginDto(
    val nome:String? = null,
    val email:String? = null,
    val senha:String? = null,
    val telefone:String? = null,
    val cep:String? = null,
    val cidade:String? = null,
    val uf:String? = null,
    val isPremium:Boolean = false
)
