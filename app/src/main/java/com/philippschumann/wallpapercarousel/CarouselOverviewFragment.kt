package com.philippschumann.wallpapercarousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.adapter.CarouselOverviewAdapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarouselOverviewFragment : Fragment() {
    private lateinit var carouselOverviewViewModel: CarouselOverviewViewModel
    private lateinit var overviewAdapter: CarouselOverviewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carousel_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*  view.findViewById<Button>(R.id.button_first).setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        overviewAdapter = CarouselOverviewAdapter(requireContext())
        recyclerView.adapter = overviewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        carouselOverviewViewModel = ViewModelProvider(this).get(CarouselOverviewViewModel::class.java)
        carouselOverviewViewModel.allCarousels.observe(viewLifecycleOwner, Observer { carousels ->
            carousels?.let { overviewAdapter.setCarousels(it) }
        })
    }
}