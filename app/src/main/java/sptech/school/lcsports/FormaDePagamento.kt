package sptech.school.lcsports

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.*
import sptech.school.lcsports.databinding.ActivityFormaDePagamentoBinding
import java.io.IOException

class FormaDePagamento : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormaDePagamentoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
            val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
            startActivity(telaMeuPerfil)
        }

        binding.btnPdf.setOnClickListener {
            criarAssinaturaOneStep(6)

        }

        binding.btnPremium.setOnClickListener {
            criarAssinaturaOneStep(6)

        }
    }

    private fun criarAssinaturaOneStep(idUsuario: Int) {
        val clienteOkHttp = OkHttpClient()

        val request = Request.Builder()
            .url("https://lcsports.azurewebsites.net/v1/assinatura/$idUsuario")
            .post(RequestBody.create(null, ByteArray(0)))
            .build()

        clienteOkHttp.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val json = response.body?.string()
                    val informacoes = extrairInformacoesDoJson(json)

                    informacoes?.let {
                        val billetLink = it.first
                        val qrcodeImage = it.second

                        runOnUiThread {
                            Toast.makeText(baseContext, "billet_link: $billetLink", Toast.LENGTH_SHORT).show()
                            Toast.makeText(baseContext, "qrcode_image: $qrcodeImage", Toast.LENGTH_SHORT).show()

                            if (!billetLink.isNullOrBlank()) {
                                abrirLinkNoNavegador(billetLink)
                            }
                        }
                    }

                    val intent = Intent(this@FormaDePagamento, Feed::class.java)
                    startActivity(intent)
                } else {
                    runOnUiThread {
                        Toast.makeText(baseContext, "Erro na requisição: ${response.code}", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(baseContext, "Erro na requisição: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun abrirLinkNoNavegador(link: String) {
        val uri = Uri.parse(link)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }


    private fun extrairInformacoesDoJson(json: String?): Pair<String, String>? {
        try {
            val gson = Gson()
            val apiResponse = gson.fromJson(json, ApiResponse::class.java)

            val billetLink = apiResponse.data.billetLink
            val qrcodeImage = apiResponse.data.pix.qrcodeImage

            return Pair(billetLink, qrcodeImage)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }
}