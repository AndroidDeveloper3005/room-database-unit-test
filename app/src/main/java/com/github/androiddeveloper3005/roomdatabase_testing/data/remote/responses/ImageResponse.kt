package com.androiddevs.shoppinglisttestingyt.data.remote.responses

import com.github.androiddeveloper3005.roomdatabase_testing.data.remote.responses.ImageResult


data class ImageResponse(
    val hits: List<ImageResult>,
    val total: Int,
    val totalHits: Int
)