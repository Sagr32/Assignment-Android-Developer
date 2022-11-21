package com.sakr.assignment.ui.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.sakr.assignment.databinding.FragmentHomeBinding
import com.sakr.assignment.ui.adapter.HeadlineNewsAdapter
import com.sakr.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorText: TextView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        recyclerView = binding.newsList
        shimmer = binding.shimmerViewContainer
        errorText = binding.txtError

        lifecycleScope.launchWhenStarted {
            viewModel.headlines.collect { event ->
                Timber.tag("event").d("${event.toString()}")
                when (event) {
                    is MainViewModel.NewsEvent.Success -> {
                        Timber.tag("Success").d("Success")
                        //recyclerview adapter here
                        shimmer.visibility = View.GONE
                        errorText.visibility = View.GONE
                        val adapter =
                            HeadlineNewsAdapter(
                                event.result.articles,
                                HeadlineNewsAdapter.OnClickListener { article ->
//                                    this.findNavController().navigate(
//                                        HomeFragmentDirections.actionHomeFragmentToDetailsFragment(
//                                            article
//                                        )
//                                    )

                                })
                        recyclerView.adapter = adapter

                    }
                    is MainViewModel.NewsEvent.Failure -> {
                        Timber.tag("Failure").d("Failure")
                        shimmer.visibility = View.GONE
                        errorText.visibility = View.VISIBLE
                        errorText.text = event.errorText

                    }
                    is MainViewModel.NewsEvent.Loading -> {
                        Timber.tag("Loading").d("Loading")
                        shimmer.visibility = View.VISIBLE
                        errorText.visibility = View.GONE

                    }
                    else -> {
                        Timber.tag("else").d("else")
                    }
                }
            }
        }


        // Inflate the layout for this fragment
        return binding.root
    }

}