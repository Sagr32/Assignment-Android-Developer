package com.sakr.assignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.sakr.assignment.databinding.FragmentArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {
    private val args: ArticleDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailsBinding.inflate(inflater)
        binding.article = args.article
        // Inflate the layout for this fragment
        return binding.root
    }
}