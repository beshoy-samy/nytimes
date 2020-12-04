package com.beshoy.nytimes.koin

import com.beshoy.nytimes.features.home.domain.HomeUseCase
import com.beshoy.nytimes.features.home.domain.HomeUseCaseImp
import com.beshoy.nytimes.features.home.model.HomeRepo
import com.beshoy.nytimes.features.home.model.HomeRepoImp
import com.beshoy.nytimes.features.home.presentation.HomeViewModel
import com.beshoy.nytimes.remote.services.provideMostPopularService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get()) }

    factory<HomeRepo> { HomeRepoImp(get()) }
    factory<HomeUseCase> { HomeUseCaseImp(get()) }

    single { provideMostPopularService(get()) }
}
