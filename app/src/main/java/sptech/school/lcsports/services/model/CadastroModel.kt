package sptech.school.lcsports.services.model

data class CadastroModel (
    val nome : String,
    val email: String,
    val cpf: String,
    val cep: String,
    val uf: String,
    val cidade: String,
    val telefone: String,
    val senha: String,
)