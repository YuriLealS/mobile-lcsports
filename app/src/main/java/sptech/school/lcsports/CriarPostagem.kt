package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityCriarPostagemBinding
class CriarPostagem : AppCompatActivity() {

    val binding by lazy {
        ActivityCriarPostagemBinding.inflate(layoutInflater)
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

            binding.btnUpload.setOnClickListener {
                openGallery()

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
                Toast.makeText(baseContext, "Meu Perfil", Toast.LENGTH_SHORT).show()
                val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
                startActivity(telaMeuPerfil)
            }
        }
    }
    private fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), PICK_IMAGE_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {
        }
    }
}