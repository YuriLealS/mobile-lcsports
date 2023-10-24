package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityFavoritosBinding

class favoritos : AppCompatActivity() {

    val binding by lazy {
        ActivityFavoritosBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btHome.setOnClickListener{
            Toast.makeText(baseContext, "Home", Toast.LENGTH_SHORT).show()
            val telaHome = Intent(this, feed::class.java)
            startActivity(telaHome)
        }

        binding.btFavoritos.setOnClickListener{
            Toast.makeText(baseContext, "Você já está em favoritos", Toast.LENGTH_SHORT).show()
        }

        binding.img1.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

        binding.img2.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

        binding.img3.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

        binding.btPerfil.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }

        binding.btPublicar.setOnClickListener {
            Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
        }
    }
}