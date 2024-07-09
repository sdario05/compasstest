package com.dario.compasstest.analyzetext.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dario.compasstest.R
import com.dario.compasstest.databinding.ActivityPlainTextAnalyzeBinding
import org.koin.android.ext.android.inject

class PlainTextAnalyzeActivity:  AppCompatActivity() {

    private val viewModel: PlainTextAnalyzeViewModel by inject()
    private lateinit var binding: ActivityPlainTextAnalyzeBinding

    private var charArrayFetched = false
    private var wordsCountFetched = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlainTextAnalyzeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        observeLiveData()
        setListeners()
    }

    private fun observeLiveData() {
        viewModel.characterArrayData.observe(this, ::showCharArray)
        viewModel.wordCountData.observe(this, ::showWordsCount)
        viewModel.characterArrayError.observe(this, ::showCharArrayError)
        viewModel.wordCountError.observe(this, ::showWordsCountError)
    }

    private fun setListeners() {
        binding.btnProcess.setOnClickListener {
            binding.tvCharArrayValue.text = getString(R.string.result_hint)
            binding.tvWordsCountValue.text = getString(R.string.result_hint)
            disableButton()
            if (viewModel.characterArrayData.value != null &&
                viewModel.wordCountData.value != null
            ) {
                applyDelay {
                    showCharArray(viewModel.characterArrayData.value!!)
                    showWordsCount(viewModel.wordCountData.value!!)
                }
            } else {
                viewModel.getCharacterArray()
                viewModel.getWordCount()
            }
        }
    }

    private fun showCharArray(array: List<Char>) {
        binding.tvCharArrayValue.text =
            getString(R.string.format_string_to_display, array.joinToString(separator = ", "))
        wordsCountFetched = true
        if (charArrayFetched) {
            enableButton()
        }
    }

    private fun showWordsCount(wordsCount: HashMap<String, Int>) {
        var countWordsToDisplay = ""
        for ((key, value) in wordsCount) {
            countWordsToDisplay += "$key = $value\n"
        }
        binding.tvWordsCountValue.text =
            getString(R.string.format_string_to_display, countWordsToDisplay)
        charArrayFetched = true
        if (wordsCountFetched) {
            enableButton()
        }
    }

    private fun enableButton() {
        binding.btnProcess.apply {
            charArrayFetched = false
            wordsCountFetched = false
            isEnabled = true
            text = getString(R.string.btn_title)
        }
    }

    private fun disableButton() {
        binding.btnProcess.apply {
            isEnabled = false
            text = getString(R.string.btn_processing)
        }
    }

    private fun showCharArrayError(error: String) {
        binding.tvCharArrayValue.text = error
    }

    private fun showWordsCountError(error: String) {
        binding.tvWordsCountValue.text = error
    }

    private fun applyDelay(function: () -> Unit ) {
        Handler(Looper.getMainLooper()).postDelayed(function, 10)
    }
}