package com.catnip.core.utils

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object TextUtils {
    fun getTags(tag : String) : String{
        return tag.split(",").joinToString(separator = " ") {
            "#".plus(it.replace("\\s".toRegex(), ""))
        }
    }
}