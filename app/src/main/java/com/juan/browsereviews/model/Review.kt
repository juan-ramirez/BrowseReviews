package com.juan.browsereviews.model

import com.google.gson.JsonObject

data class Review(
        val review_id: Int,
        val rating: String,
        val title: String,
        val message: String,
        val author: String,
        val foreignLanguage: Boolean,
        val date: String,
        val languageCode: String,
        val traveler_type: String,
        val reviewerName: String,
        val reviewerCountry: String
)