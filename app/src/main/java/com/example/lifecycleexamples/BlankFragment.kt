package com.example.lifecycleexamples

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import org.w3c.dom.Text

class BlankFragment : Fragment() {

    val viewModel: MyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textContent = view.findViewById<TextView>(R.id.text_content)
        textContent.text = viewModel.count.toString()
        view.findViewById<Button>(R.id.button_count).setOnClickListener {
            viewModel.doCount()
            textContent.text = viewModel.count.toString()
        }
    }
}