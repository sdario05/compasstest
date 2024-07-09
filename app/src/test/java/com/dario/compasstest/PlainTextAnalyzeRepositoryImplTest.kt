package com.dario.compasstest

import com.dario.compasstest.analyzetext.data.PlainTextAnalyzeRepositoryImpl
import com.dario.compasstest.analyzetext.data.PlainTextAnalyzeService
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class PlainTextAnalyzeRepositoryImplTest: BaseUnitTest() {

    @Mock
    private lateinit var service: PlainTextAnalyzeService

    private lateinit var repository: PlainTextAnalyzeRepositoryImpl

    @Before
    override fun setup() {
        super.setup()
        repository = PlainTextAnalyzeRepositoryImpl(service)
    }

    @Test
    fun `When fetchCompassWebsiteHtml is called, verify service is executed`() {
        runBlocking {
            val response = "HTML example"

            Mockito.`when`(service.fetchCompassWebsiteHtml()).thenReturn(response)
            repository.fetchCompassWebsiteHtml()
            Mockito.verify(service, Mockito.times(1)).fetchCompassWebsiteHtml()
        }
    }
}