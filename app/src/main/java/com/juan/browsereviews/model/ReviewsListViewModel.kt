package com.juan.browsereviews.model

import com.juan.browsereviews.ui.ReviewListAdapter
import com.juan.browsereviews.utils.GetYourGuideApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ReviewsListViewModel : BaseViewModel() {
    @Inject
    lateinit var getYourGuideApi: GetYourGuideApi
    var reviewListAdapter: ReviewListAdapter = ReviewListAdapter()
    var success: Boolean = true;

    /*purely optional as we might need to keep this observable, this might be replaced by a Kotlin coroutine
    (too much impl might be needed for this particular example tho)
     */
    private lateinit var disposableObserver: Disposable

    override fun onCleared() {
        super.onCleared()
        disposableObserver.dispose()
    }

    init {
        getReviews()
    }

    fun getReviews(sortBy: String = "date_of_review", direction: String = "DESC") {
        disposableObserver = getYourGuideApi.getReviews(0, 0, sortBy, direction)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { reviewListResult -> onRetrieveReviewsList(reviewListResult.data) }
                )
    }

    private fun onRetrieveReviewsList(reviewListResult: List<Review>) {
        reviewListAdapter.updateReviewList(reviewListResult)
        success = true
    }
}