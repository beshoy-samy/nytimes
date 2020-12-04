package com.beshoy.nytimes.features.model

import com.beshoy.nytimes.remote.BaseResponseModel
import com.beshoy.nytimes.remote.error.ApiErrorModel
import com.beshoy.nytimes.remote.error.toApiErrorModel
import com.beshoy.nytimes.remote.models.NyTimesArticleResponseModel
import com.beshoy.nytimes.remote.services.MostPopularService

class HomeRepoImp(private val service: MostPopularService) : HomeRepo {

    override suspend fun getMostPopularArticles(
        period: String,
        success: (BaseResponseModel<List<NyTimesArticleResponseModel>>) -> Unit,
        fail: (ApiErrorModel) -> Unit
    ) {
        try {
            val response = service.getMostPopularArticles(period)
            success.invoke(response)
        } catch (throwable: Throwable) {
            fail.invoke(throwable.toApiErrorModel())
        }

    }

}