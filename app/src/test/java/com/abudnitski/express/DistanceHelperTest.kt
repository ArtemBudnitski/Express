package com.abudnitski.express

import com.abudnitski.express.domain.main.Station
import com.abudnitski.express.presentation.main.DistanceHelper
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DistanceHelperTest {

    private val sut = DistanceHelper()

    @Test
    fun `Given start equals end When calculate Then distance as expected`() {
        val start = mockk<Station> {
            every { latitude } returns 55f
            every { longitude } returns 33f
        }
        val end = mockk<Station> {
            every { latitude } returns 55f
            every { longitude } returns 33f
        }

        val result = sut.calculateDistance(start, end)

        assertEquals(0f, result)
    }

    @Test
    fun `Given start different than end When calculate Then distance as expected`() {
        val start = mockk<Station> {
            every { latitude } returns 52.2297f
            every { longitude } returns 21.0122f
        }
        val end = mockk<Station> {
            every { latitude } returns 50.0647f
            every { longitude } returns 19.9450f
        }

        val result = sut.calculateDistance(start, end)

        assertEquals(251.97626408433118, result)
    }
}