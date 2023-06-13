package com.skystatus.domain.forecast

import com.skystatus.domain.interactor.forecast.Get12HoursForecastUseCase
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
class Get12HoursForecastUseCaseTest : TestUtils() {

    @MockK
    private lateinit var repository: ForecastRepository

    @InjectMockKs
    private lateinit var useCase: Get12HoursForecastUseCase

    @Test
    fun `invoke should call to repository`() = runBlocking {
        coEvery { repository.get12HoursForecast(any()) } returns listOf()

        useCase(0)
        coVerify { repository.get12HoursForecast(any()) }

    }

}