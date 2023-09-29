package com.skystatus.domain.forecast

import com.skystatus.domain.use_case.forecast.GetSearchCitiesUseCase
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
class GetSearchCitiesUseCaseTest : TestUtils() {

    @MockK
    private lateinit var repository: ForecastRepository

    @InjectMockKs
    private lateinit var useCase: GetSearchCitiesUseCase

    @Test
    fun `invoke should call to repository`() = runBlocking {
        coEvery { repository.getSearchCities(any()) } returns listOf()

        useCase("")
        coVerify { repository.getSearchCities(any()) }

    }

}