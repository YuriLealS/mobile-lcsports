package sptech.school.lcsports.services.repository

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {

    companion object {

        private const val BASE_URL = "https://lcsports.azurewebsites.net/v1/"

        private fun httpBuilder(): OkHttpClient.Builder {
            return OkHttpClient.Builder().addInterceptor(APIKeyInterceptor())
        }

        private lateinit var retrofit: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = httpBuilder()

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit

        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}

class APIKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val url = chain.request().url
            .newBuilder().build()

        val request = chain.request().newBuilder().url(url).build()

        return chain.proceed(request)
    }
}