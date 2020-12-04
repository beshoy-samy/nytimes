package com.beshoy.nytimes.koin

import com.beshoy.nytimes.features.domain.HomeUseCase
import com.beshoy.nytimes.features.domain.HomeUseCaseImp
import com.beshoy.nytimes.features.model.HomeRepo
import com.beshoy.nytimes.features.model.HomeRepoImp
import com.beshoy.nytimes.features.presentation.HomeViewModel
import com.beshoy.nytimes.remote.services.provideMostPopularService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    viewModel { HomeViewModel(get()) }

    factory<HomeRepo> { HomeRepoImp(get()) }
    factory<HomeUseCase> { HomeUseCaseImp(get()) }

    single { provideMostPopularService(get()) }
}
