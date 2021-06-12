package com.example.infinityslider

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.infinityslider.databinding.SliderItemContainerBinding
import com.makeramen.roundedimageview.RoundedImageView


class SliderAdapter internal constructor(
        sliderItems: MutableList<SliderItem>,
        viewPager: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    private val sliderItems: List<SliderItem>
    private val viewPager : ViewPager2

    init {
        this.sliderItems = sliderItems
        this.viewPager = viewPager
    }

    inner class SliderViewHolder(val binding: SliderItemContainerBinding) : RecyclerView.ViewHolder(binding.root) {
        private val imageView: RoundedImageView = binding.imageSlider

        fun bind(sliderItem: SliderItem) {
            imageView.setImageResource(sliderItem.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = SliderItemContainerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItems[position])
        if (position == sliderItems.size -2){
            viewPager.post(runnable)
        }
    }

    override fun getItemCount(): Int {
        return sliderItems.size
    }

    private var runnable = Runnable {
        sliderItems.addAll(sliderItems)
        notifyDataSetChanged()
    }

}