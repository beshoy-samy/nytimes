package com.beshoy.nytimes.remote.services

import com.beshoy.nytimes.remote.BaseResponseModel
import com.beshoy.nytimes.remote.Constants
import com.beshoy.nytimes.remote.TimePeriod
import com.beshoy.nytimes.remote.models.NyTimesArticleResponseModel
import com.beshoy.nytimes.utils.AppConstants
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MostPopularService {

    @GET("mostviewed/all-sections/{period}")
    suspend fun getMostPopularArticles(
        @Path("period") period: String = TimePeriod.SEVEN_DAYS.value,
        @Query(Constants.API_KEY) apiKey: String = AppConstants.MY_API_KEY
    ): BaseResponseModel<List<NyTimesArticleResponseModel>>
}

fun provideMostPopularService(retrofit: Retrofit): MostPopularService {
    return retrofit.create(MostPopularService::class.java)
}