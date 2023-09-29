package com.skystatus.presentation.sunset

import app.cash.turbine.test
import com.skystatus.domain.core.successOf
import com.skystatus.domain.model.DawnType
import com.skystatus.domain.model.Sunset
import com.skystatus.domain.model.SunsetType
import com.skystatus.domain.use_case.sunset.GetSunsetQualityUseCase
import com.skystatus.utils.TestUtils
import io.kotest.matchers.should
import io.kotest.matchers.types.instanceOf
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Test
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