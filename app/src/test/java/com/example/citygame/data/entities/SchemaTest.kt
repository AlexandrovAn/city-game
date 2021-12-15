package com.example.citygame.data.entities

import com.google.gson.Gson
import net.pwall.json.schema.JSONSchema
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.FileInputStream

class SchemaTest {

    companion object {

        const val schemaPath = "src\\test\\resources\\city-schema.json"

        val testCity = City(
            region = "Москва и Московская обл.",
            city = "Москва"
        )
    }

    @Test
    fun `Schema validate`() {
        val schema = JSONSchema.parse(
            FileInputStream(schemaPath).use {
                it.reader().readText()
            }
        )
        val cityJSON = Gson().toJson(testCity)
        val output = schema.validateBasic(cityJSON)
        output.errors?.forEach { basicErrorEntry ->
            println("${basicErrorEntry.error} - ${basicErrorEntry.instanceLocation}")
        }
        assertEquals(true, output.errors.isNullOrEmpty())
    }

}