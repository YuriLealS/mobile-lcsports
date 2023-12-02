package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityFeedBinding
import sptech.school.lcsports.databinding.ActivityPostagemBinding

class Postagem : AppCompatActivity() {

    val binding by lazy {
        ActivityPostagemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btHome.setOnClickListener{
            Toast.makeText(baseContext, "Home", Toast.LENGTH_SHORT).show()
            val telaHome = Intent(this, Feed::class.java)
            startActivity(telaHome)
        }

        binding.btLupa.setOnClickListener {
            Toast.makeText(baseContext, "Categorias", Toast.LENGTH_SHORT).show()
            val telaCategorias = Intent(this, Categorias::class.java)
            startActivity(telaCategorias)
        }

        binding.btFavoritos.setOnClickListener{
            Toast.makeText(baseContext, "Favoritos", Toast.LENGTH_SHORT).show()
            val telaFavoritos = Intent(this, Favoritos::class.java)
            startActivity(telaFavoritos)
        }

        binding.btPublicar.setOnClickListener {
            Toast.makeText(baseContext, "Postar", Toast.LENGTH_SHORT).show()
            val telaCriarPostagem = Intent(this, CriarPostagem::class.java)
            startActivity(telaCriarPostagem)
        }

        binding.btPerfil.setOnClickListener {
            Toast.makeText(baseContext, "MeuPerfil", Toast.LENGTH_SHORT).show()
            val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
            startActivity(telaMeuPerfil)
        }

    }
}