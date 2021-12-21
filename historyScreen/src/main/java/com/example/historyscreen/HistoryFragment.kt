package com.example.historyscreen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.historyscreen.databinding.FragmentHistoryBinding
import com.example.utils.getAlertDialog
import org.koin.androidx.scope.ScopeFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryFragment : ScopeFragment(R.layout.fragment_history) {
    private val adapter = HistoryAdapter()
    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private val model by viewModel<HistoryViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHistoryBinding.bind(view)
        initList()
        initViewModel()
        model.getData()
    }

    private fun initViewModel() {
        model.subscribe().observe(viewLifecycleOwner) {
            when (it) {
                is com.example.model.AppState.Success -> {
                    adapter.setData(it.data)
                }
                is com.example.model.AppState.Error -> {
                    requireContext().getAlertDialog(
                        getString(R.string.dialog_title),
                        it.error.message.toString()
                    ).show()
                }
                is com.example.model.AppState.Loading -> {
                    //FIXME add progress
                }
            }
        }

    }

    private fun initList() {
        binding.historyRv.adapter = adapter
    }

}