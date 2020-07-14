package com.philippschumann.wallpapercarousel.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.R
import com.philippschumann.wallpapercarousel.model.Carousel
import kotlinx.android.synthetic.main.fragment_first_list_item.view.*

class CarouselAdapter(private val exampleList: List<Carousel>) :
    RecyclerView.Adapter<CarouselAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.fragment_first_list_item,
            parent, false
        )
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]
        //  holder.imageView.setImageResource(currentItem.imageResource)
        holder.textView1.text = currentItem.id.toString()
        //holder.textView2.text = currentItem.text2
    }

    override fun getItemCount() = exampleList.size
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.image_view
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
    }
}