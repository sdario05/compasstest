package com.dario.compasstest

import androidx.lifecycle.Observer
import com.dario.compasstest.analyzetext.domain.FetchCompassWebsiteHtmlUseCase
import com.dario.compasstest.analyzetext.presentation.PlainTextAnalyzeViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

class PlainTextAnalyzeViewModelTest: BaseUnitTest() {

    private lateinit var viewModel: PlainTextAnalyzeViewModel
    @Mock
    private lateinit var useCase: FetchCompassWebsiteHtmlUseCase

    override fun setup() {
        super.setup()
        viewModel = PlainTextAnalyzeViewModel(
            useCase
        )
    }

    @Test
    fun `We test viewModel method getWordCount with success`() {
        val mockObserver = Mockito.mock(Observer::class.java) as Observer<List<Char>>
        viewModel.characterArrayData.observeForever(mockObserver)
        val expectedResult = "Example test Example test"
        runBlocking {
            Mockito.`when`(useCase.fetchWebsiteHtml()).then {
                expectedResult
            }
        }
        viewModel.getCharacterArray()
        Assert.assertEquals(listOf('s', ' '), viewModel.characterArrayData.value)
        viewModel.characterArrayData.removeObserver(mockObserver)
    }

    @Test
    fun `We test viewModel method getCharacterArray with success`() {
        val mockObserver = Mockito.mock(Observer::class.java) as Observer<HashMap<String, Int>>
        viewModel.wordCountData.observeForever(mockObserver)
        val expectedResult = "Example test Example test"
        runBlocking {
            Mockito.`when`(useCase.fetchWebsiteHtml()).then {
                expectedResult
            }
        }
        viewModel.getWordCount()
        Assert.assertEquals(hashMapOf("test" to 2, "example" to 2), viewModel.wordCountData.value)
        viewModel.wordCountData.removeObserver(mockObserver)
    }
}