package com.dario.compasstest.di

import com.dario.compasstest.analyzetext.data.PlainTextAnalyzeRepositoryImpl
import com.dario.compasstest.analyzetext.data.PlainTextAnalyzeService
import com.dario.compasstest.analyzetext.domain.FetchCompassWebsiteHtmlUseCase
import com.dario.compasstest.analyzetext.domain.PlainTextAnalyzeRepository
import com.dario.compasstest.analyzetext.presentation.PlainTextAnalyzeViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


val plainTextAnalyzeModule: Module = module {

    viewModel {
        PlainTextAnalyzeViewModel(get())
    }

    single { providerPlainTextAnalyzeService(get()) }
    single { providerRetrofit(get()) }
    single { providerHttpClient() }

    single<PlainTextAnalyzeRepository> { providerPlainTextAnalyzeRepository(get()) }
    single { providerFetchCompassWebsiteHtmlUseCase(get()) }

}

fun providerPlainTextAnalyzeService(retrofit: Retrofit): PlainTextAnalyzeService {
    return retrofit.create(PlainTextAnalyzeService::class.java)
}

fun providerRetrofit(client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://www.example.com/")
        .client(client)
        .build()
}

fun providerHttpClient(): OkHttpClient {
    val client = OkHttpClient.Builder().build()
    return client
}

fun providerPlainTextAnalyzeRepository(service: PlainTextAnalyzeService): PlainTextAnalyzeRepositoryImpl {
    return PlainTextAnalyzeRepositoryImpl(service)
}

fun providerFetchCompassWebsiteHtmlUseCase(repository: PlainTextAnalyzeRepository): FetchCompassWebsiteHtmlUseCase {
    return FetchCompassWebsiteHtmlUseCase(repository)
}