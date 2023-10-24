package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityTelaCadastroBinding

class cadastro : AppCompatActivity() {
    val binding by lazy {
        ActivityTelaCadastroBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btCadastrar.setOnClickListener {
            Toast.makeText(baseContext, "Usuário cadastrado!!", Toast.LENGTH_SHORT).show()
            val telaLogin= Intent(this, login::class.java)
            startActivity(telaLogin)
        }

        binding.txtTermos.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

    }
}