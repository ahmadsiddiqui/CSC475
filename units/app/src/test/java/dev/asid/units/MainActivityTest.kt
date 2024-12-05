package dev.asid.units
import dev.asid.units.MainActivity

import org.junit.jupiter.api.*

class MainActivityTest {
    private val mainActivity = MainActivity()

    @Test
    public fun fahrenheitToKelvinTest() {
        val result = mainActivity.fahrenheitToKelvin(32.0)
        Assertions.assertEquals("273.15", result)
    }

    @Test
    public fun kelvinToFahrenheitTest() {
        val result = mainActivity.kelvinToFahrenheit(273.15)
        Assertions.assertEquals("32.0", result)
    }

    @Test
    public fun celsiusToKelvin() {
        val result = mainActivity.celsiusToKelvin(0.0)
        Assertions.assertEquals("273.15", result)
    }

    @Test
    public fun kelvinToCelsius() {
        val result = mainActivity.kelvinToCelsius(273.15)
        Assertions.assertEquals("0.0", result)
    }

    @Test
    public fun fahrenheitToCelsius() {
        val result = mainActivity.fahrenheitToCelsius(32.0)
        Assertions.assertEquals("0.0", result)
    }

    @Test
    public fun celsiusToFahrenheit() {
        val result = mainActivity.celsiusToFahrenheit(0.0)
        Assertions.assertEquals("32.0", result)
    }

    @Test
    public fun centimetersToInches() {
        val result = mainActivity.centimetersToInches(2.54)
        Assertions.assertEquals("1.0", result)
    }

    @Test
    public fun inchesToCentimeters() {
        val result = mainActivity.inchesToCentimeters(1.0)
        Assertions.assertEquals("2.54", result)
    }

    @Test
    public fun metersToFeet() {
        val result = mainActivity.metersToFeet(1.0)
        Assertions.assertEquals("3.28084", result)
    }

    @Test
    fun feetToMeters() {
        val result = mainActivity.feetToMeters(1.0)
        Assertions.assertEquals("0.3048", result)
    }

    @Test
    public fun gramsToOunces() {
        val result = mainActivity.gramsToOunces(1.0)
        Assertions.assertEquals("0.035274", result)
    }

    @Test
    public fun ouncesToGrams() {
        val result = mainActivity.ouncesToGrams(1.0)
        Assertions.assertEquals("28.3495", result)
    }

    @Test
    public fun kilogramsToOunces() {
        val result = mainActivity.kilogramsToOunces(1.0)
        Assertions.assertEquals("35.274", result)
    }

    @Test
    fun ouncesToKilograms() {
        val result = mainActivity.ouncesToKilograms(1.0)
        Assertions.assertEquals("0.0283495", result)
    }

    @Test
    public fun kilogramsToPounds() {
        val result = mainActivity.kilogramsToPounds(1.0)
        Assertions.assertEquals("2.20462", result)
    }

    @Test
    public fun poundsToKilograms() {
        val result = mainActivity.poundsToKilograms(1.0)
        Assertions.assertEquals("0.453592", result)

    }
}