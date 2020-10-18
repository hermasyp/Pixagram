package com.catnip.core.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.catnip.core.R
import kotlinx.android.synthetic.main.widget_content_state_view.view.*

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
class ContentStateView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var configs: HashMap<Int, ContentStateViewConfig?> = hashMapOf()

    private var state = STATE_NORMAL

    private var listener: ContentStateViewListener? = null

    init {
        inflate(context, R.layout.widget_content_state_view, this)

    }

    companion object {
        const val STATE_NORMAL = 0
        const val STATE_LOADING = 1
        const val STATE_EMPTY = 2
        const val STATE_ERROR = 3
    }

    fun setListener(listener: ContentStateViewListener) {
        this.listener = listener
    }

    fun setState(state: Int) {
        this.state = state
        when (state) {
            STATE_LOADING -> {
                listener?.onStateChanged(false, state)
                setStateView(configs[state])
            }
            STATE_EMPTY -> {
                listener?.onStateChanged(false, state)
                setStateView(configs[state])
            }
            STATE_ERROR -> {
                listener?.onStateChanged(false, state)
                setStateView(configs[state])
            }
            else -> {
                listener?.onStateChanged(true, state)
                setStateView(configs[state])
            }
        }
    }

    fun addConfig(state: Int, config: ContentStateViewConfig?) {
        configs[state] = config
    }

    private fun setStateView(config: ContentStateViewConfig?) {
        if (config != null) {
            this.visibility = View.VISIBLE
            setVisibilityState(config)
            setContentMessage(config)
        } else {
            this.visibility = View.GONE
        }
    }

    private fun setVisibilityState(config: ContentStateViewConfig) {
        pb_csv?.visibility = if (config.isShowProgress) View.VISIBLE else View.GONE
        tv_title_csv?.visibility = if (config.isShowTitle) View.VISIBLE else View.GONE
        tv_message_csv?.visibility = if (config.isShowSubtitle) View.VISIBLE else View.GONE
    }

    private fun setContentMessage(config: ContentStateViewConfig) {
        if (!config.contentStateViewMessage?.title.isNullOrEmpty()) {
            tv_title_csv.text = config.contentStateViewMessage?.title.orEmpty()
        } else {
            tv_title_csv.visibility = View.GONE
        }
        if (!config.contentStateViewMessage?.message.isNullOrEmpty()) {
            tv_message_csv.text = config.contentStateViewMessage?.message.orEmpty()
        } else {
            tv_message_csv.visibility = View.GONE
        }
    }


    fun useDefaultConfig() {
        configs[STATE_NORMAL] = null
        configs[STATE_LOADING] = ContentStateViewConfig(true)
        configs[STATE_EMPTY] = ContentStateViewConfig(
            isShowProgress = false,
            isShowTitle = true,
            isShowSubtitle = false,
            contentStateViewMessage = ContentStateViewMessage(
                context.getString(R.string.default_title_empty_state)
            )
        )
        configs[STATE_ERROR] = ContentStateViewConfig(
            isShowProgress = false,
            isShowTitle = true,
            isShowSubtitle = true,
            contentStateViewMessage = ContentStateViewMessage(
                context.getString(R.string.default_title_error_state),
                context.getString(R.string.default_message_error_state)
            )
        )
    }

    interface ContentStateViewListener {
        fun onStateChanged(isContentVisible: Boolean, state: Int)
    }
}

data class ContentStateViewConfig(
    val isShowProgress: Boolean,
    val isShowTitle: Boolean = false,
    val isShowSubtitle: Boolean = false,
    val contentStateViewMessage: ContentStateViewMessage? = null
)

data class ContentStateViewMessage(
    val title: String? = null,
    val message: String? = null
)

