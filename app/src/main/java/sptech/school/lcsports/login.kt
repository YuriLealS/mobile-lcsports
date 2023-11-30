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
import sptech.school.lcsports.databinding.ActivityTelaLoginBinding
import java.io.IOException

class login : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val authManager = AuthManager(this)

        binding.btLogar.setOnClickListener {
            if (binding.etEmail.text.toString().isEmpty()) {
                binding.etEmail.error = "Preencha todos os campos"
            } else if (binding.etSenha.text.toString().isEmpty()) {
                binding.etSenha.error = "Preencha todos os campos"
            } else if (binding.etSenha.text.toString().length < 6) {
                binding.etSenha.error = "Senha inválida"
            } else if (!binding.etEmail.text.toString().contains("@")) {
                binding.etEmail.error = "Email inválido"
            } else {
                val email = binding.etEmail.text.toString()
                val senha = binding.etSenha.text.toString()

                val dadosLogin = JSONObject()
                dadosLogin.put("email", email)
                dadosLogin.put("senha", senha)

                val client = OkHttpClient()
                val url = "https://192.168.1.105:8080/v1/usuarios/autenticar"
                val json = dadosLogin.toString()
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
                            if (responseStatus == 404) {
                                binding.etEmail.error = "Email não cadastrado"
                            } else if (responseStatus == 401) {
                                binding.etSenha.error = "Senha incorreta"
                            } else if (response.isSuccessful) {
                                if (responseBody != null) {
                                    val gson = Gson()
                                    val login = gson.fromJson(responseBody, DtoAuth::class.java)
                                    authManager.saveAuthToken(login.token.toString())
                                    val intent = Intent(this@login, feed::class.java)
                                    startActivity(intent)
                                }
                            } else {
                                Toast.makeText(
                                    baseContext,
                                    "Erro na resposta, tente novamente mais tarde",
                                    Toast.LENGTH_SHORT
                                ).show()
                                println(dadosLogin)
                            }
                        }
                    }
                })
            }
        }

        binding.txtCadastro.setOnClickListener {
            Toast.makeText(baseContext, "Cadastre-se", Toast.LENGTH_SHORT).show()
            val telaCadastro = Intent(this, cadastro::class.java)
            startActivity(telaCadastro)
        }
    }
                }
