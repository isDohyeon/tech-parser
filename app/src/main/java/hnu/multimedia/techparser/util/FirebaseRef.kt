package hnu.multimedia.techparser.util

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseRef {

    companion object {
        private val database = Firebase.database
        val feeds = database.getReference("feeds")
        val users = database.getReference("users")
        val blogs = database.getReference("blogs")

        var currentUserId: String = Firebase.auth.currentUser?.uid ?: ""

        fun initCUid() {
            val currentUser = Firebase.auth.currentUser
            currentUserId = currentUser?.uid ?: ""
            MyData.init()
        }

        fun bookmarksRef(): DatabaseReference {
            return users.child(currentUserId).child("bookmarks")
        }

        fun bookmarkFolderRef(folderName: String): DatabaseReference {
            return bookmarksRef().child(folderName)
        }

        fun subscribeRef(): DatabaseReference {
            return users.child(currentUserId).child("subscribe")
        }

        fun settingsRef(): DatabaseReference {
            return users.child(currentUserId).child("settings")
        }
    }
}