package com.example.helloworld

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

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

        Toast.makeText(this, "Can you see me?", Toast.LENGTH_SHORT).show()
        Log.i("info", "Done Creating the app")
    }

    fun add(v: View){
        val textView = findViewById<TextView>(R.id.result)
        val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
        val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
        textView.text = (firstOperand + secondOperand).toString()
    }

    fun subtract(v: View){
        val textView = findViewById<TextView>(R.id.result)
        val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
        val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
        textView.text = (firstOperand - secondOperand).toString()
    }

    fun multiply(v: View){
        val textView = findViewById<TextView>(R.id.result)
        val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
        val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
        textView.text = (firstOperand * secondOperand).toString()
    }

    fun divide(v: View){
        val textView = findViewById<TextView>(R.id.result)
        val firstOperand = findViewById<EditText>(R.id.firstOperand).text.toString().toInt()
        val secondOperand = findViewById<EditText>(R.id.secondOperand).text.toString().toInt()
        try {
            textView.text = (firstOperand / secondOperand).toString()
        }
        catch(e: ArithmeticException){
            Toast.makeText(this, "Cannot divide by Zero",Toast.LENGTH_SHORT).show()
        }
    }
}