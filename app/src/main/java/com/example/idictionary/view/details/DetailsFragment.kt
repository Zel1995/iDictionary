package com.example.idictionary.view.details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.ImageLoader
import coil.request.LoadRequest
import com.example.idictionary.R
import com.example.idictionary.databinding.FragmentDetailsBinding
import com.example.idictionary.model.data.DataModel

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
        val url = dataModel?.meanings?.get(0)?.imageUrl
        val request = LoadRequest.Builder(requireContext())
            .data("https:$url")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .target(
                onStart = {},
                onError = {},
                onSuccess = {
                    binding.detailsImg.setImageDrawable(it)
                }).build()
        ImageLoader(requireContext()).execute(request)

    }
}