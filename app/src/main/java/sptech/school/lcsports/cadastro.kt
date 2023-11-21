package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import okhttp3.MediaType
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

        binding.btCadastrar.setOnClickListener {
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
                    val nome = binding.etNome.text.toString()
                    val cep = binding.etCep.text.toString()
                    val cpf = binding.etCpf.text.toString()
                    val uf = binding.etUf.text.toString()
                    val cidade = binding.etCidade.text.toString()
                    val tel = binding.etTel.text.toString()

                    val dadosCadastro = JSONObject()
                    dadosCadastro.put("email", email)
                    dadosCadastro.put("senha", senha)
                    dadosCadastro.put("nome", nome)
                    dadosCadastro.put("cep", cep)
                    dadosCadastro.put("cpf", cpf)
                    dadosCadastro.put("uf", uf)
                    dadosCadastro.put("cidade", cidade)
                    dadosCadastro.put("tel", tel)

                    val client = OkHttpClient()
                    val url = "https://18.208.9.255/v1/usuarios/autenticar"
                    val json = dadosCadastro.toString()
                    val body =
                        RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
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
                            val responseStatus = response.code()
                            val responseBody = response.body()?.string()

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
                                        Toast.makeText(baseContext, "Usuário cadastrado!!", Toast.LENGTH_SHORT).show()
                                        val intent = Intent(this@cadastro, login::class.java)
                                        startActivity(intent)
                                    }
                                } else {
                                    Toast.makeText(
                                        baseContext,
                                        "Erro na resposta, tente novamente mais tarde",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    println(dadosCadastro)
                                }
                            }
                        }
                    })
                }


            val telaLogin= Intent(this, login::class.java)
            startActivity(telaLogin)
        }

        binding.txtTermos.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

    }
}