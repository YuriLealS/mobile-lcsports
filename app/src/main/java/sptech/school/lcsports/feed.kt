package sptech.school.lcsports
import sptech.school.lcsports.databinding.ActivityFeedBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class feed : AppCompatActivity() {

        val binding by lazy {
            ActivityFeedBinding.inflate(layoutInflater)
        }
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(binding.root)  // <-- sempre mude a segunda linha do onCreate() por esta
        }
}