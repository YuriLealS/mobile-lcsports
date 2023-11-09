package sptech.school.lcsports.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "http://18.208.9.255:8080/" // Substitua pela URL da sua API
    //private const val BASE_URL = "http://192.168.173.48:8080/" // Substitua pela URL da sua API

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiService::class.java)
    }
}