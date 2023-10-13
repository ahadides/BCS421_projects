package edu.farmingdale.bcs421.bcs421_f22_w6_fragmentsdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    lateinit var mBtn1:Button
    lateinit var mBtn2:Button
    lateinit var mTV:TextView
    lateinit var seekBar : SeekBar
    lateinit var editText01 : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mBtn1 = findViewById(R.id.btn1)
        mBtn2 = findViewById(R.id.btn2)
        mTV = findViewById(R.id.tv1)
        editText01 = findViewById(R.id.editText01)
        seekBar = findViewById(R.id.seekBar)
        var frgmnt01 = Fragment01()
        var frgmnt02 = Fragment02()


        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val textSize = (12 + (progress)*2) .toFloat()
                editText01.setText(textSize.toString())
                mTV.textSize = textSize
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                // Not needed for your implementation
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                // Not needed for your implementation
            }
        })

        mBtn1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt01)
                commit()
            }
        }
        mBtn2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.framelayout1, frgmnt02)
                commit()
            }
            readFromSharedPref()
        }
    }

    private fun readFromSharedPref() {
        val sharedPref = getSharedPreferences(SHAREDPREF_FILENAME, MODE_PRIVATE)
        mTV.text = sharedPref.getString("KEY", "not found")
    }

    companion object {
        const val SHAREDPREF_FILENAME = "YourSharedPrefFile"
    }
}
