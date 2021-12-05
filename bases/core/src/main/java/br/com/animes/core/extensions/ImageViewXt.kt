package br.com.animes.core.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(imageUrl: String) {
    Glide.with(this.context).load(imageUrl).into(this)
}