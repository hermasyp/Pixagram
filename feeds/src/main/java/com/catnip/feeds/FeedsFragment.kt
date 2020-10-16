package com.catnip.feeds

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
import com.catnip.core.utils.ShareUtils
import kotlinx.android.synthetic.main.fragment_feeds.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedsFragment : Fragment() {


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
            setupList()
            observeData()
        }
    }

    private fun observeData() {
        feedsViewModel.feeds.observe(viewLifecycleOwner, Observer { feeds ->
            if (feeds != null) {
                when (feeds) {
                    is Resource.Loading -> progress_bar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        progress_bar.visibility = View.GONE
                        feedsAdapter.swapData(feeds.data!!)
                    }
                    is Resource.Error -> {
                        progress_bar.visibility = View.GONE
                        Toast.makeText(context, "Error While Load Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        })
    }

    private fun setupList() {
        feedsAdapter = FeedsAdapter(object : FeedsAdapter.FeedsAdapterClickListener {
            override fun onItemClicked(feed: FeedViewParam, position: Int) {
                Toast.makeText(context, feed.id, Toast.LENGTH_SHORT).show()
            }

            override fun onFavoriteIconClicked(feed: FeedViewParam, position: Int) {
                setFeedsToFavorite(feed,!feed.isFavorite)
            }

            override fun onShareClicked(feed: FeedViewParam, position: Int) {
                ShareUtils.sendLink(context,feed.largeImageURL)
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
}