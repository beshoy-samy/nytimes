package com.beshoy.nytimes.features.model

import com.beshoy.nytimes.remote.BaseResponseModel
import com.beshoy.nytimes.remote.TimePeriod
import com.beshoy.nytimes.remote.error.ApiErrorModel
import com.beshoy.nytimes.remote.models.NyTimesArticleResponseModel

interface HomeRepo {

    suspend fun getMostPopularArticles(
        period: String = TimePeriod.SEVEN_DAYS.value,
        success: ((BaseResponseModel<List<NyTimesArticleResponseModel>>) -> Unit),
        fail: ((ApiErrorModel) -> Unit)
    )
}