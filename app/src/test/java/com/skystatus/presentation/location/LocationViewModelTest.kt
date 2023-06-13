package com.skystatus.presentation.location

import android.provider.CallLog.Locations
import app.cash.turbine.test
import com.skystatus.data.service.SettingsService
import com.skystatus.domain.core.failureOf
import com.skystatus.domain.core.successOf
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.interactor.forecast.Get12HoursForecastUseCase
import com.skystatus.domain.interactor.forecast.Get5DaysForecastUseCase
import com.skystatus.domain.interactor.forecast.GetSearchCitiesUseCase
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

class LocationViewModelTest : TestUtils() {

    @MockK
    private lateinit var getSearchCitiesUseCase: GetSearchCitiesUseCase

    @InjectMockKs
    private lateinit var viewModel: LocationViewModel

    @Test
    fun `onEvent when InitializeView must call InitializeView and then InitializeView`() =
        runBlocking {
            viewModel.viewState.test {
                viewModel.onEvent(LocationEvent.InitializeView)
                awaitItem() should instanceOf<LocationViewState.InitializeView>()
                cancelAndConsumeRemainingEvents()
            }
        }

    @Test
    fun `onEvent when Initialize must call InitializeView and then Error`() =
        runBlocking {
            coEvery { getSearchCitiesUseCase(any()) } returns successOf(buildListCities())
            viewModel.viewState.test {
                viewModel.onEvent(LocationEvent.SearchLocation(""))
                awaitItem() should instanceOf<LocationViewState.Loading>()
                awaitItem() should instanceOf<LocationViewState.ShowLocations>()
                awaitItem() should instanceOf<LocationViewState.Loading>()

                coVerify { getSearchCitiesUseCase(any()) }
                cancelAndConsumeRemainingEvents()
            }
        }

    private fun buildListCities() = listOf<City>()

}