package com.sakr.assignment.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sakr.assignment.R
import java.text.ParseException
import java.text.SimpleDateFormat


@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imgView.context)
            .load(imgUrl)
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image)
            .into(imgView)
    }
}

@BindingAdapter("formatDate")
fun TextView.setDate(date: String) {
    val outputDate: String?
    try {
        val curFormater = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss")
        val postFormater = SimpleDateFormat("MMM dd, yyyy")

        val dateObj = curFormater.parse(date)
        outputDate = postFormater.format(dateObj)
        this.text = (outputDate)

    } catch (e: ParseException) {
        e.printStackTrace()
    }
}

