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
import com.philippschumann.wallpapercarousel.adapter.CarouselAdapter
import com.philippschumann.wallpapercarousel.model.Carousel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var carouselViewModel: CarouselViewModel
    private lateinit var adapter: CarouselAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*  view.findViewById<Button>(R.id.button_first).setOnClickListener {
              findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
          }*/
        val recyclerView: RecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        adapter = CarouselAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)

        carouselViewModel = ViewModelProvider(this).get(CarouselViewModel::class.java)
        carouselViewModel.allCarousels.observe(viewLifecycleOwner, Observer { carousels ->
            carousels?.let { adapter.setCarousels(it) }
        })
    }
}