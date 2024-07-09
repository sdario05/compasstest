package com.dario.compasstest.analyzetext.domain

class FetchCompassWebsiteHtmlUseCase(
    private val repository: PlainTextAnalyzeRepository
) {
    suspend fun fetchWebsiteHtml(): String {
        return repository.fetchCompassWebsiteHtml()
    }
}