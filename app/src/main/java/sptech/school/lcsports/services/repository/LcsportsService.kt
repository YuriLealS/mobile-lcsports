package sptech.school.lcsports.services.repository

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import sptech.school.lcsports.DtoAuth
import sptech.school.lcsports.domain.Usuario
import sptech.school.lcsports.services.model.CadastroModel
import sptech.school.lcsports.services.model.LoginModel

interface LcsportsService {
    @POST("usuarios/autenticar")
    fun login(@Body body: LoginModel): Call<DtoAuth>

    @POST("usuarios")
    fun cadastrar(@Body body:CadastroModel): Call<DtoAuth>

    @GET("usuarios/{id}")
    fun buscarUsuarioPeloId(@Path("id") id: Int): Call<Usuario>
}