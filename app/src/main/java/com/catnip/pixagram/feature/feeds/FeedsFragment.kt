package com.catnip.pixagram.feature.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.catnip.pixagram.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class FeedsFragment : Fragment() {


    private val feedsViewModel: FeedsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
}