package com.sakr.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sakr.assignment.databinding.FragmentArticleDetailsBinding

class ArticleDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailsBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding.root
    }
}