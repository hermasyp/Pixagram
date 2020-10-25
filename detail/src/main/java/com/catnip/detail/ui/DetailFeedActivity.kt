package com.catnip.detail.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.request.ImageRequest
import coil.request.ImageResult
import coil.transform.CircleCropTransformation
import com.catnip.core.domain.viewparam.FeedViewParam
import com.catnip.core.utils.TextUtils
import com.catnip.detail.R
import com.igreenwood.loupe.Loupe
import kotlinx.android.synthetic.main.activity_detail_feed.*

class DetailFeedActivity : AppCompatActivity() {
    companion object {
        private const val ARG_DATA = "ARG_DATA"
        @JvmStatic
        fun startThisActivity(context: Context?, feed: FeedViewParam) {
            context?.startActivity(Intent(context, DetailFeedActivity::class.java).apply {
                putExtra(ARG_DATA, feed)
            })
        }
    }

    private val data : FeedViewParam? by lazy {
        intent?.getParcelableExtra<FeedViewParam>(ARG_DATA)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_feed)
        loadData()
    }

    private fun loadData(){
        image?.load(data?.webformatURL) {
            crossfade(true)
            listener(object  : ImageRequest.Listener{
                override fun onSuccess(request: ImageRequest, metadata: ImageResult.Metadata) {
                    super.onSuccess(request, metadata)
                    Loupe.create(image, container) {
                        useFlingToDismissGesture = false
                        onViewTranslateListener = object : Loupe.OnViewTranslateListener {

                            override fun onStart(view: ImageView) {
                                cl_header?.visibility = View.GONE
                            }

                            override fun onViewTranslate(view: ImageView, amount: Float) {}

                            override fun onRestore(view: ImageView) {
                                cl_header?.visibility = View.VISIBLE
                            }

                            override fun onDismiss(view: ImageView) {
                                finish()
                            }
                        }
                    }
                }
            })
            placeholder(com.catnip.core.R.drawable.placeholder_img)
            error(com.catnip.core.R.drawable.placeholder_img)
        }
        iv_profile_icon.load(data?.userImageURL) {
            crossfade(true)
            placeholder(com.catnip.core.R.drawable.ic_profile_placeholder)
            error(com.catnip.core.R.drawable.ic_profile_placeholder)
            transformations(CircleCropTransformation())
        }
        tv_feed_uploader_name.text = data?.user
        tv_feed_total_views.text = String.format("%s %s", data?.views, "Views")
        data?.tags?.let {
            tv_feed_hashtags.text = TextUtils.getTags(it)
        }
    }
}