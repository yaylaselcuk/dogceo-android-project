package com.yaylas.dogceo.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yaylas.dogceo.R
import com.yaylas.dogceo.databinding.ItemBreedImageBinding
import com.yaylas.dogceo.domain.model.ImageInfo

class BreedImagesAdapter(
    private val imagesList: List<ImageInfo>
) : RecyclerView.Adapter<BreedImagesAdapter.BreedImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedImagesViewHolder {
        val binding = ItemBreedImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return BreedImagesViewHolder(binding)
    }

    override fun getItemCount() = imagesList.size

    override fun onBindViewHolder(holder: BreedImagesViewHolder, position: Int) {
        with(holder) {
            with(imagesList[position]) {
                Glide.with(holder.itemView.context)
                    .load(url)
                    .placeholder(R.drawable.img_placeholder)
                    .into(binding.root)
            }
        }
    }


    inner class BreedImagesViewHolder(val binding: ItemBreedImageBinding) :
        RecyclerView.ViewHolder(binding.root)

}