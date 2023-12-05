package sptech.school.lcsports

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONObject
import sptech.school.lcsports.databinding.ActivityCriarPostagemBinding
import java.io.ByteArrayOutputStream
import java.io.IOException

class CriarPostagem : AppCompatActivity() {

    private val binding by lazy {
        ActivityCriarPostagemBinding.inflate(layoutInflater)
    }

    companion object {
        private const val PICK_IMAGE_REQUEST = 1
    }

    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUpload.setOnClickListener {
            openGallery()
        }

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
            Toast.makeText(baseContext, "Meu Perfil", Toast.LENGTH_SHORT).show()
            val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
            startActivity(telaMeuPerfil)
        }

        binding.postarpostagem.setOnClickListener {
            if (selectedImageUri != null) {
                Thread {
                    val bitmap = if (Build.VERSION.SDK_INT < 28) {
                        MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
                    } else {
                        val source = ImageDecoder.createSource(contentResolver, selectedImageUri!!)
                        ImageDecoder.decodeBitmap(source)
                    }

                    runOnUiThread {
                        val imageBody = criarImageBody(bitmap)
                        val novoEchoBody = criarNovoEchoBody()

                        enviarDadosParaServidor(imageBody, novoEchoBody)
                    }
                }.start()
            }
            val telaMeuPerfil = Intent(this, MeuPerfil::class.java)
            startActivity(telaMeuPerfil)
        }

    }

    private fun criarImageBody(bitmap: Bitmap): MultipartBody.Part {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val imageByteArray = byteArrayOutputStream.toByteArray()

        return MultipartBody.Part.createFormData(
            "imagem",
            "imagem.jpg",
            imageByteArray.toRequestBody("image/*".toMediaTypeOrNull())
        )
    }

    private fun criarNovoEchoBody(): MultipartBody.Part {

        val sharedPreferences = getSharedPreferences("suas_preferencias", Context.MODE_PRIVATE)
        val idUsuario = sharedPreferences.getInt("idUsuario", -1)
        val tituloPostagem = binding.etTitulo.text.toString()
        val descricaoProduto = binding.etDescricao.text.toString()
        val novoEcho = JSONObject().apply {
            put("usuario", JSONObject().apply {
                put("idUsuario", idUsuario)
            })
            put("titulo", tituloPostagem)
            put("descricao", descricaoProduto)
            put("competencias", "futebol")

        }

        return MultipartBody.Part.createFormData(
            "postagem",
            null,
            novoEcho.toString().toRequestBody("multipart/form-data".toMediaTypeOrNull())
        )
    }

    private fun enviarDadosParaServidor(imageBody: MultipartBody.Part, novoEchoBody: MultipartBody.Part) {
        val clienteOkHttp = OkHttpClient()

        val request = Request.Builder()
            .url("https://lcsports.azurewebsites.net/v1/postagens")
            .post(MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addPart(imageBody)
                .addPart(novoEchoBody)
                .build())
            .build()

        clienteOkHttp.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val intent = Intent(this@CriarPostagem, Perfil::class.java)
                startActivity(intent)
            }

            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(baseContext, "Erro na requisição: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        })
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
            selectedImageUri = data.data
        }
    }
}