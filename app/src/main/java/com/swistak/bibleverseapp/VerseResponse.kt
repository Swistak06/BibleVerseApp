package com.swistak.bibleverseapp

data class VerseResponse(
    val reference: String,
    val text: String,
    val translation_id: String,
    val translation_name: String,
    val translation_note: String,
    val verses: List<Verse>
)