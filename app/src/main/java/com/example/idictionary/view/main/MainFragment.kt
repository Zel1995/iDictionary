package com.example.idictionary.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.idictionary.R
import com.example.idictionary.databinding.FragmentMainBinding
import com.example.utils.getAlertDialog
import com.example.idictionary.view.details.DetailsFragment
import com.example.historyscreen.HistoryFragment
import com.example.idictionary.view.main.adapter.MainAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    companion object {
        private const val DIALOG_FRAGMENT_KEY = "DIALOG_FRAGMENT_KEY"
    }

    private val model by viewModel<MainViewModel>()
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter: MainAdapter by lazy {
        MainAdapter {
            requireActivity().supportFragmentManager.beginTransaction().replace(
                R.id.main_fragment_container,
                DetailsFragment.newInstance(it)
            ).addToBackStack(getString(R.string.details)).commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        binding.mainRecyclerview.adapter = adapter
        initMenu()
        initFabListener()
        initViewModel()
    }

    private fun initMenu() {
        setHasOptionsMenu(true)
        binding.mainToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.history_item -> {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.main_fragment_container,
                            com.example.historyscreen.HistoryFragment()
                        )
                        .addToBackStack(getString(R.string.History)).commit()
                    return@setOnMenuItemClickListener true
                }
                else -> {
                    return@setOnMenuItemClickListener false
                }
            }
        }
    }

    private fun initFabListener() {
        binding.searchFab.setOnClickListener {
            val searchDialogFragment = SearchDialogFragment()
            searchDialogFragment.setOnSearchClickListener {
                model.getData(it)
            }
            searchDialogFragment.show(parentFragmentManager, DIALOG_FRAGMENT_KEY)
        }
    }

    private fun initViewModel() {
        model.subscribe().observe(viewLifecycleOwner, {
            when (it) {
                is com.example.model.AppState.Success -> {
                    val data = it.data
                    if (data.isNullOrEmpty()) {
                        requireContext().getAlertDialog(
                            getString(R.string.dialog_title),
                            getString(R.string.cant_find)
                        ).show()
                    } else {
                        adapter.setData(data)
                    }
                }
                is com.example.model.AppState.Error -> {
                    requireContext().getAlertDialog(
                        getString(R.string.dialog_title),
                        it.error.message.toString()
                    ).show()
                }
                is com.example.model.AppState.Loading -> {
                    binding.mainProgress.visibility = if (it.isLoading) View.VISIBLE else View.GONE
                    binding.mainRecyclerview.visibility =
                        if (it.isLoading) View.GONE else View.VISIBLE
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}