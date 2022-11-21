package com.sakr.assignment.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.sakr.assignment.R
import com.sakr.assignment.databinding.FragmentArticleDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleDetailsFragment : Fragment() {
    lateinit var progressBar: ProgressBar
    private val args: ArticleDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentArticleDetailsBinding.inflate(inflater)
        binding.article = args.article
        // Inflate the layout for this fragment
        binding.openUrlBtn.setOnClickListener {
            showBottomSheetDialog(args.article.url)
        }
        return binding.root
    }


    private fun showBottomSheetDialog(url: String) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        bottomSheetDialog.setContentView(R.layout.webview_bottom_sheet)
        val webView = bottomSheetDialog.findViewById<WebView>(R.id.webView)
        progressBar = bottomSheetDialog.findViewById<ProgressBar>(R.id.progressBar)!!
        bottomSheetDialog.show()
        webView?.webViewClient = MyWebViewClient()
        webView?.settings?.setJavaScriptEnabled(true)
        webView?.loadUrl(url)

    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
        }
    }
}