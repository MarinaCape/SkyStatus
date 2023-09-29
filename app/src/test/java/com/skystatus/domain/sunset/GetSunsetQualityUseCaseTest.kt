package com.skystatus.domain.sunset

import com.skystatus.domain.model.DawnType
import com.skystatus.domain.model.Sunset
import com.skystatus.domain.model.SunsetType
import com.skystatus.domain.use_case.sunset.GetSunsetQualityUseCase
import com.skystatus.domain.repository.SunsetRepository
import com.skystatus.utils.TestUtils
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
class GetSunsetQualityUseCaseTest : TestUtils() {

    @MockK
    private lateinit var repository: SunsetRepository

    @InjectMockKs
    private lateinit var useCase: GetSunsetQualityUseCase

    @Test
    fun `invoke should call to repository`() = runBlocking {
        coEvery { repository.getQualitySun(any()) } returns buildSunset()

        useCase("")
        coVerify { repository.getQualitySun(any()) }
    }

    private fun buildSunset() = Sunset(
        DawnType.SUNSET,
        LocalDateTime.now(),
        0.0,
        0.0,
        SunsetType.FAIR
    )

}