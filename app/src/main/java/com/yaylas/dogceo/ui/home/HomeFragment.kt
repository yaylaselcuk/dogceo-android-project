package com.yaylas.dogceo.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.dogceo.R
import com.yaylas.dogceo.databinding.FragmentHomeBinding
import com.yaylas.dogceo.domain.DataState
import com.yaylas.dogceo.domain.model.Breed
import com.yaylas.dogceo.ui.BreedsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel by hiltNavGraphViewModels<HomeViewModel>(R.id.nav_graph)

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        subscribeObservers()
        viewModel.setStateEvent(HomeStateEvent.GetMasterBreedsEvent)
    }

    private fun setUpRecyclerView() {
        binding.rvMasterBreeds.setHasFixedSize(true)
        binding.rvMasterBreeds.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMasterBreeds.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, { dataState ->
            when (dataState) {
                is DataState.Success<List<Breed>> -> showBreedData(dataState.data)
                is DataState.Error -> errorOccurred(dataState.exception.message)
                is DataState.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun showBreedData(data: List<Breed>) {
        binding.rvMasterBreeds.adapter = BreedsAdapter(data, ::gotoDetail)
        hideProgressBar()
    }

    private fun gotoDetail(breed: Breed) {
        val bundle = bundleOf("parent_breed" to breed.name)
        findNavController().navigate(R.id.detailFragment, bundle)
    }

    private fun errorOccurred(errorMessage: String?) {
        hideProgressBar()
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }


    private fun showProgressBar() {
        binding.rvMasterBreeds.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
        binding.rvMasterBreeds.visibility = View.VISIBLE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}