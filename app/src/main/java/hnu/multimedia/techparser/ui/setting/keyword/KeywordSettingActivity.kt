package hnu.multimedia.techparser.ui.setting.keyword

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.databinding.ActivityKeywordSettingBinding
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.tasks.await

class KeywordSettingActivity : AppCompatActivity() {

    private val binding by lazy {ActivityKeywordSettingBinding.inflate(layoutInflater) }
    private var keywords = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        getKeywords()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = KeywordSettingAdapter(keywords)

        binding.buttonKeywordAdd.setOnClickListener {
            createKeywordAddDialog()
        }
    }

    private fun createKeywordAddDialog() {
        val editText = EditText(binding.root.context).apply {
            hint = " 키워드를 입력하세요"
        }

        AlertDialog.Builder(binding.root.context)
            .setTitle("키워드 추가")
            .setView(editText)
            .setPositiveButton("추가") { _, _ ->
                val keyword = editText.text.toString().trim()
                if (keyword.isNotEmpty()) {
                    FirebaseRef.keywordRef().push().setValue(keyword)
                    Toast.makeText(binding.root.context, "\"$keyword\" 키워드가 추가되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            .setNegativeButton("취소", null)
            .show()
    }

    private fun getKeywords() {
        val ref = FirebaseRef.keywordRef()
        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                keywords.clear()
                for (child in snapshot.children) {
                    val value = child.getValue(String::class.java)
                    value?.let {
                        keywords.add(value)
                    }
                }
                binding.recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(postListener)
    }
}