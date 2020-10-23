package com.catnip.search.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.catnip.core.data.source.resource.Resource
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.ui.adapter.FeedsAdapter
import com.catnip.core.ui.widget.ContentStateView
import com.catnip.core.ui.widget.ContentStateViewConfig
import com.catnip.core.ui.widget.ContentStateViewMessage
import com.catnip.core.utils.ShareUtils
import com.catnip.detail.ui.DetailFeedActivity
import com.catnip.search.R
import kotlinx.android.synthetic.main.activity_search_feed.*
import kotlinx.android.synthetic.main.layout_toolbar_search.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class SearchFeedsActivity : AppCompatActivity(), ContentStateView.ContentStateViewListener {
    private val feedsViewModel: SearchFeedsViewModel by viewModel()
    private lateinit var feedsAdapter: FeedsAdapter
    private var lastQuery: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_feed)
        setupView()
        setupList()
    }

    @SuppressLint("InflateParams")
    private fun setupToolbar() {
        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayShowCustomEnabled(true)
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        val customView: View = layoutInflater.inflate(R.layout.layout_toolbar_search,null )
        actionBar?.customView = customView
        val parent: Toolbar = customView.parent as Toolbar
        parent.setContentInsetsAbsolute(0, 0)
        parent.contentInsetStartWithNavigation = 0

        sv_feeds?.isIconified = false
        sv_feeds?.isFocusable = true
        sv_feeds?.clearFocus()
        sv_feeds?.requestFocusFromTouch()
        sv_feeds?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    lastQuery = it
                    search(it)
                    sv_feeds?.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
    private fun setInitialState(){
        csv_feeds?.addConfig(
            ContentStateView.STATE_EMPTY,
            ContentStateViewConfig(
                isShowProgress = false, isShowTitle = true, isShowSubtitle = true,
                contentStateViewMessage = ContentStateViewMessage(
                    getString(R.string.title_empty_initial_search),
                    getString(R.string.msg_empty_initial_search)
                )
            )
        )
        csv_feeds?.setState(ContentStateView.STATE_EMPTY)
    }

    private fun search(query: String) {
        feedsViewModel.getSearchFeeds(query).observe(this, Observer { feeds ->
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
                            csv_feeds?.addConfig(
                                ContentStateView.STATE_EMPTY,
                                ContentStateViewConfig(
                                    isShowProgress = false,
                                    isShowTitle = true,
                                    isShowSubtitle = true,
                                    contentStateViewMessage = ContentStateViewMessage(
                                        getString(R.string.title_empty_result_search).format(lastQuery),
                                        getString(R.string.msg_empty_result_search)
                                    )
                                )
                            )
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
        setupToolbar()
        setInitialState()
        csv_feeds?.setListener(this)
        csv_feeds?.useDefaultConfig()

    }

    private fun setupList() {
        feedsAdapter = FeedsAdapter(object : FeedsAdapter.FeedsAdapterClickListener {
            override fun onItemClicked(feed: FeedViewParam, position: Int) {
                DetailFeedActivity.startThisActivity(this@SearchFeedsActivity, feed)
            }

            override fun onFavoriteIconClicked(feed: FeedViewParam, position: Int) {
                setFeedsToFavorite(feed, !feed.isFavorite)
            }

            override fun onShareClicked(feed: FeedViewParam, position: Int) {
                ShareUtils.sendLink(this@SearchFeedsActivity, feed.largeImageURL)
            }
        }, false)
        with(rv_feeds) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
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