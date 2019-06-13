package com.swistak.bibleverseapp

data class Verse(
    val book_id: String,
    val book_name: String,
    val chapter: Int,
    val text: String,
    val verse: Int
)