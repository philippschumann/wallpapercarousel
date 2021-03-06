package com.philippschumann.wallpapercarousel

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.adapter.CarouselOverviewAdapter
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages

class CarouselOverviewFragment : Fragment(), CarouselOverviewCellClickListener,
    MainActivity.FABClickedListener {
    private val sharedViewModel: SharedViewModel by activityViewModels()
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
        val recyclerView: RecyclerView =
            view.findViewById(R.id.recycler_view_overview)
        overviewAdapter = CarouselOverviewAdapter(requireContext(), this)
        recyclerView.adapter = overviewAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        sharedViewModel.allCarousels.observe(viewLifecycleOwner, Observer { carousels ->
            carousels?.let { overviewAdapter.setCarousels(it) }
        })
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.overview_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCellClicked(carousel: CarouselWithImages) {
        sharedViewModel.select(carousel)
        val bundle =
            bundleOf(CarouselDetailFragment.NAVIGATION_TYPE to CarouselDetailFragment.NAVIGATION_EDIT_CAROUSEL)
        findNavController().navigate(R.id.action_OverviewFragment_to_DetailFragment, bundle)
    }

    companion object {
        const val TAG = "overview fragment"
    }


    override fun fabClicked() {
        if (this.isVisible) {
            val bundle =
                bundleOf(CarouselDetailFragment.NAVIGATION_TYPE to CarouselDetailFragment.NAVIGATION_NEW_CAROUSEL)
            findNavController().navigate(R.id.action_OverviewFragment_to_DetailFragment, bundle)
        }
    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).setFABClickListener(this)
        super.onAttach(context)
    }
}