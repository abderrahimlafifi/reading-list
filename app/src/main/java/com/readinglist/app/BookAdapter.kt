package com.readinglist.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BookAdapter(private val sections: List<Section>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items: List<Any> = buildFlatList(sections)

    private fun buildFlatList(sections: List<Section>): List<Any> {
        val list = mutableListOf<Any>()
        for (section in sections) {
            list.add(section.title)
            list.addAll(section.books)
        }
        return list
    }

    override fun getItemViewType(position: Int): Int =
        if (items[position] is String) VIEW_TYPE_HEADER else VIEW_TYPE_BOOK

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == VIEW_TYPE_HEADER) {
            val view = inflater.inflate(R.layout.item_section_header, parent, false)
            HeaderViewHolder(view)
        } else {
            val view = inflater.inflate(R.layout.item_book, parent, false)
            BookViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is HeaderViewHolder -> holder.bind(items[position] as String)
            is BookViewHolder -> holder.bind(items[position] as Book)
        }
    }

    override fun getItemCount() = items.size

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.section_title)
        fun bind(header: String) { title.text = header }
    }

    class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: TextView = view.findViewById(R.id.book_title)
        private val author: TextView = view.findViewById(R.id.book_author)
        private val status: TextView = view.findViewById(R.id.book_status)

        fun bind(book: Book) {
            title.text = book.title
            author.text = book.author
            val (label, color) = when (book.status) {
                BookStatus.COMPLETE -> "✓ Read" to 0xFF2E7D32.toInt()
                BookStatus.IN_PROGRESS -> "• Reading" to 0xFFE65100.toInt()
                BookStatus.UNREAD -> "◦ Queue" to 0xFF757575.toInt()
            }
            status.text = label
            status.setTextColor(color)
        }
    }

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_BOOK = 1
    }
}
