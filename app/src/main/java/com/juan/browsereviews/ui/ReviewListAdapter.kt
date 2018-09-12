package com.juan.browsereviews.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.juan.browsereviews.R
import com.juan.browsereviews.model.Review
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewListAdapter : RecyclerView.Adapter<ReviewListAdapter.ViewHolder>() {
    private lateinit var reviewList: List<Review>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (::reviewList.isInitialized) reviewList.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(reviewList.get(position))
    }

    fun updateReviewList(updatedReviewList: List<Review>) {
        this.reviewList = updatedReviewList
        notifyDataSetChanged()
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(review: Review) {
            view.reviwer_name.text = review.author
            view.review_title.text = review.title
            view.review_date.text = review.date
            view.review_rating.rating = review.rating.toFloat()
            view.review_message.text = review.message
        }
    }
}