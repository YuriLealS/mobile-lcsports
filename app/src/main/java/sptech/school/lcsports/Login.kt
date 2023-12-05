package sptech.school.lcsports

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sptech.school.lcsports.databinding.ActivityLoginBinding
import sptech.school.lcsports.services.model.LoginModel
import sptech.school.lcsports.viewmodel.LcsportsViewModel

class Login : AppCompatActivity() {
    private lateinit var viewModel: LcsportsViewModel
    val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater)
    }

    @SuppressLint("SuspiciousIndentation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val authManager = AuthManager(this)

        binding.txtCadastro.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }

        binding.btLogar.setOnClickListener {
            viewModel = ViewModelProvider(this).get(LcsportsViewModel::class.java)
            viewModel.login(LoginModel(binding.etEmail.text.toString(), binding.etSenha.text.toString()))

            viewModel.nasaResponse.observe(this, Observer { response ->
                // Salvar o idUsuario nas SharedPreferences
                val sharedPreferences = getSharedPreferences("suas_preferencias", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                // Se response.id for nulo, você pode fornecer um valor padrão, como -1
                val idUsuario = response.id ?: -1

                editor.putInt("idUsuario", idUsuario)
                editor.apply()

                // Iniciar a tela de Feed
                val telaFeed = Intent(this, Feed::class.java)
                startActivity(telaFeed)
            })
        }



//        binding.btLogar.setOnClickListener {
//            if (binding.etEmail.text.toString().isEmpty()) {
//                binding.etEmail.error = "Preencha todos os campos"
//            } else if (binding.etSenha.text.toString().isEmpty()) {
//                binding.etSenha.error = "Preencha todos os campos"
//            } else if (binding.etSenha.text.toString().length < 6) {
//                binding.etSenha.error = "Senha inválida"
//            } else if (!binding.etEmail.text.toString().contains("@")) {
//                binding.etEmail.error = "Email inválido"
//            } else {
//                val email = binding.etEmail.text.toString()
//                val senha = binding.etSenha.text.toString()
//
//                val dadosLogin = JSONObject()
//                dadosLogin.put("email", email)
//                dadosLogin.put("senha", senha)
//
//                val client = OkHttpClient()
//                val url = "https://lcsports.azurewebsites.net/v1/usuarios"
//                val json = dadosLogin.toString()
//                val body =
//                    RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
//                val request = Request.Builder()
//                    .url(url)
//                    .post(body)
//                    .build()
//
//                client.newCall(request).enqueue(object : okhttp3.Callback {
//                    override fun onFailure(call: okhttp3.Call, e: IOException) {
//                        runOnUiThread {
//                            Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
//                        }
//                    }
//
//                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
//                        val responseStatus = response.code
//                        val responseBody = response.body?.string()
//
//                        runOnUiThread {
//                            if (responseStatus == 404) {
//                                binding.etEmail.error = "Email não cadastrado"
//                            } else if (responseStatus == 401) {
//                                binding.etSenha.error = "Senha incorreta"
//                            } else if (response.isSuccessful) {
//                                if (responseBody != null) {
//                                    val gson = Gson()
//                                    authManager.saveAuthToken(login.token.toString())
//                                }
//                            } else {
//                                Toast.makeText(
//                                    baseContext,
//                                    "Erro na resposta, tente novamente mais tarde",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                                println(dadosLogin)
//                            }
//                        }
//                    }
//                })
//            }
//        }


    }
}
