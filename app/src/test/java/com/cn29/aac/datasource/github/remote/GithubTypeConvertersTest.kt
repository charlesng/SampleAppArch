package com.cn29.aac.datasource.github.remote

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GithubTypeConvertersTest {
    private lateinit var converter: GithubTypeConverters

    @Test
    fun `should return (1,2) if the string is "1,2"`() {
        val listIds = GithubTypeConverters.stringToIntList("1,2")
        assertEquals(listOf(1, 2), listIds)
    }

    @Test
    fun `should return empty list if the string is empty`() {
        val listIds = GithubTypeConverters.stringToIntList("")
        assertEquals(emptyList<Int>(), listIds)
    }

    @Test
    fun `should return string 1,2 if the list is (1,2)`() {
        val str = GithubTypeConverters.intListToString(listOf(1, 2))
        assertEquals("1,2", str)
    }

    @Test
    fun `should return empty string if the list is empty`() {
        val str = GithubTypeConverters.intListToString(emptyList<Int>())
        assertEquals("", str)
    }
}