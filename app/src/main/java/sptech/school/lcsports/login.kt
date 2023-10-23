package sptech.school.lcsports

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import sptech.school.lcsports.databinding.ActivityTelaLoginBinding

class login : AppCompatActivity() {

    val binding by lazy {
        ActivityTelaLoginBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_login)

        binding.btLogar.setOnClickListener{
            Toast.makeText(baseContext, "Mudando de tela", Toast.LENGTH_SHORT).show()
            val feed = Intent(this, feed::class.java)
            startActivity(feed)
        }
    }
}