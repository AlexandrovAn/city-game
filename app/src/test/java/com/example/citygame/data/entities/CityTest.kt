package com.example.citygame.data.entities

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileInputStream

class CityTest {

    companion object {

        const val path = "src\\test\\resources\\city.json"

        val testCity = City(
            region = "Москва и Московская обл.",
            city = "Москва"
        )
    }

    private val gson = Gson()

    @Test
    fun `validate deserialization`() {
        val city: String = FileInputStream(path).use {
            it.reader().readText()
        }
        val cityFromGson = gson.fromJson(city, City::class.java)
        assertEquals(testCity, cityFromGson)
    }

}