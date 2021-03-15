package com.yaylas.dogceo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yaylas.dogceo.databinding.ItemBreedListBinding
import com.yaylas.dogceo.domain.model.Breed


class BreedsAdapter(
    private val breedList: List<Breed>,
    private val itemClicked: (item: Breed) -> Unit
) : RecyclerView.Adapter<BreedsAdapter.BreedsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        val binding = ItemBreedListBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedsViewHolder(binding)
    }

    override fun getItemCount() = breedList.size

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        with(holder) {
            with(breedList[position]) {
                binding.tvBreedName.text = name.capitalize()
                binding.root.setOnClickListener { itemClicked(this) }
            }
        }
    }


    inner class BreedsViewHolder(val binding: ItemBreedListBinding) :
        RecyclerView.ViewHolder(binding.root)

}