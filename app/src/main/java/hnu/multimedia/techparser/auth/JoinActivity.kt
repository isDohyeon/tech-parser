package hnu.multimedia.techparser.auth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hnu.multimedia.techparser.auth.model.UserModel
import hnu.multimedia.techparser.databinding.ActivityJoinBinding
import hnu.multimedia.techparser.util.FirebaseRef
import hnu.multimedia.techparser.validate.UserInfoValidator

class JoinActivity : AppCompatActivity() {

    private val binding by lazy { ActivityJoinBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonJoin.setOnClickListener {
            val nickname = binding.editTextNickname.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            val passwordConfig = binding.editTextPasswordConfig.text.toString()
            if (UserInfoValidator.validateJoin(binding.root.context, nickname, email, password, passwordConfig)) {
                createUser(nickname, email, password)

            }
            return@setOnClickListener
        }
    }

    private fun createUser(nickName: String, email: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    saveUser(nickName, email, password)
                    Toast.makeText(binding.root.context, "회원가입 성공!.", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(binding.root.context, "회원가입 실패", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    private fun saveUser(nickName: String, email: String, password: String) {
        val uid = Firebase.auth.currentUser?.uid ?: ""
        val userModel = UserModel(uid, nickName, email, password)
        val userRef = FirebaseRef.users.child(uid)
        userRef.child("userInfo").setValue(userModel)
        userRef.child("bookmarks").child("${System.currentTimeMillis()}_기본 폴더").setValue(true)
        userRef.child("subscribe").setValue(true)
        userRef.child("settings").child("notification").setValue(true)
        userRef.child("settings").child("notificationBlog").setValue(true)
        userRef.child("settings").child("keyword").setValue(true)
    }
}