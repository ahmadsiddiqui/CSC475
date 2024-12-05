package dev.asid.expressyourself

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val name = "Ahmad"
        val yearOfBirth = 1934
        var currentYear = 2023
        var age: Int
        age = currentYear - yearOfBirth
        showToast("Hi, my name is $name. I am $age years old.")
        when (age){
            in 0..12 -> Log.i("Age Status:Child")
            in 13..19 -> Log.i("Age Status:Teenager")
            else -> Log.i("Age Status:Adult")
        }
        }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}