package sptech.school.lcsports
import android.content.Intent
import sptech.school.lcsports.databinding.ActivityFeedBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class feed : AppCompatActivity() {

        val binding by lazy {
            ActivityFeedBinding.inflate(layoutInflater)
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)

            binding.btFavoritos.setOnClickListener{
                Toast.makeText(baseContext, "Favoritos", Toast.LENGTH_SHORT).show()
                val telaFavoritos = Intent(this, favoritos::class.java)
                startActivity(telaFavoritos)
            }

            binding.btHome.setOnClickListener{
                Toast.makeText(baseContext, "Você já está na home", Toast.LENGTH_SHORT).show()
            }

            binding.btHome2.setOnClickListener{
                Toast.makeText(baseContext, "Você já está na home", Toast.LENGTH_SHORT).show()
            }

            binding.btLupa.setOnClickListener {
                Toast.makeText(baseContext, "Função ainda indisponível", Toast.LENGTH_SHORT).show()
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