package com.skystatus.domain.forecast

import com.skystatus.domain.interactor.forecast.Get5DaysForecastUseCase
import com.skystatus.domain.repository.ForecastRepository
import com.skystatus.utils.TestUtils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test

@ExperimentalCoroutinesApi
class Get5DaysForecastUseCaseTest : TestUtils() {

    @MockK
    private lateinit var repository: ForecastRepository

    @InjectMockKs
    private lateinit var useCase: Get5DaysForecastUseCase

    @Test
    fun `invoke should call to repository`() = runBlocking {
        coEvery { repository.get5DaysForecast(any()) } returns listOf()

        useCase(0)
        coVerify { repository.get5DaysForecast(any()) }

    }

}