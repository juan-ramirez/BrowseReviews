package com.juan.browsereviews.model

data class BaseResponse (
    val status: Boolean,
    val totalReviews: Int,
    val data: List<Review>
)