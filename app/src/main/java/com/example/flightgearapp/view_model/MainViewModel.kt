package com.example.flightgearapp.view_model
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.flightgearapp.model.Model

class MainViewModel: ViewModel() {
    private val model = Model()
    val ip = MutableLiveData<String>()
    val port = MutableLiveData<String>()

    fun onChangeConnect() = model.connect(ip, port)

    fun throttleChanged(throttle: Float) = model.sendThrottleNotification(throttle)

    fun rudderChanged(rudder: Float) = model.sendRudderNotification(rudder)

    fun aileronChanged(aileron: Float) = model.sendAileronNotification(aileron)

    fun elevatorChanged(elevator: Float) = model.sendElevatorNotification(elevator)

    fun isStream() = model.checkOutStream()
}