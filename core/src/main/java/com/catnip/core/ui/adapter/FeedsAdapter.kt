package com.catnip.core.ui.adapter

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.catnip.core.R
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.utils.TextUtils
import kotlinx.android.synthetic.main.item_feed.view.*
import java.util.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsAdapter(
    private val callback: FeedsAdapterClickListener?,
    private val isHasFavoriteFeature: Boolean = true
) :
    RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>() {

    private var data: List<FeedViewParam> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        return FeedsViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_feed, parent, false),
            callback,
            isHasFavoriteFeature
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) =
        holder.bind(data[position], position)

    fun swapData(data: List<FeedViewParam>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FeedsViewHolder(
        itemView: View,
        private val callback: FeedsAdapterClickListener?,
        private val isHasFavoriteFeature: Boolean
    ) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(item: FeedViewParam, position: Int) = with(itemView) {
            iv_profile_icon.load(item.userImageURL) {
                crossfade(true)
                placeholder(R.drawable.ic_profile_placeholder)
                error(R.drawable.ic_profile_placeholder)
                transformations(CircleCropTransformation())
            }
            iv_feed_image.load(item.webformatURL) {
                crossfade(true)
                placeholder(R.drawable.placeholder_img)
                error(R.drawable.placeholder_img)
            }
            val nameTagSpannable =
                SpannableStringBuilder(item.user.plus(" " + TextUtils.getTags(item.tags)))
            nameTagSpannable.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(itemView.context, R.color.md_grey_300)),
                0,
                item.user.length,
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
            )
            tv_feed_uploader_name.text = item.user
            tv_feed_total_views.text = String.format("%s %s", item.views, "Views")
            tv_feed_hashtags.text = TextUtils.getTags(item.tags)
            tv_feed_hashtags.text = nameTagSpannable
            if (isHasFavoriteFeature) {
                iv_favorite_feed.visibility = View.VISIBLE
                iv_favorite_feed.setImageDrawable(
                    ContextCompat.getDrawable(
                        itemView.context,
                        if (item.isFavorite) R.drawable.ic_favorite_true
                        else R.drawable.ic_favorite_false
                    )
                )
                iv_favorite_feed.setOnClickListener {
                    callback?.onFavoriteIconClicked(item, position)
                }
            }else{
                iv_favorite_feed.visibility = View.GONE
            }

            iv_feed_image.setOnClickListener {
                callback?.onItemClicked(item, position)
            }
            iv_share_img.setOnClickListener {
                callback?.onShareClicked(item, position)
            }
        }
    }


    interface FeedsAdapterClickListener {
        fun onItemClicked(feed: FeedViewParam, position: Int)
        fun onFavoriteIconClicked(feed: FeedViewParam, position: Int)
        fun onShareClicked(feed: FeedViewParam, position: Int)
    }
}