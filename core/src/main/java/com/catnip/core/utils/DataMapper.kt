package com.catnip.core.utils

import com.catnip.core.data.source.local.entity.FeedItemEntity
import com.catnip.core.data.source.remote.response.FeedItemResponse
import com.catnip.core.domain.model.Feed

/**
Written with love by Muhammad Hermas Yuda Pamungkas
Github : https://github.com/hermasyp
 **/
object DataMapper {
    fun mapResponsesToEntities(input: List<FeedItemResponse>): List<FeedItemEntity> =
        input.map {
            FeedItemEntity(
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

    fun mapEntitiesToDomain(input: List<FeedItemEntity>): List<Feed> =
        input.map {
            Feed(
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

    fun mapDomainToEntity(it: Feed): FeedItemEntity =
        FeedItemEntity(
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

