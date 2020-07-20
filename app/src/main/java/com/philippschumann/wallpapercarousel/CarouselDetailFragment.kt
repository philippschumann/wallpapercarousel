package com.philippschumann.wallpapercarousel

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.philippschumann.wallpapercarousel.adapter.CarouselDetailAdapter
import com.philippschumann.wallpapercarousel.database.model.Carousel
import com.philippschumann.wallpapercarousel.database.model.CarouselWithImages

class CarouselDetailFragment : Fragment(), MainActivity.FABClickedListener {
    private val sharedViewModel: SharedViewModel by activityViewModels()
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
        if (arguments?.getString(NAVIGATION_TYPE) == NAVIGATION_EDIT_CAROUSEL) {
            sharedViewModel.selected.observe(
                viewLifecycleOwner,
                Observer<CarouselWithImages> { carousel ->
                    Log.d(TAG, "selected carousel: " + carousel.carousel.carouselId)

                    Log.d(TAG, "edit carousel")
                    detailAdapter.setCarousel(carousel)

                })
        } else if (arguments?.getString(NAVIGATION_TYPE) == NAVIGATION_NEW_CAROUSEL) {
            sharedViewModel.selected.value = CarouselWithImages(Carousel(0), emptyList())
        }
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
                if (arguments?.getString(NAVIGATION_TYPE) == NAVIGATION_NEW_CAROUSEL) {
                    //TODO insert carousel if not empty
                } else if (arguments?.getString(NAVIGATION_TYPE) == NAVIGATION_EDIT_CAROUSEL) {
                    //TODO update carousel or delete if empty
                }
                findNavController().navigate(R.id.action_DetailFragment_to_OverviewFragment)
                Log.d("detail fragment", "navigate up")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TAG: String = "CarouselDetailFragment"
        const val NAVIGATION_TYPE = "navigation_type"
        const val NAVIGATION_NEW_CAROUSEL = "new_carousel"
        const val NAVIGATION_EDIT_CAROUSEL = "edit_carousel"
    }

    override fun onAttach(context: Context) {
        (activity as MainActivity).setFABClickListener(this)
        super.onAttach(context)
    }

    override fun fabClicked() {
        if (this.isVisible) {
            //fab clicked
        }
    }
}