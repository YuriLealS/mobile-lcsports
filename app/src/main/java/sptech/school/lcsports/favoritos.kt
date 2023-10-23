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
        setContentView(R.layout.activity_favoritos)

        binding.btHome.setOnClickListener{
            Toast.makeText(baseContext, "Mudando de tela", Toast.LENGTH_SHORT).show()
            val telaHome = Intent(this, feed::class.java)
            startActivity(telaHome)
        }
    }
}