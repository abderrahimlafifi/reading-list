package com.readinglist.app

enum class BookStatus { COMPLETE, IN_PROGRESS, UNREAD }

data class Book(
    val title: String,
    val author: String,
    val status: BookStatus = BookStatus.UNREAD
)

data class Section(val title: String, val books: List<Book>)
