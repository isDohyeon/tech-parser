package hnu.multimedia.techparser.validate

import android.content.Context
import android.widget.Toast

class UserInfoValidator {

    companion object {
        fun validateJoin(
            context: Context,
            nickname: String,
            email: String,
            password: String,
            passwordConfig: String,
        ) : Boolean {
            if (nickname.isEmpty()) {
                Toast.makeText(context, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (email.isEmpty()) {
                Toast.makeText(context, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (password.isEmpty()) {
                Toast.makeText(context, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (password != passwordConfig) {
                Toast.makeText(context, "패스워드를 동일하게 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }

        fun validateLogin(
            context: Context,
            email: String,
            password: String
        ): Boolean {
            if (email.isEmpty()) {
                Toast.makeText(context, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            if (password.isEmpty()) {
                Toast.makeText(context, "패스워드를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}