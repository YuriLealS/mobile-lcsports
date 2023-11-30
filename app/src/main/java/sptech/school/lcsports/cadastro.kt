package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import org.json.JSONObject
import sptech.school.lcsports.databinding.ActivityTelaCadastroBinding
import java.io.IOException

class cadastro : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaCadastroBinding.inflate(layoutInflater)
    }

    val authManager = AuthManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
    private fun realizarCadastro() {
        val nome = binding.etNome.text.toString()
        val email = binding.etEmail.text.toString()
        val cpf = binding.etCpf.text.toString()
        val cep = binding.etCep.text.toString()
        val uf = binding.etUf.text.toString()
        val cidade = binding.etCidade.text.toString()
        val tel = binding.etTel.text.toString()
        val senha = binding.etSenha.text.toString()


        val dadosCadastro = JSONObject()
        dadosCadastro.put("nome", nome)
        dadosCadastro.put("email", email)
        dadosCadastro.put("cpf", cpf)
        dadosCadastro.put("cep", cep)
        dadosCadastro.put("uf", uf)
        dadosCadastro.put("cidade", cidade)
        dadosCadastro.put("telefone", tel)
        dadosCadastro.put("senha", senha)

        val client = OkHttpClient()
        val url = "https://192.168.1.105:8080/v1/usuarios"
        val json = dadosCadastro.toString()
        val body =
            RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val responseStatus = response.code
                val responseBody = response.body?.string()

                runOnUiThread {
                    if (responseStatus == 409) {
                        binding.etNome.error = "Nome já cadastrado"
                    } else if (responseStatus == 409) {
                        binding.etEmail.error = "E-mail já cadastrado"
                    } else if (response.isSuccessful) {
                        if (responseBody != null) {
                            val gson = Gson()
                            val cadastro = gson.fromJson(responseBody, DtoAuth::class.java)
                            val authManager = AuthManager(this@cadastro)
                            authManager.saveAuthToken(cadastro.token.toString())
                            val intent =
                                Intent(this@cadastro, login::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Erro na resposta, tente novamente mais tarde",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

}