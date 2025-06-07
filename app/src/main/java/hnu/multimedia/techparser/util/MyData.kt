package hnu.multimedia.techparser.util

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import hnu.multimedia.techparser.auth.UserModel

class MyData {

    companion object {
        private var email: String = ""
        var userModel: UserModel = UserModel()

        fun init() {
            email = Firebase.auth.currentUser?.email.toString()
            val postListener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.getValue(UserModel::class.java)
                    value?.let {
                        userModel = value
                    }
                }
                override fun onCancelled(error: DatabaseError) {}
            }
            FirebaseRef.users.child(FirebaseRef.currentUserId).addValueEventListener(postListener)
        }
    }
}