package br.com.animes.di.home

import br.com.animes.base.data.remote.factory.WebServiceFactory
import br.com.animes.feature.home.HomeViewModel
import br.com.animes.feature.home.data.AnimesRemoteDataSource
import br.com.animes.feature.home.data.AnimesRepositoryImpl
import br.com.animes.feature.home.data.remote.datasource.AnimesRemoteDataSourceImpl
import br.com.animes.feature.home.data.remote.service.AnimesWebService
import br.com.animes.feature.home.domain.repository.AnimesRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    //SERVICE
    single {
        get<WebServiceFactory>()
            .createWebService(AnimesWebService::class.java)
    }

    //DATA_REMOTE
    single<AnimesRemoteDataSource> {
        AnimesRemoteDataSourceImpl(get())
    }

    //DATA
    single<AnimesRepository> {
        AnimesRepositoryImpl(get())
    }

    //VIEW_MODEL
    viewModel {
        HomeViewModel(get())
    }
}
