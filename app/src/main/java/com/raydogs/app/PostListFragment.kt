package com.raydogs.app

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.raydogs.app.adapter.PostAdapter
import com.raydogs.app.viewmodel.PostsViewModel

class PostListFragment : Fragment() {

    private val viewModel: PostsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_post_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryId = arguments?.getInt(ARG_CATEGORY, -1)?.takeIf { it != -1 }

        val swipeRefresh = view.findViewById<SwipeRefreshLayout>(R.id.swipe_refresh)
        val recyclerView  = view.findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar   = view.findViewById<ProgressBar>(R.id.progress_bar)
        val errorText     = view.findViewById<TextView>(R.id.error_text)

        val adapter = PostAdapter { post ->
            startActivity(Intent(requireContext(), ArticleActivity::class.java).apply {
                putExtra(ArticleActivity.EXTRA_URL, post.link)
                putExtra(ArticleActivity.EXTRA_TITLE, post.title.rendered)
            })
        }

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                val lm = rv.layoutManager as LinearLayoutManager
                if (lm.findLastVisibleItemPosition() >= adapter.itemCount - 3) viewModel.loadMore()
            }
        })

        swipeRefresh.setOnRefreshListener { viewModel.refresh() }

        viewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
            errorText.visibility = View.GONE
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { loading ->
            progressBar.visibility = if (loading && adapter.itemCount == 0) View.VISIBLE else View.GONE
            if (!loading) swipeRefresh.isRefreshing = false
        }
        viewModel.error.observe(viewLifecycleOwner) { err ->
            if (err != null && adapter.itemCount == 0) {
                errorText.text = err
                errorText.visibility = View.VISIBLE
            }
        }

        viewModel.init(categoryId)
    }

    companion object {
        private const val ARG_CATEGORY = "categoryId"
        fun newInstance(categoryId: Int?) = PostListFragment().apply {
            arguments = Bundle().apply { categoryId?.let { putInt(ARG_CATEGORY, it) } }
        }
    }
}
