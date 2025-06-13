package hnu.multimedia.techparser.ui.subscribe

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import hnu.multimedia.techparser.R
import hnu.multimedia.techparser.databinding.FragmentSubscribeBinding
import hnu.multimedia.techparser.rss.RssRepository
import hnu.multimedia.techparser.ui.subscribe.model.BlogModel
import hnu.multimedia.techparser.util.FirebaseRef
import kotlinx.coroutines.launch

class SubscribeFragment : Fragment() {

    private val binding by lazy { FragmentSubscribeBinding.inflate(layoutInflater) }
    private val subscribeBlogs = mutableListOf<BlogModel>()
    private val recommendBlogs = mutableListOf<BlogModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch {
            getBlogs()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.adapter = SubscribeAdapter(subscribeBlogs, true)
        updateButtonStyle(true)

        binding.buttonSubscribe.setOnClickListener {
            binding.recyclerView.adapter = SubscribeAdapter(subscribeBlogs, true)
            updateButtonStyle(true)
        }
        binding.buttonRecommend.setOnClickListener {
            binding.recyclerView.adapter = SubscribeAdapter(recommendBlogs, false)
            updateButtonStyle(false)
        }

        return binding.root
    }

    private fun getBlogs() {
        val ref = FirebaseRef.subscribeRef()
        val postListener = object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                subscribeBlogs.clear()
                recommendBlogs.clear()

                val allBlogs = RssRepository.blogs
                val subscribedKeys = snapshot.children.map { it.key.toString() }.toSet()

                subscribeBlogs.addAll(
                    allBlogs.filter { it.name in subscribedKeys }
                )

                recommendBlogs.addAll(
                    allBlogs.filter { it.name !in subscribedKeys }
                )

                binding.recyclerView.adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}
        }
        ref.addValueEventListener(postListener)
    }

    private fun updateButtonStyle(isSubscribeMode: Boolean) {
        setButtonStyle(binding.buttonSubscribe, isSubscribeMode)
        setButtonStyle(binding.buttonRecommend, !isSubscribeMode)
    }

    private fun setButtonStyle(button: View, isSelected: Boolean) {
        val backgroundColor = if (isSelected) R.color.blue else R.color.gray
        val textColor = android.R.color.white

        button.setBackgroundColor(resources.getColor(backgroundColor, null))
        (button as? android.widget.Button)?.setTextColor(resources.getColor(textColor, null))
    }
}