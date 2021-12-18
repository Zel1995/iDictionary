package com.example.idictionary.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.idictionary.R
import com.example.idictionary.databinding.FragmentDetailsBinding
import com.example.idictionary.model.data.AppState
import com.example.idictionary.model.data.DataModel
import com.example.idictionary.utils.parseSearchResult

class DetailsFragment : Fragment(R.layout.fragment_details) {
    companion object {
        private const val DETAILS_KEY = "DETAILS_KEY"
        fun newInstance(dataModel: DataModel): DetailsFragment =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(DETAILS_KEY, dataModel)
                }
            }
    }

    var _binding: FragmentDetailsBinding? = null
    val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsBinding.bind(view)
        val dataModel = arguments?.getParcelable<DataModel>(DETAILS_KEY)
        initViews(dataModel)
    }

    private fun initViews(dataModel: DataModel?) {
        binding.detailsTitleTextView.text = dataModel?.text
        binding.detailsContentTextView.text = dataModel?.meanings?.get(0)?.translation?.translation

    }
}