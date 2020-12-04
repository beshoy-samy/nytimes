package com.beshoy.nytimes.koin

import com.beshoy.nytimes.features.details.DetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val detailsModule = module {

    viewModel { DetailsViewModel() }
}