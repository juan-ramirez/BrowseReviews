package com.juan.browsereviews.utils

import com.juan.browsereviews.model.BaseResponse
import com.juan.browsereviews.model.Review
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GetYourGuideApi {
    /**
     * Get the list of reviews
     */
    @Headers("User-Agent: GetYourGuide")
    @GET("reviews.json?")
    fun getReviews(@Query("count") count: Int,
                   @Query("page") page: Int,
                   @Query("sortBy") sortBy: String,
                   @Query("direction") direction: String): Observable<BaseResponse>

}