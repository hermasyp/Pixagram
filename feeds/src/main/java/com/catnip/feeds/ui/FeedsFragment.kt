package com.catnip.feeds.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.ui.adapter.FeedsAdapter
import com.catnip.core.ui.widget.ContentStateView
import com.catnip.core.utils.ShareUtils
import com.catnip.feeds.R
import kotlinx.android.synthetic.main.fragment_feeds.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedsFragment : Fragment(), ContentStateView.ContentStateViewListener {


    private val feedsViewModel: FeedsViewModel by viewModel()
    private lateinit var feedsAdapter: FeedsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feeds, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setupView()
            setupList()
            observeData()
        }
    }

    private fun observeData() {
        feedsViewModel.feeds.observe(viewLifecycleOwner, Observer { feeds ->
            if (feeds != null) {
                when (feeds) {
                    is Resource.Loading -> csv_feeds?.setState(ContentStateView.STATE_LOADING)
                    is Resource.Success -> {
                        if (!feeds.data.isNullOrEmpty()) {
                            csv_feeds?.setState(ContentStateView.STATE_NORMAL)
                            feeds.data?.let {
                                feedsAdapter.swapData(it)
                            }
                        } else {
                            csv_feeds?.setState(ContentStateView.STATE_EMPTY)
                        }
                    }
                    is Resource.Error -> {
                        csv_feeds?.setState(ContentStateView.STATE_ERROR)
                    }
                }
            }
        })
    }

    private fun setupView() {
        csv_feeds?.setListener(this)
        csv_feeds?.useDefaultConfig()
    }

    private fun setupList() {
        feedsAdapter = FeedsAdapter(object : FeedsAdapter.FeedsAdapterClickListener {
            override fun onItemClicked(feed: FeedViewParam, position: Int) {
                //todo : to detail
                Toast.makeText(context, feed.id.toString(), Toast.LENGTH_SHORT).show()
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
        feedsViewModel.setFavoriteFeed(feed, isFavorite)
    }

    override fun onStateChanged(isContentVisible: Boolean, state: Int) {
        rv_feeds?.visibility = if (isContentVisible) View.VISIBLE else View.GONE
    }
}