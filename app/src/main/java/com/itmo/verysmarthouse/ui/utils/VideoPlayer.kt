package com.itmo.verysmarthouse.ui.utils

import android.widget.MediaController
import android.widget.VideoView
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun VideoPlayer(url: String) {
    AndroidView(
        factory = { context ->
            VideoView(context).apply {
                setVideoPath(url)
                val mediaController = MediaController(context)
                mediaController.setAnchorView(this)
                setMediaController(mediaController)
                requestFocus()
                start()
            }
        },
        update = { videoView ->
            // Here you can add code to be run every time the composable recomposes.
            // For example, if the url changes you might want to start playing a new video.
            videoView.setVideoPath(url)
            videoView.start()
        } ,
        modifier = Modifier.requiredSizeIn(maxHeight = 100.dp),
    )
}