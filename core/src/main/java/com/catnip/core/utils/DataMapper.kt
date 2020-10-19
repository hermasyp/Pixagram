package com.catnip.core.utils

import com.catnip.core.data.source.local.entity.FeedLocalEntity
import com.catnip.core.data.source.remote.entity.FeedResponseEntity
import com.catnip.core.domain.viewparam.FeedViewParam

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object DataMapper {
    fun mapResponsesToEntities(input: List<FeedResponseEntity>): List<FeedLocalEntity> =
        input.map {
            FeedLocalEntity(
                id = it.id,
                webformatWidth = it.webformatWidth,
                webformatHeight = it.webformatHeight,
                imageWidth = it.imageWidth,
                favorites = it.favorites,
                previewHeight = it.previewHeight,
                webformatURL = it.webformatURL,
                userImageURL = it.userImageURL,
                previewURL = it.previewURL,
                comments = it.comments,
                type = it.type,
                imageHeight = it.imageHeight,
                tags = it.tags,
                previewWidth = it.previewWidth,
                downloads = it.downloads,
                userId = it.userId,
                largeImageURL = it.largeImageURL,
                pageURL = it.pageURL,
                imageSize = it.imageSize,
                user = it.user,
                views = it.views,
                likes = it.likes,
                isFavorite = false
            )
        }

    fun mapResponsesToViewParam(input: List<FeedResponseEntity>): List<FeedViewParam> =
        input.map {
            FeedViewParam(
                id = it.id,
                webformatWidth = it.webformatWidth,
                webformatHeight = it.webformatHeight,
                imageWidth = it.imageWidth,
                favorites = it.favorites,
                previewHeight = it.previewHeight,
                webformatURL = it.webformatURL,
                userImageURL = it.userImageURL,
                previewURL = it.previewURL,
                comments = it.comments,
                type = it.type,
                imageHeight = it.imageHeight,
                tags = it.tags,
                previewWidth = it.previewWidth,
                downloads = it.downloads,
                userId = it.userId,
                largeImageURL = it.largeImageURL,
                pageURL = it.pageURL,
                imageSize = it.imageSize,
                user = it.user,
                views = it.views,
                likes = it.likes,
                isFavorite = false
            )
        }

    fun mapEntitiesToViewParams(input: List<FeedLocalEntity>): List<FeedViewParam> =
        input.map {
            FeedViewParam(
                id = it.id,
                webformatWidth = it.webformatWidth,
                webformatHeight = it.webformatHeight,
                imageWidth = it.imageWidth,
                favorites = it.favorites,
                previewHeight = it.previewHeight,
                webformatURL = it.webformatURL,
                userImageURL = it.userImageURL,
                previewURL = it.previewURL,
                comments = it.comments,
                type = it.type,
                imageHeight = it.imageHeight,
                tags = it.tags,
                previewWidth = it.previewWidth,
                downloads = it.downloads,
                userId = it.userId,
                largeImageURL = it.largeImageURL,
                pageURL = it.pageURL,
                imageSize = it.imageSize,
                user = it.user,
                views = it.views,
                likes = it.likes,
                isFavorite = it.isFavorite
            )
        }

    fun mapEntityToViewParam(it: FeedLocalEntity): FeedViewParam =
        FeedViewParam(
            id = it.id,
            webformatWidth = it.webformatWidth,
            webformatHeight = it.webformatHeight,
            imageWidth = it.imageWidth,
            favorites = it.favorites,
            previewHeight = it.previewHeight,
            webformatURL = it.webformatURL,
            userImageURL = it.userImageURL,
            previewURL = it.previewURL,
            comments = it.comments,
            type = it.type,
            imageHeight = it.imageHeight,
            tags = it.tags,
            previewWidth = it.previewWidth,
            downloads = it.downloads,
            userId = it.userId,
            largeImageURL = it.largeImageURL,
            pageURL = it.pageURL,
            imageSize = it.imageSize,
            user = it.user,
            views = it.views,
            likes = it.likes,
            isFavorite = it.isFavorite
        )


    fun mapViewParamToEntity(it: FeedViewParam): FeedLocalEntity =
        FeedLocalEntity(
            id = it.id,
            webformatWidth = it.webformatWidth,
            webformatHeight = it.webformatHeight,
            imageWidth = it.imageWidth,
            favorites = it.favorites,
            previewHeight = it.previewHeight,
            webformatURL = it.webformatURL,
            userImageURL = it.userImageURL,
            previewURL = it.previewURL,
            comments = it.comments,
            type = it.type,
            imageHeight = it.imageHeight,
            tags = it.tags,
            previewWidth = it.previewWidth,
            downloads = it.downloads,
            userId = it.userId,
            largeImageURL = it.largeImageURL,
            pageURL = it.pageURL,
            imageSize = it.imageSize,
            user = it.user,
            views = it.views,
            likes = it.likes,
            isFavorite = it.isFavorite
        )


}

