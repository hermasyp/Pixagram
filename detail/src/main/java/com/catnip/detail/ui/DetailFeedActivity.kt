package com.catnip.detail.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.catnip.detail.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFeedActivity : AppCompatActivity() {
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_feed)
    }
}