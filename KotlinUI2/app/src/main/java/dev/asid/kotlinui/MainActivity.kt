package dev.asid.kotlinui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.Switch
import android.widget.TextClock
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat



class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var value = 0
   // private val textView = findViewById<TextView>(R.id.txtValue)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.add).setOnClickListener(this)
        findViewById<View>(R.id.take).setOnClickListener(this)
        findViewById<View>(R.id.grow).setOnClickListener(this)
        findViewById<View>(R.id.shrink).setOnClickListener(this)


        findViewById<Switch>(R.id.toggleView).setOnCheckedChangeListener{
            buttonView, isChecked ->
            if (isChecked) {
                findViewById<View>(R.id.txtValue).visibility = View.VISIBLE
            }
            else {
                findViewById<View>(R.id.txtValue).visibility = View.INVISIBLE
            }
        }
        findViewById<CheckBox>(R.id.myCheckBox).setOnCheckedChangeListener {
            buttonView, isChecked ->
            if(isChecked) {
                findViewById<Button>(R.id.grow).textSize = 15F
            }
            else {
                findViewById<Button>(R.id.grow).textSize = 10F
            }
        }

        findViewById<TextClock>(R.id.tClock).timeZone = "Asia/Karachi"
        findViewById<Button>(R.id.hide).setOnClickListener{
            findViewById<TextView>(R.id.txtValue).visibility = View.INVISIBLE

        }

        findViewById<Button>(R.id.button).setOnClickListener {
            val myDialog = MyDialog()
            myDialog.show(supportFragmentManager, "123")
        }

    }
    fun changeView(){
        val linearLayout = LinearLayout(this)
        val myButton = Button(this)
        linearLayout.addView(myButton)
        setContentView(linearLayout)



    }

    override fun onClick(v: View) {
        val size:Float
        when (v.id) {
            R.id.add -> {
                //changeView()
                value++
                findViewById<TextView>(R.id.txtValue).text = "$value"
            }
            R.id.take -> {
                value--
                findViewById<TextView>(R.id.txtValue).text = "$value"
            }
            R.id.grow -> {
                size =findViewById<TextView>(R.id.txtValue).textSize
                findViewById<TextView>(R.id.txtValue).textSize = size + 1
            }
            R.id.shrink -> {
                size = findViewById<TextView>(R.id.txtValue).textSize
                findViewById<TextView>(R.id.txtValue).textSize = size - 1
            }
            R.id.hide -> {
                if (findViewById<TextView>(R.id.txtValue).visibility == View.VISIBLE) {
                    findViewById<TextView>(R.id.txtValue).visibility = View.INVISIBLE
                    findViewById<TextView>(R.id.hide).text = "show"

                }
                else {
                    findViewById<TextView>(R.id.txtValue).visibility = View.VISIBLE
                    findViewById<TextView>(R.id.hide).text = ("hide")

                }
            }
            R.id.reset -> {
                value = 0
                findViewById<TextView>(R.id.txtValue).text = "$value"
            }

        }
    }
}