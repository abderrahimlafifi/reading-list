package com.raydogs.app.api

import com.raydogs.app.model.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface WordPressApi {
    @GET("wp/v2/posts")
    suspend fun getPosts(
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int = 1,
        @Query("categories") categoryId: Int? = null,
        @Query("_embed") embed: Int = 1
    ): List<Post>
}
