package com.skystatus.presentation.sunset

import android.provider.CallLog.Locations
import app.cash.turbine.test
import com.skystatus.data.service.SettingsService
import com.skystatus.domain.core.failureOf
import com.skystatus.domain.core.successOf
import com.skystatus.domain.entity.City
import com.skystatus.domain.entity.DailyForecast
import com.skystatus.domain.entity.DawnType
import com.skystatus.domain.entity.HourlyForecast
import com.skystatus.domain.entity.Sunset
import com.skystatus.domain.entity.SunsetType
import com.skystatus.domain.interactor.forecast.Get12HoursForecastUseCase
import com.skystatus.domain.interactor.forecast.Get5DaysForecastUseCase
import com.skystatus.domain.interactor.forecast.GetSearchCitiesUseCase
import com.skystatus.domain.interactor.sunset.GetSunsetQualityUseCase
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
import java.time.LocalDateTime

class SunsetViewModelTest : TestUtils() {

    @MockK
    private lateinit var getSunsetQualityUseCase: GetSunsetQualityUseCase

    @InjectMockKs
    private lateinit var viewModel: SunsetViewModel

    @Test
    fun `onEvent when Initialize must call InitializeView and then Error`() =
        runBlocking {
            coEvery { getSunsetQualityUseCase(any()) } returns successOf(buildSunset())
            viewModel.viewState.test {
                viewModel.onEvent(SunsetEvent.InitializeView)
                awaitItem() should instanceOf<SunsetViewState.Loading>()
                awaitItem() should instanceOf<SunsetViewState.InitializeView>()
                awaitItem() should instanceOf<SunsetViewState.Loading>()

                coVerify { getSunsetQualityUseCase(any()) }
                cancelAndConsumeRemainingEvents()
            }
        }

    private fun buildSunset() = Sunset(
        DawnType.SUNSET,
        LocalDateTime.now(),
        0.0,
        0.0,
        SunsetType.FAIR
    )

}