package com.raydogs.app.model

import com.google.gson.annotations.SerializedName

data class Post(
    val id: Int,
    val date: String,
    val title: Title,
    val excerpt: Excerpt,
    val slug: String,
    val link: String,
    val categories: List<Int>,
    @SerializedName("featured_media") val featuredMedia: Int,
    @SerializedName("_embedded") val embedded: Embedded? = null
)

data class Title(val rendered: String)
data class Excerpt(val rendered: String)

data class Embedded(
    @SerializedName("wp:featuredmedia") val featuredMedia: List<FeaturedMedia>?
)

data class FeaturedMedia(
    @SerializedName("source_url") val sourceUrl: String,
    @SerializedName("media_details") val mediaDetails: MediaDetails?
)

data class MediaDetails(val sizes: Map<String, ImageSize>?)

data class ImageSize(
    @SerializedName("source_url") val sourceUrl: String
)
