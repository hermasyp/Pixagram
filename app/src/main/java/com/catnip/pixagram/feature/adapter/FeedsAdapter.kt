package com.catnip.pixagram.feature.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.catnip.core.domain.model.Feed
import com.catnip.core.utils.TextUtils
import com.catnip.pixagram.R
import kotlinx.android.synthetic.main.item_feed.view.*
import java.util.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class FeedsAdapter : RecyclerView.Adapter<FeedsAdapter.FeedsViewHolder>() {

    private var data: List<Feed> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedsViewHolder {
        return FeedsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_feed, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: FeedsViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Feed>) {
        this.data = data
        notifyDataSetChanged()
    }

    class FeedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Feed) = with(itemView) {
            // TODO: Bind the data with View
            iv_profile_icon.load(item.userImageURL) {
                crossfade(true)
                placeholder(R.drawable.ic_profile_placeholder)
                transformations(CircleCropTransformation())
            }
            iv_feed_image.load(item.largeImageURL) {
                crossfade(true)
                placeholder(R.drawable.placeholder_img)
            }
            tv_feed_uploader_name.text = item.user
            tv_feed_total_views.text = String.format("%s %s", item.views, "Views")
            tv_feed_hashtags.text = TextUtils.getTags(item.tags)
            setOnClickListener {

            }
        }
    }
}