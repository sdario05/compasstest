package com.dario.compasstest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
abstract  class BaseUnitTest: KoinTest {

    protected val testDispatcher = TestCoroutineDispatcher()

    @Mock
    protected lateinit var mockAplication: Application

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
    }


    @After
    open fun tearDown() {
        testDispatcher.cleanupTestCoroutines()
        Dispatchers.resetMain()

    }
}