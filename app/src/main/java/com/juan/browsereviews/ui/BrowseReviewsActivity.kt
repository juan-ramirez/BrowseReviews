package com.juan.browsereviews.ui

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import com.juan.browsereviews.R
import com.juan.browsereviews.model.ReviewsListViewModel
import kotlinx.android.synthetic.main.activity_browse_reviews.*
import kotlinx.android.synthetic.main.content_browse_reviews.*

class BrowseReviewsActivity : AppCompatActivity() {

    lateinit var reviewsListViewModel: ReviewsListViewModel
    var selectedSort: SortBy = SortBy.DATE_OF_REVIEW
    var selectedDirection: SortDirection = SortDirection.DESC
    var dateAsc: Boolean = false
    var ratingAsc: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse_reviews)
        setSupportActionBar(toolbar)

        reviewsListViewModel = ViewModelProviders.of(this).get(ReviewsListViewModel::class.java)
        reviewsRecyclerView.adapter = reviewsListViewModel?.reviewListAdapter
        reviewsRecyclerView.addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))

        swipeRefreshLayout.setOnRefreshListener {
            refreshItems()
        }

        filter_date.setOnClickListener { view ->
            swipeRefreshLayout.isRefreshing = true
            selectedSort = SortBy.DATE_OF_REVIEW
            selectedDirection = if (dateAsc) SortDirection.ASC else SortDirection.DESC
            refreshItems()
            dateAsc = !dateAsc
        }

        filter_rating.setOnClickListener { view ->
            swipeRefreshLayout.isRefreshing = true
            selectedSort = SortBy.RATING
            selectedDirection = if (ratingAsc) SortDirection.ASC else SortDirection.DESC
            refreshItems()
            ratingAsc = !ratingAsc
        }
    }

    fun refreshItems() {
        reviewsListViewModel.getReviews(selectedSort.sort, selectedDirection.direction)
        if (!reviewsListViewModel.success) {
            Snackbar.make(coordinator_layout, "Something failed, probably ghosts or something, wanna swipe to refresh and try again?", Snackbar.LENGTH_LONG).show();
        }
        swipeRefreshLayout.isRefreshing = false
    }

    enum class SortBy(val sort: String) {
        DATE_OF_REVIEW("date_of_review"),
        RATING("rating")
    }

    enum class SortDirection(val direction: String) {
        ASC("ASC"),
        DESC("DESC")
    }
}
