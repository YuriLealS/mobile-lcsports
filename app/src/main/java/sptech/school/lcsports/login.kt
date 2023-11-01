package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import retrofit2.Response
import sptech.school.lcsports.databinding.ActivityTelaLoginBinding
import sptech.school.lcsports.domain.UsuarioLoginDto
import sptech.school.lcsports.service.ApiService
import retrofit2.Callback
import retrofit2.Call
import sptech.school.lcsports.domain.Login
import sptech.school.lcsports.service.RetrofitClient

class login : AppCompatActivity() {

    private val apiService = RetrofitClient.instance
    var isLogado = false
    val binding by lazy {
        ActivityTelaLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLogar.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val senha = binding.etSenha.text.toString()
            fazerAutenticacao(email,senha)
            if (isLogado) {
                Toast.makeText(baseContext, "Logado com sucesso!!", Toast.LENGTH_SHORT).show()
                val telaFeed = Intent(this, feed::class.java)
                startActivity(telaFeed)
            }
            else{
                Toast.makeText(baseContext, "Usuário/Senha inválidos", Toast.LENGTH_SHORT).show()
            }
            Toast.makeText(baseContext, "Logado com sucesso!!", Toast.LENGTH_SHORT).show()
                val telaFeed = Intent(this, feed::class.java)
                startActivity(telaFeed)
        }

        binding.txtCadastro.setOnClickListener {
            Toast.makeText(baseContext, "Cadastre-se", Toast.LENGTH_SHORT).show()
            val telaCadastro = Intent(this, cadastro::class.java)
            startActivity(telaCadastro)
        }
    }
        fun fazerAutenticacao(email: String, senha: String) {
            val usuario = Login(email, senha)

            val call = apiService.autenticar(usuario)
            call.enqueue(object :Callback<UsuarioLoginDto> {
                override fun onResponse(call: Call<UsuarioLoginDto>, response: Response<UsuarioLoginDto>) {
                    if (response.isSuccessful) {
                        val usuarioTokenDto = response.body()
                        val nome = usuarioTokenDto?.nome
                        isLogado = true
                    } else {
                        if (response.code() == 404) {
                        } else if (response.code() == 401) {
                        }
                    }
                }

                override fun onFailure(call: Call<UsuarioLoginDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }
