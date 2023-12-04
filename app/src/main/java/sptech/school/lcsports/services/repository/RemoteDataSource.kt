package sptech.school.lcsports.services.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sptech.school.lcsports.DtoAuth
import sptech.school.lcsports.services.model.CadastroModel
import sptech.school.lcsports.services.model.LoginModel

class RemoteDataSource {

    private val retrofitClient = RetrofitClient.createService(LcsportsService::class.java)

    fun login(
        body: LoginModel,
        onSuccess: (DtoAuth) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call: Call<DtoAuth> = retrofitClient.login(body)
        call.enqueue(object : Callback<DtoAuth> {
            override fun onResponse(call: Call<DtoAuth>, response: Response<DtoAuth>) {
                if (response.isSuccessful) {
                    response.body()?.let { retorno ->
                        onSuccess.invoke(retorno)

                    }
                } else {
                    onFailure.invoke(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DtoAuth>, throwable: Throwable) {
                throwable.message?.let { stringMessage ->
                    onFailure.invoke(stringMessage)
                }
            }

        })
    }

    fun cadastro(
        body: CadastroModel,
        onSuccess: (DtoAuth) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val call: Call<DtoAuth> = retrofitClient.cadastrar(body)
        call.enqueue(object : Callback<DtoAuth> {
            override fun onResponse(call: Call<DtoAuth>, response: Response<DtoAuth>) {
                if (response.isSuccessful) {
                    response.body()?.let { retorno ->
                        onSuccess.invoke(retorno)

                    }
                } else {
                    onFailure.invoke(response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<DtoAuth>, throwable: Throwable) {
                throwable.message?.let { stringMessage ->
                    onFailure.invoke(stringMessage)
                }
            }

        })
    }
}