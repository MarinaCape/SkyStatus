package com.skystatus.utils

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before

open class TestUtils {

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun clean() {
        clearAllMocks()
    }
}