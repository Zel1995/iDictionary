package com.example.idictionary.view

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.idictionary.MainActivity
import com.example.idictionary.R
import com.example.idictionary.databinding.FragmentMainBinding
import com.example.idictionary.model.data.AppState
import com.example.idictionary.utils.ui.AlertDialogFragment
import com.example.idictionary.utils.ui.getAlertDialog
import com.example.idictionary.view.adapter.MainAdapter
import javax.inject.Inject

class MainFragment : Fragment(R.layout.fragment_main) {
    companion object {
        private const val DIALOG_FRAGMENT_KEY = "DIALOG_FRAGMENT_KEY"
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var model: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter: MainAdapter by lazy {
        MainAdapter {
            Toast.makeText(requireContext(), it.text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as MainActivity).appComponent.inject(this)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = viewModelFactory.create(MainViewModel::class.java)
        _binding = FragmentMainBinding.bind(view)
        binding.mainRecyclerview.adapter = adapter
        initFabListener()
        initViewModel()
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
                is AppState.Success -> {
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
                is AppState.Error -> {
                    requireContext().getAlertDialog(
                        getString(R.string.dialog_title),
                        it.error.message.toString()
                    ).show()
                }
                is AppState.Loading -> {
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