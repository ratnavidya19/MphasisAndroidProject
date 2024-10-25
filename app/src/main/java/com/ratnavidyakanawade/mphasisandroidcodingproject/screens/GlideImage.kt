package com.ratnavidyakanawade.mphasisandroidcodingproject.screens

import android.widget.ImageView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.bumptech.glide.Glide

@Composable
fun GlideImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier
) {
    AndroidView(
        factory = { context ->
            ImageView(context).apply {
                contentDescription?.let { this.contentDescription = it }
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
        },
        modifier = modifier,
        update = { imageView ->
            Glide.with(imageView.context)
                .load(imageUrl)
                .into(imageView)
        }
    )
}