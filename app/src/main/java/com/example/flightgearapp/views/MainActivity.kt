package com.example.flightgearapp.views

import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.flightgearapp.R
import com.example.flightgearapp.databinding.ActivityMainBinding
import com.example.flightgearapp.view_model.MainViewModel

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel


        val rudderBar = findViewById<SeekBar>(R.id.Rudder)
        rudderBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                val is_stream = viewModel.isStream()
                //if is false
                if (!is_stream) {
                    val message = "Not connected yet!"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                } else {
                    //println("main value $progress")
                    viewModel.rudderChanged(getConvertedRudderValue(progress))
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        val throttleBar = findViewById<SeekBar>(R.id.Throttle)
        throttleBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, b: Boolean) {
                val is_stream = viewModel.isStream()
                //if is false
                if (!is_stream) {
                    val message = "Not connected yet!"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                } else {
                    //println("Thorttle" + getConvertedThrottleValue(progress))
                    viewModel.throttleChanged(getConvertedThrottleValue(progress))
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        val joystick = findViewById<Joystick>(R.id.Joystick)
            joystick.onChange = { aileron: Float, elevator: Float ->
                val is_stream = viewModel.isStream()
                //if is false
                if (!is_stream) {
                    val message = "Not connected yet!"
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                } else {
                    viewModel.aileronChanged(aileron)
                    viewModel.elevatorChanged(elevator)
                }
            }
        }
}

//between -1 to 1
fun getConvertedRudderValue(intVal: Int): Float {
    var floatVal = 0.0f
    floatVal = (intVal - 100.0f) / 100f

    return floatVal
}

//between -1 to 0
fun getConvertedThrottleValue(intVal: Int): Float {
    var floatVal = 0.0f
    floatVal = intVal / 100f

    return floatVal
}