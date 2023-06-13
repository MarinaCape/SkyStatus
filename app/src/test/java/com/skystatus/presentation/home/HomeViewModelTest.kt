package com.skystatus.presentation.home

import app.cash.turbine.test
import com.skystatus.data.service.SettingsService
import com.skystatus.domain.core.failureOf
import com.skystatus.domain.core.successOf
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.interactor.forecast.Get12HoursForecastUseCase
import com.skystatus.domain.interactor.forecast.Get5DaysForecastUseCase
import com.skystatus.utils.TestUtils
import io.kotest.matchers.should
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any

class HomeViewModelTest : TestUtils() {

    @MockK
    private lateinit var settingsService: SettingsService

    @MockK
    private lateinit var get12HoursForecastUseCase: Get12HoursForecastUseCase

    @MockK
    private lateinit var get5DaysForecastUseCase: Get5DaysForecastUseCase

    @InjectMockKs
    private lateinit var viewModel: HomeViewModel

    @Test
    fun `onEvent when Initialize must call InitializeView and then InitializeView`() =
        runBlocking {
            coEvery { settingsService.cityFavourite } returns "0"
            coEvery { get12HoursForecastUseCase(any()) } returns successOf(buildHourly())
            coEvery { get5DaysForecastUseCase(any()) } returns successOf(buildDaily())
            viewModel.viewState.test {
                viewModel.onEvent(HomeEvent.InitializeView(any()))
                awaitItem() should instanceOf<HomeViewState.Loading>()
                awaitItem() should instanceOf<HomeViewState.InitializeView>()
                awaitItem() should instanceOf<HomeViewState.Loading>()

                coVerify { get12HoursForecastUseCase(any()) }
                coVerify { get5DaysForecastUseCase(any()) }
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `onEvent when Initialize must call InitializeView and then Error`() =
        runBlocking {
            coEvery { settingsService.cityFavourite } returns "0"
            coEvery { get12HoursForecastUseCase(any()) } returns failureOf(RuntimeException())
            viewModel.viewState.test {
                viewModel.onEvent(HomeEvent.InitializeView(any()))
                awaitItem() should instanceOf<HomeViewState.Loading>()
                awaitItem() should instanceOf<HomeViewState.Error>()

                coVerify { get12HoursForecastUseCase(any()) }
                cancelAndConsumeRemainingEvents()
            }
        }

    private fun buildHourly() = listOf<HourlyForecast>()
    private fun buildDaily() = listOf<DailyForecast>()

}