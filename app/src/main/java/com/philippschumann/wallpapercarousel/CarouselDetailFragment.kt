package com.philippschumann.wallpapercarousel

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.adapter.CarouselDetailAdapter
import com.philippschumann.wallpapercarousel.model.CarouselWithImages

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class CarouselDetailFragment() : Fragment() {

    private lateinit var detailAdapter: CarouselDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        if ((activity as AppCompatActivity).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.apply {
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        } else {
            Log.d(TAG, "no actionbar found")
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carousel_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("detail", "view created")
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_detail)
        detailAdapter = CarouselDetailAdapter(requireContext())
        recyclerView.adapter = detailAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        // detailAdapter.setCarousel(carousel)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "activity created")
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.detail_menu, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().navigate(R.id.action_DetailFragment_to_OverviewFragment)
                Log.d("detail fragment", "navigate up")
            }
            /*R.id.action_settings -> {
                Log.d(TAG, "go to settings")
            }*/
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG: String = "CarouselDetailFragment"
    }
}