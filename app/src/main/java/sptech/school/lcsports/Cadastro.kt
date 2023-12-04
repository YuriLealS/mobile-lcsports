package sptech.school.lcsports

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import sptech.school.lcsports.databinding.ActivityCadastroBinding
import sptech.school.lcsports.services.model.CadastroModel
import sptech.school.lcsports.viewmodel.LcsportsViewModel

class Cadastro : AppCompatActivity() {
    val binding by lazy {
        ActivityCadastroBinding.inflate(layoutInflater)
    }

    private lateinit var viewModel: LcsportsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val authManager = AuthManager(this)

        binding.btCadastrar.setOnClickListener {
            if (binding.etSenha.text.toString() == binding.etConfirmar.text.toString()) {
                viewModel = ViewModelProvider(this).get(LcsportsViewModel::class.java)
                viewModel.cadastrar(
                    CadastroModel(
                        binding.etNome.text.toString(),
                        binding.etEmail.text.toString(),
                        binding.etCpf.text.toString(),
                        binding.etCep.text.toString(),
                        binding.etUf.text.toString(),
                        binding.etCidade.text.toString(),
                        binding.etTel.text.toString(),
                        binding.etSenha.text.toString()
                    )
                )
                viewModel.cadastroResponse.observe(this, Observer { response ->
                    val telaFeed = Intent(this, Login::class.java)
                    startActivity(telaFeed)
                })
            }
        }

    }


}