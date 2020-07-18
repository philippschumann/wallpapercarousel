package com.philippschumann.wallpapercarousel

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.adapter.CarouselOverviewAdapter
import com.philippschumann.wallpapercarousel.model.CarouselWithImages

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CarouselOverviewFragment : Fragment(), CarouselOverviewCellClickListener {
    private lateinit var carouselOverviewViewModel: CarouselOverviewViewModel
    private lateinit var overviewAdapter: CarouselOverviewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        if ((activity as AppCompatActivity).supportActionBar != null) {
            (activity as AppCompatActivity).supportActionBar?.apply {
                //setHomeButtonEnabled(false)
                setDisplayHomeAsUpEnabled(false)
                setDisplayShowHomeEnabled(false)
            }
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carousel_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*  view.findViewById<Button>(R.id.button_first).setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/
        val recyclerView: RecyclerView =
            view.findViewById<RecyclerView>(R.id.recycler_view_overview)
        overviewAdapter = CarouselOverviewAdapter(requireContext(), this)
        recyclerView.adapter = overviewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        carouselOverviewViewModel =
            ViewModelProvider(this).get(CarouselOverviewViewModel::class.java)
        carouselOverviewViewModel.allCarousels.observe(viewLifecycleOwner, Observer { carousels ->
            carousels?.let { overviewAdapter.setCarousels(it) }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.overview_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCellClicked(carousel: CarouselWithImages) {
        //Toast.makeText(requireContext(), carousel.carousel.carouselId, Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_OverviewFragment_to_DetailFragment)
    }
}