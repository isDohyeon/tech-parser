package hnu.multimedia.techparser.validate

import android.view.View
import com.google.android.material.snackbar.Snackbar

class JoinValidator {

    companion object {
        fun validateInfo(
            view: View,
            nickname: String,
            email: String,
            password: String,
            passwordConfig: String,
        ) : Boolean {
            if (nickname.isEmpty()) {
                Snackbar.make(view, "닉네임을 입력해주세요.", Snackbar.LENGTH_LONG).show()
                return false
            }
            if (email.isEmpty()) {
                Snackbar.make(view, "이메일을 입력해주세요.", Snackbar.LENGTH_LONG).show()
                return false
            }
            if (password.isEmpty()) {
                Snackbar.make(view, "패스워드를 입력해주세요.", Snackbar.LENGTH_LONG).show()
                return false
            }
            if (password != passwordConfig) {
                Snackbar.make(view, "패스워드를 동일하게 입력해주세요.", Snackbar.LENGTH_LONG).show()
                return false
            }
            return true
        }
    }
}