package hnu.multimedia.techparser.ui.feed.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hnu.multimedia.techparser.databinding.ItemNotificationLogBinding


class NotificationLogAdapter(
    private val logs: List<Triple<String, String, String>>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<NotificationLogAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemNotificationLogBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(log: Triple<String, String, String>) {
            binding.textViewTitle.text = log.first
            binding.textViewMessage.text = log.second
            binding.root.setOnClickListener {
                onItemClick(log.third)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationLogBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = logs.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(logs[position])
    }
}