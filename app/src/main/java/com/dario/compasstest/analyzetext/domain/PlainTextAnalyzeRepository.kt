package com.dario.compasstest.analyzetext.domain

interface PlainTextAnalyzeRepository {

    suspend fun fetchCompassWebsiteHtml(): String
}