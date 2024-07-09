package com.dario.compasstest.analyzetext.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dario.compasstest.analyzetext.domain.FetchCompassWebsiteHtmlUseCase
import kotlinx.coroutines.launch

class PlainTextAnalyzeViewModel(
    private val useCase: FetchCompassWebsiteHtmlUseCase
): ViewModel() {

    private val _characterArrayData = MutableLiveData<List<Char>>()
    val characterArrayData: LiveData<List<Char>> = _characterArrayData
    private val _characterArrayError = MutableLiveData<String>()
    val characterArrayError: LiveData<String> = _characterArrayError

    private val _wordCountData = MutableLiveData<HashMap<String, Int>>()
    val wordCountData: LiveData<HashMap<String, Int>> = _wordCountData
    private val _wordCountError = MutableLiveData<String>()
    val wordCountError: LiveData<String> = _wordCountError

    fun getCharacterArray() {
        viewModelScope.launch {
            try {
                val websiteHtml = useCase.fetchWebsiteHtml()
                val charArray = mutableListOf<Char>()
                for (i in 10..websiteHtml.length step 10) {
                    charArray.add(websiteHtml[i])
                }
                _characterArrayData.postValue(charArray)
            } catch (e: Exception) {
                _characterArrayError.postValue(e.message)
            }
        }
    }

    fun getWordCount() {
        viewModelScope.launch {
            try {
                val websiteHtml = useCase.fetchWebsiteHtml()
                var words = websiteHtml.split("\\s+".toRegex())
                words = words.map { it.lowercase() }
                val map = words.groupingBy { it }.eachCount()
                _wordCountData.postValue(map as HashMap<String, Int>)
            } catch (e: Exception) {
                _wordCountError.postValue(e.message)
            }
        }
    }
}