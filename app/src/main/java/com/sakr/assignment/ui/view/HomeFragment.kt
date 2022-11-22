package com.sakr.assignment.ui.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.sakr.assignment.R
import com.sakr.assignment.databinding.FragmentHomeBinding
import com.sakr.assignment.ui.adapter.HeadlineNewsAdapter
import com.sakr.assignment.ui.adapter.SourceNewsAdapter
import com.sakr.assignment.ui.viewmodel.MainViewModel
import com.sakr.assignment.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var sourceRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater)
        newsRecyclerView = binding.newsList
        sourceRecyclerView = binding.sourceList
        shimmer = binding.shimmerViewContainer
        errorText = binding.txtError
        val sharedPref = requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        editor = sharedPref.edit()
        lifecycleScope.launchWhenStarted {
            viewModel.sources.collect { event ->
                Timber.tag("sourceEvent").d("sourceEvent : ${event.toString()}")
                when (event) {
                    is MainViewModel.SourceEvent.Success -> {
                        Timber.tag("Success").d("Success")
                        sourceRecyclerView.visibility = View.VISIBLE
                        //recyclerview adapter here
                        shimmer.visibility = View.GONE
                        errorText.visibility = View.GONE
                        val adapter =
                            SourceNewsAdapter(
                                event.result.sources,
                                SourceNewsAdapter.OnSourceClicked { source ->
                                    viewModel.getHeadlinesWithSources(source.name)
                                })
                        sourceRecyclerView.adapter = adapter

                    }

                    else -> {
                        sourceRecyclerView.visibility = View.GONE

                    }
                }
            }
        }


        // Headlines RecyclerView
        lifecycleScope.launchWhenStarted {
            viewModel.headlines.collect { event ->
                when (event) {
                    is MainViewModel.NewsEvent.Success -> {
                        shimmer.visibility = View.GONE
                        if (event.result.isNotEmpty()) {

                            //recyclerview adapter here
                            newsRecyclerView.visibility = View.VISIBLE

                            errorText.visibility = View.GONE
                            val adapter =
                                HeadlineNewsAdapter(
                                    event.result,
                                    HeadlineNewsAdapter.OnClickListener { article ->
                                        Timber.tag("Article").d(article.toString())

                                        this@HomeFragment.findNavController().navigate(
                                            HomeFragmentDirections.actionHomeFragmentToArticleDetailsFragment(
                                                article
                                            )
                                        )

                                    })
                            newsRecyclerView.adapter = adapter
                        } else {
                            newsRecyclerView.visibility = View.GONE
                            errorText.visibility = View.VISIBLE
                            errorText.text = "Result is empty"
                        }


                    }
                    is MainViewModel.NewsEvent.Failure -> {
                        Timber.tag("Failure").d("Failure")
                        newsRecyclerView.visibility = View.GONE
                        Timber.d(event.errorText)

                        shimmer.visibility = View.GONE


                    }
                    is MainViewModel.NewsEvent.Loading -> {
                        newsRecyclerView.visibility = View.GONE

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.option_menu, menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            editor.apply {
                putBoolean(Constants.USER_LOGGED, false)
                apply()
            }
            this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment())
        }
        return super.onOptionsItemSelected(item)


    }
}