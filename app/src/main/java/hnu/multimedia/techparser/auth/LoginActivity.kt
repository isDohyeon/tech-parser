package hnu.multimedia.techparser.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hnu.multimedia.techparser.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}