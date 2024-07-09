package com.dario.compasstest.analyzetext.data

import com.dario.compasstest.analyzetext.domain.PlainTextAnalyzeRepository

class PlainTextAnalyzeRepositoryImpl(
    private val service: PlainTextAnalyzeService
): PlainTextAnalyzeRepository {
    override suspend fun fetchCompassWebsiteHtml(): String {
        return service.fetchCompassWebsiteHtml()
    }
}