package com.ratnavidyakanawade.mphasisandroidcodingproject.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.ratnavidyakanawade.mphasisandroidcodingproject.DataStoreManager
import com.ratnavidyakanawade.mphasisandroidcodingproject.model.Main
import com.ratnavidyakanawade.mphasisandroidcodingproject.model.Weather
import com.ratnavidyakanawade.mphasisandroidcodingproject.model.WeatherResponse
import com.ratnavidyakanawade.mphasisandroidcodingproject.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class WeatherViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Mock or Fake instance of DataStoreManager
    @Mock
    private lateinit var mockDataStoreManager: DataStoreManager
    private lateinit var viewModel: WeatherViewModel
    private lateinit var repository: WeatherRepository

    @Before
    fun setup() {
        repository = mock(WeatherRepository::class.java)
        viewModel = WeatherViewModel(repository, mockDataStoreManager)
    }

    @Test
    fun `fetch weather success`() = runBlockingTest {
        val weather = WeatherResponse(
            listOf(Weather("clear sky", "01d")),
            Main(20.0, 18.0, 15.0, 25.0),
            "New York"
        )
        `when`(repository.getWeather("New York", "api_key")).thenReturn(weather)

        viewModel.fetchWeather("New York", "api_key")
        assertEquals("New York", viewModel.weatherState.value?.name)
    }
}