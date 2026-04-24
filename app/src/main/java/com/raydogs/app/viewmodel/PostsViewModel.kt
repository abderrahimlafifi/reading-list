package com.raydogs.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raydogs.app.api.ApiClient
import com.raydogs.app.model.Post
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<Post>>(emptyList())
    val posts: LiveData<List<Post>> = _posts

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>(null)
    val error: LiveData<String?> = _error

    private var currentPage = 0
    private var isLoadingMore = false
    private var hasMore = true
    private var categoryId: Int? = null
    private var initialized = false

    fun init(category: Int?) {
        if (initialized && categoryId == category) return
        categoryId = category
        initialized = true
        reset()
        loadMore()
    }

    fun refresh() {
        reset()
        loadMore()
    }

    fun loadMore() {
        if (isLoadingMore || !hasMore) return
        isLoadingMore = true
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val page = currentPage + 1
                val newPosts = ApiClient.api.getPosts(page = page, categoryId = categoryId)
                currentPage = page
                if (newPosts.size < 10) hasMore = false
                _posts.value = (_posts.value ?: emptyList()) + newPosts
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Couldn't load posts. Check your connection."
            } finally {
                isLoadingMore = false
                _isLoading.value = false
            }
        }
    }

    private fun reset() {
        currentPage = 0
        isLoadingMore = false
        hasMore = true
        _posts.value = emptyList()
        _error.value = null
    }
}
