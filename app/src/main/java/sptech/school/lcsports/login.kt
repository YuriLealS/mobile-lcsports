package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityTelaLoginBinding

class login : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btLogar.setOnClickListener {
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
}