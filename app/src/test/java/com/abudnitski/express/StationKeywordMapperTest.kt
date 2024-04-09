package com.abudnitski.express

import com.abudnitski.express.data.api.StationKeywordNet
import com.abudnitski.express.data.db.stationkeyword.StationKeywordEntity
import com.abudnitski.express.domain.list.StationKeyword
import com.abudnitski.express.domain.list.StationKeywordMapper
import org.junit.Test
import junit.framework.TestCase.assertEquals

class StationKeywordMapperTest {
    private val sut = StationKeywordMapper()

    @Test
    fun `map entity station keyword`() {
        val data: List<StationKeywordEntity> = listOf(StationKeywordEntity(1, "Wawa", 2), StationKeywordEntity(3, "Lublin", 4))

        val result = sut.mapEntity(data)

        val expected: List<StationKeyword> = listOf(StationKeyword(1, "Wawa", 2), StationKeyword(3, "Lublin", 4))
        assertEquals(expected, result)
    }

    @Test
    fun `map net station keyword`() {
        val data: List<StationKeywordNet> = listOf(StationKeywordNet(1, "Wawa", 2), StationKeywordNet(3, "Lublin", 4))

        val result = sut.mapNet(data)

        val expected: List<StationKeyword> = listOf(StationKeyword(1, "Wawa", 2), StationKeyword(3, "Lublin", 4))
        assertEquals(expected, result)
    }

    @Test
    fun `map list of Station Key Words to list of station keywords entity`() {
        val data: List<StationKeyword> = listOf(StationKeyword(1, "Wawa", 2), StationKeyword(3, "Lublin", 4))

        val result = sut.map(data)

        val expected: List<StationKeywordEntity> =
            listOf(StationKeywordEntity(1, "Wawa", 2), StationKeywordEntity(3, "Lublin", 4))
        assertEquals(expected, result)
    }
}