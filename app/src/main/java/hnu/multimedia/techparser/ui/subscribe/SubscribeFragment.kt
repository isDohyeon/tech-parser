package hnu.multimedia.techparser.ui.subscribe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hnu.multimedia.techparser.databinding.FragmentSubscribeBinding

class SubscribeFragment : Fragment() {

    private val binding by lazy { FragmentSubscribeBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
}