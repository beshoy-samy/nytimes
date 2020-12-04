package com.beshoy.nytimes.features.presentation

import com.beshoy.nytimes.base.BaseViewModel
import com.beshoy.nytimes.features.domain.HomeUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    init {
        getMostPopularArticles()
    }

    private fun getMostPopularArticles() {

    }

}