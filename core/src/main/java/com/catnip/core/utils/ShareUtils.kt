package com.catnip.core.utils

import android.content.Context
import android.content.Intent

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object ShareUtils {
    fun sendLink(context: Context?, link: String?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Check this Image from Pixagram by Pixabay API. $link")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        context?.startActivity(shareIntent)

    }
}