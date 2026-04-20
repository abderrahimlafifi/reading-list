package com.readinglist.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BooksFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val recyclerView = RecyclerView(requireContext())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val sections = when (arguments?.getInt(ARG_LIST)) {
            LIST_2018 -> BookData.books2018
            LIST_2019 -> BookData.books2019
            else -> BookData.booksQueue
        }
        recyclerView.adapter = BookAdapter(sections)
        recyclerView.setPadding(0, 8, 0, 8)
        recyclerView.clipToPadding = false
        return recyclerView
    }

    companion object {
        private const val ARG_LIST = "list"
        const val LIST_2018 = 0
        const val LIST_2019 = 1
        const val LIST_QUEUE = 2

        fun newInstance(list: Int) = BooksFragment().apply {
            arguments = Bundle().apply { putInt(ARG_LIST, list) }
        }
    }
}
