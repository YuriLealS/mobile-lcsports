package sptech.school.lcsports
import android.content.Intent
import sptech.school.lcsports.databinding.ActivityFeedBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Feed : AppCompatActivity() {

        val binding by lazy {
            ActivityFeedBinding.inflate(layoutInflater)
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

            binding.img1.setOnClickListener {
                Toast.makeText(baseContext, "Postagem", Toast.LENGTH_SHORT).show()
                val telaPostagem = Intent(this, Postagem::class.java)
                startActivity(telaPostagem)
            }

            binding.img2.setOnClickListener {
                Toast.makeText(baseContext, "Postagem", Toast.LENGTH_SHORT).show()
                val telaPostagem = Intent(this, Postagem::class.java)
                startActivity(telaPostagem)
            }

            binding.img3.setOnClickListener {
                Toast.makeText(baseContext, "Postagem", Toast.LENGTH_SHORT).show()
                val telaPostagem = Intent(this, Postagem::class.java)
                startActivity(telaPostagem)
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
                Toast.makeText(baseContext, "Meu Perfil", Toast.LENGTH_SHORT).show()
                val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
                startActivity(telaMeuPerfil)
            }
        }
}