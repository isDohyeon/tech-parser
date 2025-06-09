package hnu.multimedia.techparser.util

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {
        private val database = Firebase.database
        val users = database.getReference("users")
        val blogs = database.getReference("blogs")

        var currentUserId: String = Firebase.auth.currentUser?.uid ?: ""

        fun initCUid() {
            val currentUser = Firebase.auth.currentUser
            if (currentUser == null) {
                Log.w("folder log", "Firebase 유저가 아직 로그인되지 않았습니다.")
                return
            }

            currentUserId = currentUser.uid
            Log.d("folder log", "currentUserId: $currentUserId")
            MyData.init()
        }

        fun userBookmarksRef(): DatabaseReference {
            return users.child(currentUserId).child("bookmarks")
        }

        fun userSubscribeRef(): DatabaseReference {
            return users.child(currentUserId).child("subscribe")
        }

        fun userSettingsRef(): DatabaseReference {
            return users.child(currentUserId).child("settings")
        }
    }
}