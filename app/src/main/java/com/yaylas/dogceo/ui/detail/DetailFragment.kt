package com.yaylas.dogceo.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.dogceo.R
import com.yaylas.dogceo.databinding.FragmentDetailBinding
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.domain.model.ImageInfo
import com.yaylas.dogceo.ui.BreedsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    val args: DetailFragmentArgs by navArgs()


    private val viewModel: DetailViewModel by viewModels()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArguments()
        setUpHeaderTitle()
        setUpRecyclerViews()
        subscribeObservers()
        viewModel.setStateEvent(DetailStateEvent.GetSubBreedsEvent)
    }

    private fun setUpHeaderTitle() {
        activity?.apply {
            var titleText = viewModel.parentBreed.capitalize()
            viewModel.breed?.let {
                titleText = "${it.capitalize()} $titleText"
            }
            (this as AppCompatActivity).supportActionBar?.title = titleText
        }

    }

    private fun setArguments() {
        viewModel.breed = args.breedName
        viewModel.parentBreed = args.parentBreed
    }

    private fun setUpRecyclerViews() {
        setUpBreedRecyclerView()
        setUpImagesRecyclerView()
    }

    private fun setUpBreedRecyclerView() {
        binding.rvSubBreeds.setHasFixedSize(true)
        binding.rvSubBreeds.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvSubBreeds.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun setUpImagesRecyclerView() {
        binding.rvImages.setHasFixedSize(true)
        binding.rvImages.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvImages.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun subscribeObservers() {
        viewModel.breedsDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Breed>> -> showBreedData(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> showProgressBar()
            }
        })
        viewModel.imageInfoDataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<ImageInfo>> -> showImageData(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> showProgressBar()
            }
        })
    }

    private fun showBreedData(data: List<Breed>) {
        if (data.isEmpty()) {
            viewModel.setStateEvent(DetailStateEvent.GetImagesEvent)
            return
        }
        binding.rvSubBreeds.adapter = BreedsAdapter(data, ::gotoDetail)
        binding.rvSubBreeds.visibility = View.VISIBLE
        hideProgressBar()
    }

    private fun showImageData(data: List<ImageInfo>) {
        binding.rvImages.adapter = BreedImagesAdapter(data)
        binding.rvImages.visibility = View.VISIBLE
        hideProgressBar()

    }


    private fun gotoDetail(breed: Breed) {
        val bundle = bundleOf(
            "parent_breed" to viewModel.parentBreed,
            "breed_name" to breed.name
        )
        findNavController().navigate(R.id.innerDetailFragment, bundle)
    }

    private fun errorOccurred(errorMessage: String?) {
        hideProgressBar()
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }


    private fun showProgressBar() {
        binding.rvImages.visibility = View.GONE
        binding.rvSubBreeds.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}