package dev.asid.units

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    private var firstUnit: String? = null
    private var secondUnit: String? = null
    private var inputValue: Double? = null
    private var outputValue: Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val weightUnits = resources.getStringArray(R.array.WeightUnits)
        val lengthUnits = resources.getStringArray(R.array.LengthUnits)
        val temperatureUnits = resources.getStringArray(R.array.TemperatureUnits)
        val firstSpinner = findViewById<Spinner>(R.id.firstunit)
        val secondSpinner = findViewById<Spinner>(R.id.secondunit)
        val weightsBtn = findViewById<Button>(R.id.weight)
        val lengthsBtn = findViewById<Button>(R.id.length)
        val temperaturesBtn = findViewById<Button>(R.id.temperature)
        val input = findViewById<EditText>(R.id.input)
        val output = findViewById<TextView>(R.id.output)
        val goBtn = findViewById<Button>(R.id.go)

        weightsBtn.setOnClickListener {
            firstSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightUnits)
            secondSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, weightUnits)
        }
        lengthsBtn.setOnClickListener {
            firstSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lengthUnits)
            secondSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, lengthUnits)
        }
        temperaturesBtn.setOnClickListener {
            firstSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatureUnits)
            secondSpinner.adapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatureUnits)
        }
        firstSpinner.onItemSelectedListener = this
        secondSpinner.onItemSelectedListener = this
        goBtn.setOnClickListener {
            inputValue = input.text.toString().toDouble()
            output.setText("")
            Toast.makeText(this, "Converting...$inputValue$firstUnit to $secondUnit", Toast.LENGTH_SHORT).show()
            if(firstUnit == "Pounds" && secondUnit == "Kilograms"){
                output.text = poundsToKilograms(inputValue)
            }
            else if(firstUnit == "Kilograms" && secondUnit == "Pounds"){
                output.text = kilogramsToPounds(inputValue)
            }
            else if(firstUnit == "Ounces" && secondUnit == "Kilograms"){
                output.text = ouncesToKilograms(inputValue)
            }
            else if(firstUnit == "Kilograms" && secondUnit == "Ounces"){
                output.text = kilogramsToOunces(inputValue)
            }
            else if(firstUnit == "Ounces" && secondUnit == "Grams"){
                output.text = ouncesToGrams(inputValue)
            }
            else if(firstUnit == "Grams" && secondUnit == "Ounces"){
                output.text = gramsToOunces(inputValue)
            }
            else if(firstUnit == "Feet" && secondUnit == "Meters"){
                output.text = feetToMeters(inputValue)
            }
            else if(firstUnit == "Meters" && secondUnit == "Feet"){
                output.text = metersToFeet(inputValue)
            }

            else if(firstUnit == "Inches" && secondUnit == "Centimeters"){
                output.text = inchesToCentimeters(inputValue)
            }
            else if(firstUnit == "Centimeters" && secondUnit == "Inches"){
                output.text = centimetersToInches(inputValue)
            }
            else if(firstUnit == "Celsius" && secondUnit == "Fahrenheit"){
                output.text = celsiusToFahrenheit(inputValue)
            }
            else if(firstUnit == "Fahrenheit" && secondUnit == "Celsius"){
                output.text = fahrenheitToCelsius(inputValue)
            }
            else if(firstUnit == "Kelvin" && secondUnit == "Celsius"){
                output.text = kelvinToCelsius(inputValue)
            }
            else if(firstUnit == "Celsius" && secondUnit == "Kelvin"){
                output.text = celsiusToKelvin(inputValue)
            }
            else if(firstUnit == "Kelvin" && secondUnit == "Fahrenheit"){
                output.text = kelvinToFahrenheit(inputValue)
            }
            else if(firstUnit == "Fahrenheit" && secondUnit == "Kelvin"){
                output.text = fahrenheitToKelvin(inputValue)
            }
        }
    }

     companion object fun fahrenheitToKelvin(inputValue: Double?):String {
        return((inputValue!! - 32) * 5 / 9 + 273.15).toString()

    }

     fun kelvinToFahrenheit(inputValue: Double?):String {
        return ((inputValue!! - 273.15) * 9 / 5 + 32).toString()

    }

     fun celsiusToKelvin(inputValue: Double?):String {
        return ((inputValue!! + 273.15).toString())
    }

     fun kelvinToCelsius(inputValue: Double?):String {
        return ((inputValue!! - 273.15).toString())
    }

     fun fahrenheitToCelsius(inputValue: Double?):String{
        return (((inputValue!! - 32) * 5 / 9).toString())
    }

     fun celsiusToFahrenheit(inputValue: Double?):String{
        return (((inputValue!! * 9 / 5) + 32).toString())
    }

     fun centimetersToInches(inputValue: Double?):String {
        return ((inputValue!! * 0.393701).toString())
    }

     fun inchesToCentimeters(inputValue: Double?):String {
        return ((inputValue!! * 2.54).toString())
    }

     fun metersToFeet(inputValue: Double?):String {
        return (( inputValue!! * 3.28084).toString())
    }

     fun feetToMeters(inputValue: Double?):String {
        return ((inputValue!! * 0.3048).toString())
    }

     fun gramsToOunces(inputValue: Double?):String {
        return ((inputValue!! * 0.035274).toString())
    }

     fun ouncesToGrams(inputValue: Double?):String {
        return ((inputValue!! * 28.3495).toString())
    }

     fun kilogramsToOunces(inputValue: Double?):String {
        return ((inputValue!! * 35.274).toString())
    }

     fun ouncesToKilograms(inputValue: Double?):String {
        return ((inputValue!! * 0.0283495).toString())
    }

     fun kilogramsToPounds(inputValue: Double?):String {
        return ((inputValue!! * 2.20462).toString())
    }

     fun poundsToKilograms(inputValue: Double?):String {
        return ((inputValue!! * 0.453592).toString())

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent?.id == R.id.firstunit)
            firstUnit = parent?.getItemAtPosition(position).toString()
        else
            secondUnit = parent?.getItemAtPosition(position).toString()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}