package hnu.multimedia.techparser.util

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
            currentUserId = Firebase.auth.currentUser?.uid ?: ""
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