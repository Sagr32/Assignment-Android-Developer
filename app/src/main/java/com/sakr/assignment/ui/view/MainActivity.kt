package com.sakr.assignment.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sakr.assignment.R
import com.sakr.assignment.data.models.NewsResponse
import com.sakr.assignment.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    lateinit var textView2: TextView

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)


        lifecycleScope.launchWhenStarted {
            viewModel.sources.collect { event ->
                when (event) {
                    is MainViewModel.SourceEvent.Success -> {
                        textView.text = event.result.sources.size.toString()
                        Timber.e("onCreateView: " + event.result.sources.size)
                    }
                    is MainViewModel.SourceEvent.Failure -> {
                        textView.text = event.errorText
                        Timber.tag("Failure").e("onCreateView: " + event.errorText)

                    }
                    is MainViewModel.SourceEvent.Loading -> {
                        textView.text = "Loading"
                        Timber.tag("Loading").e("onCreateView: Loading")

                    }
                    else -> {}
                }
            }
        }



        lifecycleScope.launchWhenStarted {
            viewModel.headlines.collect { event ->
                when (event) {
                    is MainViewModel.NewsEvent.Success -> {
                        textView2.text = event.result.articles.size.toString()
                        Timber.tag("Success").e("onCreateView: %s", event.result.articles.size)
                    }
                    is MainViewModel.NewsEvent.Failure -> {
                        textView2.text = event.errorText
                        Timber.tag("Failure").e("onCreateView: %s", event.errorText)

                    }
                    is MainViewModel.NewsEvent.Loading -> {
                        textView2.text = "Loading"
                        Timber.tag("Loading").e("onCreateView: Loading")

                    }
                    else -> {}
                }
            }
        }


    }
}