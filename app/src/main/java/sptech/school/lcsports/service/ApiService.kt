package sptech.school.lcsports.service

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import sptech.school.lcsports.domain.Login
import sptech.school.lcsports.domain.UsuarioLoginDto

interface ApiService {
    @POST("v1/usuario/autenticar")
    fun autenticar(@Body request: Login): Call<UsuarioLoginDto>
}
