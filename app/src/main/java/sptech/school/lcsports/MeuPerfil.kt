package sptech.school.lcsports

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import sptech.school.lcsports.databinding.ActivityMeuPerfilBinding
import sptech.school.lcsports.domain.Usuario
import java.io.IOException
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

class MeuPerfil : AppCompatActivity() {

    val binding by lazy {
        ActivityMeuPerfilBinding.inflate(layoutInflater)
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        popularDados()

        binding.btHome.setOnClickListener {
            Toast.makeText(baseContext, "Home", Toast.LENGTH_SHORT).show()
            val telaHome = Intent(this, Feed::class.java)
            startActivity(telaHome)
        }

        binding.btLupa.setOnClickListener {
            Toast.makeText(baseContext, "Categorias", Toast.LENGTH_SHORT).show()
            val telaCategorias = Intent(this, Categorias::class.java)
            startActivity(telaCategorias)
        }

        binding.btFavoritos.setOnClickListener {
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
        }

        binding.txtEditarPerfil.setOnClickListener {
            Toast.makeText(baseContext, "Editando Perfil", Toast.LENGTH_SHORT).show()
            val telaEditarPerfil = Intent(this, EditarPerfil::class.java)
            startActivity(telaEditarPerfil)
        }

    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    fun popularDados() {

        val sharedPreferences = getSharedPreferences("suas_preferencias", Context.MODE_PRIVATE)
        val idUsuario = sharedPreferences.getInt("idUsuario", -1)
        val client = OkHttpClient()
        val url = "https://lcsports.azurewebsites.net/v1/usuarios/$idUsuario"
        val request = Request.Builder().url(url).get().build()

        client.newCall(request).enqueue(object : okhttp3.Callback {
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(baseContext, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val responseBody = response.body?.string()

                runOnUiThread {
                    if (response.isSuccessful) {
                        if (responseBody != null) {
                            val gson = Gson()
                            val usuario = gson.fromJson(responseBody, Usuario::class.java)
                            binding.nomeCostureira.text = usuario.nome
                            binding.tvBio.text = usuario.biografia
                            binding.tvWpp.text = usuario.telefone
                            binding.tvEmail.text = usuario.email

                            val urlFotoUsuario = usuario.fotoUsuario

                            Glide.with(this@MeuPerfil)
                                .load(urlFotoUsuario)
                                .apply(
                                    RequestOptions()
                                        .diskCacheStrategy(DiskCacheStrategy.ALL) // Cache a imagem em disco
                                        .placeholder(R.mipmap.padrao) // Imagem de espaço reservado enquanto carrega
                                        .error(R.mipmap.perfil) // Imagem a ser exibida em caso de erro de carregamento
                                )
                                .into(binding.imgPerfil)

                        }
                    } else {
                        Toast.makeText(
                            baseContext,
                            "Erro de conexão!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })

    }
}