package com.catnip.favorite.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.catnip.core.domain.model.Feed
import com.catnip.core.ui.adapter.FeedsAdapter
import com.catnip.favorite.R
import com.catnip.favorite.di.favoriteModule
import kotlinx.android.synthetic.main.fragment_favorite_feeds.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

class FavoriteFeedsFragment : Fragment() {


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
            setupList()
            observeData()
        }
    }

    private fun observeData() {
        favoriteFeedsViewModel.favoritedFeeds.observe(viewLifecycleOwner, Observer { feeds ->
            feedsAdapter.swapData(feeds)
            progress_bar.visibility = if (feeds.isNotEmpty()) View.GONE else View.VISIBLE
        })
    }

    private fun setupList() {
        feedsAdapter = FeedsAdapter(object : FeedsAdapter.FeedsAdapterClickListener {
            override fun onItemClicked(feed: Feed, position: Int) {
                Toast.makeText(context, feed.id, Toast.LENGTH_SHORT).show()
            }

            override fun onFavoriteIconClicked(feed: Feed, position: Int) {
                setFeedsToFavorite(feed, !feed.isFavorite)
            }
        })
        with(rv_feeds) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            this.adapter = feedsAdapter
        }
    }

    private fun setFeedsToFavorite(feed: Feed, isFavorite: Boolean) {
        favoriteFeedsViewModel.setFavoriteFeed(feed, isFavorite)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(favoriteModule)

    }
}