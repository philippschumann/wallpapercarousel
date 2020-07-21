package com.philippschumann.wallpapercarousel.adapter

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.R
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages
import java.lang.Exception

class CarouselDetailAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private lateinit var carouselWithImages: CarouselWithImages

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val text: TextView = view.findViewById(R.id.text)
        fun setDetails(position: Int) {
            val current = carouselWithImages.images[position]
            text.text = current.path
        }
    }

    inner class SettingsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val spinner: AppCompatSpinner = view.findViewById(R.id.spinner_frequency)
        private val editTextInterval: AppCompatEditText = view.findViewById(R.id.edit_text_interval)
        fun setDetails() {
            setSpinner()
            setEditText()
        }

        private fun setEditText() {
            editTextInterval.setText(carouselWithImages.carousel.interval.toString())
            editTextInterval.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    Log.d(TAG, "new text: $s")
                    try {
                        carouselWithImages.carousel.interval = s.toString().toInt()
                    } catch (e: Exception) {
                        carouselWithImages.carousel.interval = 0
                    }
                }
            })
        }

        private fun setSpinner() {
            ArrayAdapter.createFromResource(
                context,
                R.array.frequency_plural,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
                spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(p0: AdapterView<*>?) {

                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        carouselWithImages.carousel.frequency =
                            carouselWithImages.carousel.frequencyKeys[position]
                    }
                }
                spinner.setSelection(
                    carouselWithImages.carousel.frequencyKeys.indexOf(
                        carouselWithImages.carousel.frequency
                    )
                )
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_SETTINGS) {
            val view: View = inflater.inflate(
                R.layout.fragment_carousel_detail_settings_list_item,
                parent,
                false
            )
            SettingsViewHolder(view)
        } else {
            val view: View = inflater.inflate(
                R.layout.fragment_carousel_overview_list_item_4_wallpapers,
                parent,
                false
            )
            ItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_SETTINGS) {
            (holder as SettingsViewHolder).setDetails()
        } else if (getItemViewType(position) == VIEW_TYPE_ITEM) {
            (holder as ItemViewHolder).setDetails(position)
        }
    }


    internal fun setCarousel(carousel: CarouselWithImages) {
        this.carouselWithImages = carousel
        notifyDataSetChanged()
    }

    //add one for settings view
    override fun getItemCount() = carouselWithImages.images.size + 1

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
        private const val TAG = "detail adapter"

    }
}