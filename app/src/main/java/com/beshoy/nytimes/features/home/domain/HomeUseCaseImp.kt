package com.beshoy.nytimes.features.home.domain

import com.beshoy.nytimes.features.home.model.HomeRepo
import com.beshoy.nytimes.models.ArticleMediaModel
import com.beshoy.nytimes.models.NyTimesArticleModel
import com.beshoy.nytimes.remote.BaseResponseModel
import com.beshoy.nytimes.remote.error.ApiErrorModel
import com.beshoy.nytimes.remote.models.MediaResponseModel
import com.beshoy.nytimes.remote.models.NyTimesArticleResponseModel
import com.beshoy.nytimes.utils.orNotFound

class HomeUseCaseImp(private val repo: HomeRepo) : HomeUseCase {

    override suspend fun getMostPopularArticles(
        period: String,
        success: (List<NyTimesArticleModel>) -> Unit,
        fail: (ApiErrorModel) -> Unit
    ) {
        repo.getMostPopularArticles(period, { mostPopularResponse ->
            success.invoke(mostPopularResponse.toDomainModel())
        }, fail)
    }

}

private fun BaseResponseModel<List<NyTimesArticleResponseModel>>.toDomainModel(): List<NyTimesArticleModel> {
    return results?.map { nyTimesArticleResponseModel ->
        NyTimesArticleModel(
            nyTimesArticleResponseModel.url.orEmpty(),
            nyTimesArticleResponseModel.id.orNotFound(),
            nyTimesArticleResponseModel.source.orEmpty(),
            nyTimesArticleResponseModel.publishedDate.orEmpty(),
            nyTimesArticleResponseModel.updated.orEmpty(),
            nyTimesArticleResponseModel.section.orEmpty(),
            nyTimesArticleResponseModel.adxKeywords?.split(";").orEmpty(),
            nyTimesArticleResponseModel.byline.orEmpty(),
            nyTimesArticleResponseModel.type.orEmpty(),
            nyTimesArticleResponseModel.title.orEmpty(),
            nyTimesArticleResponseModel.abstract.orEmpty(),
            nyTimesArticleResponseModel.mediaResponseModels.toDomainModel()
        )
    }.orEmpty()
}

private fun List<MediaResponseModel>?.toDomainModel(): List<ArticleMediaModel> {
    return this?.map { mediaResponseModel ->
        ArticleMediaModel(
            mediaResponseModel.type.orEmpty(),
            mediaResponseModel.caption.orEmpty(),
            mediaResponseModel.copyright.orEmpty(),
            mediaResponseModel.mediaMetadataResponseModels?.lastOrNull()?.url.orEmpty()
        )
    }.orEmpty()
}