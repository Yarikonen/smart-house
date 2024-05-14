package com.itmo.verysmarthouse.ui.components.templates
import com.itmo.verysmarthouse.data.enum.DeviceType

import android.os.Build
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.itmo.verysmarthouse.R

@Composable
fun DeviceTemplateComponent(
    deviceType: DeviceType,
    initialStatus: Boolean,
    image: Painter,
    name: String,
    content: @Composable () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .fillMaxHeight(),
        border = BorderStroke(1.dp,Color.Black),
        shape = AbsoluteCutCornerShape(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF8F7B2D)),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            DeleteTemplateComponent(
                onClick = {
                    onDelete()
                }
            )
            val context = LocalContext.current
            val imageLoader = ImageLoader.Builder(context)
                .components {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder.Factory())
                    } else {
                        add(GifDecoder.Factory())
                    }
                }
                .build()


            if (initialStatus) {
                when (deviceType) {
                    DeviceType.VIDEO_CAMERA -> Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(context).data(data = R.drawable.tv_gif)
                            .apply(block = {
                                size(Size.ORIGINAL)
                            }).build(), imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    modifier = Modifier.requiredSizeIn(maxHeight = 100.dp),
                )

                    else -> Image(
                        painter = image,
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(64.dp)
                            .padding(8.dp)
                    )
                }
            } else {
                Image(
                    painter = image,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .padding(8.dp)
                )
            }

            Text(
                text = name,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.Start,
        ) {
            content()
        }
    }
}

