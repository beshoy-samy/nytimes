package com.beshoy.nytimes.features.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.beshoy.nytimes.base.BaseViewModel
import com.beshoy.nytimes.features.domain.HomeUseCase
import com.beshoy.nytimes.models.NyTimesArticleModel
import com.beshoy.nytimes.remote.error.ApiErrorModel
import com.beshoy.nytimes.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : BaseViewModel() {

    private val _showProgress = SingleLiveEvent<Boolean>()
    val showProgress: LiveData<Boolean>
        get() = _showProgress

    private val _articles = MutableLiveData<List<NyTimesArticleModel>>()
    val articles: LiveData<List<NyTimesArticleModel>>
        get() = _articles
    private val _error = SingleLiveEvent<ApiErrorModel>()
    val showError: LiveData<ApiErrorModel>
        get() = _error


    init {
        getMostPopularArticles()
    }

    fun getMostPopularArticles() {
        _showProgress.value = true
        viewModelScope.launch(dispatcher) {
            homeUseCase.getMostPopularArticles(
                success = { articles ->
                    _showProgress.postValue(false)
                    _articles.postValue(articles)
                }, fail = { error ->
                    _showProgress.postValue(false)
                    _error.postValue(error)
                }
            )
        }
    }

}