package com.beshoy.nytimes.features.home.domain

import com.beshoy.nytimes.models.NyTimesArticleModel
import com.beshoy.nytimes.remote.TimePeriod
import com.beshoy.nytimes.remote.error.ApiErrorModel

interface HomeUseCase {

    suspend fun getMostPopularArticles(
        period: String = TimePeriod.SEVEN_DAYS.value,
        success: ((List<NyTimesArticleModel>) -> Unit),
        fail: ((ApiErrorModel) -> Unit)
    )
}