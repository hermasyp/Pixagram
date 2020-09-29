package com.catnip.pixagram.feature.detailfeeds

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.pixagram.R
import com.catnip.pixagram.feature.feeds.FeedsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFeedActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_feed)
    }
}