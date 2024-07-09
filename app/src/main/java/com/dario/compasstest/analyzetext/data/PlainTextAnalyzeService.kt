package com.dario.compasstest.analyzetext.data

import retrofit2.http.GET

interface PlainTextAnalyzeService {

    @GET("https://www.compass.com/about")
    suspend fun fetchCompassWebsiteHtml(): String
}