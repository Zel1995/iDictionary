package com.example.idictionary.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.idictionary.databinding.SearchDialogFragmentBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SearchDialogFragment : BottomSheetDialogFragment() {
    private var _binding: SearchDialogFragmentBinding? = null
    private val binding get() = _binding!!
    private var onSearchClick: ((String) -> Unit)? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchDialogFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInput()
        initSearchBtn()

    }

    private fun initSearchBtn() {
        binding.searchButton.setOnClickListener {
            onSearchClick?.invoke(binding.searchEditText.text.toString())
            dismiss()
        }
    }

    private fun initInput() {
        binding.searchEditText.addTextChangedListener {
            binding.searchButton.isEnabled = (it != null && it.isNotEmpty() && it.length > 1)
        }
    }

    fun setOnSearchClickListener(onSearchClickListener: (String) -> Unit) {
        onSearchClick = onSearchClickListener
    }


}