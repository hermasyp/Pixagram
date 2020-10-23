package com.catnip.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.ui.adapter.FeedsAdapter
import com.catnip.core.ui.widget.ContentStateView
import com.catnip.core.ui.widget.ContentStateViewConfig
import com.catnip.core.ui.widget.ContentStateViewMessage
import com.catnip.core.utils.ShareUtils
import com.catnip.detail.ui.DetailFeedActivity
import com.catnip.favorite.R
import com.catnip.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite_feeds.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFeedsFragment : Fragment(), ContentStateView.ContentStateViewListener {


    private val favoriteFeedsViewModel: FavoriteFeedsViewModel by viewModel()
    private lateinit var feedsAdapter: FeedsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        unloadKoinModules(favoriteModule)
        loadKoinModules(favoriteModule)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setupView()
            setupList()
            observeData()
        }
    }

    private fun setupView() {
        csv_feeds?.setListener(this)
        csv_feeds?.useDefaultConfig()
        csv_feeds?.addConfig(
            ContentStateView.STATE_EMPTY, ContentStateViewConfig(
                isShowProgress = false,
                isShowTitle = true,
                isShowSubtitle = true,
                contentStateViewMessage = ContentStateViewMessage(
                    context?.getString(R.string.title_empty_favorite),
                    context?.getString(R.string.msg_empty_favorite)
                )
            )
        )
    }

    override fun onStateChanged(isContentVisible: Boolean, state: Int) {
        rv_feeds?.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }

    private fun observeData() {
        favoriteFeedsViewModel.favoritedFeeds.observe(viewLifecycleOwner, Observer { feeds ->
            if (!feeds.isNullOrEmpty()) {
                csv_feeds?.setState(ContentStateView.STATE_NORMAL)
                feedsAdapter.swapData(feeds)
            } else {
                csv_feeds?.setState(ContentStateView.STATE_EMPTY)
            }
        })
    }

    private fun setupList() {
        feedsAdapter = FeedsAdapter(object : FeedsAdapter.FeedsAdapterClickListener {
            override fun onItemClicked(feed: FeedViewParam, position: Int) {
                DetailFeedActivity.startThisActivity(context,feed)
            }

            override fun onFavoriteIconClicked(feed: FeedViewParam, position: Int) {
                setFeedsToFavorite(feed, !feed.isFavorite)
            }

            override fun onShareClicked(feed: FeedViewParam, position: Int) {
                ShareUtils.sendLink(context, feed.largeImageURL)
            }
        })
        with(rv_feeds) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = feedsAdapter
        }
    }

    private fun setFeedsToFavorite(feed: FeedViewParam, isFavorite: Boolean) {
        favoriteFeedsViewModel.setFavoriteFeed(feed, isFavorite)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)

    }
}