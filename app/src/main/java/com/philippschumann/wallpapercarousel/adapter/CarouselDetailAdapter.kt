package com.philippschumann.wallpapercarousel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.R
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages
import com.philippschumann.wallpapercarousel.database.model.Image

class CarouselDetailAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<CarouselDetailAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var images = emptyList<Image>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text = itemView.findViewById<TextView>(R.id.text)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CarouselDetailAdapter.ViewHolder {
        val itemView: View = if (viewType == VIEW_TYPE_SETTINGS) {
            inflater.inflate(R.layout.fragment_carousel_detail_settings_list_item, parent, false)
        } else {
            inflater.inflate(
                R.layout.fragment_carousel_overview_list_item_4_wallpapers,
                parent,
                false
            )
        }
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_SETTINGS) {

        } else if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            val current = images[position]
            holder.text.text = current.path
        }

    }

    internal fun setCarousel(carousel: CarouselWithImages) {
        this.images = carousel.images
        notifyDataSetChanged()
    }

    //add one for settings view
    override fun getItemCount() = images.size + 1

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            VIEW_TYPE_SETTINGS
        } else {
            VIEW_TYPE_ITEM
        }
    }

    companion object {
        const val VIEW_TYPE_SETTINGS = 0
        const val VIEW_TYPE_ITEM = 1
    }
}