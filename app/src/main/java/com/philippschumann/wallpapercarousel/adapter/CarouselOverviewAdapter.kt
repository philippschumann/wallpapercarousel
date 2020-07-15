package com.philippschumann.wallpapercarousel.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.R
import com.philippschumann.wallpapercarousel.model.Carousel

class CarouselOverviewAdapter internal constructor(context: Context) :
    RecyclerView.Adapter<CarouselOverviewAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var carousels = emptyList<Carousel>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textPrimary = itemView.findViewById<TextView>(R.id.text_view_1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.fragment_carousel_overview_list_item_4_wallpapers, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = carousels[position]
        holder.textPrimary.text = current.carouselId.toString()
    }

    internal fun setCarousels(carousels: List<Carousel>) {
        this.carousels = carousels
        notifyDataSetChanged()
    }

    override fun getItemCount() = carousels.size
}