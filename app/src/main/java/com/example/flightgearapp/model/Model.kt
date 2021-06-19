package com.example.flightgearapp.model

import androidx.lifecycle.MutableLiveData
import java.io.IOException
import java.io.OutputStream
import java.net.InetAddress
import java.net.InetSocketAddress
import java.net.Socket

class Model {
    private lateinit var socket: Socket
    private lateinit var outStream: OutputStream
    private var is_stream = false

    fun connect(ip: MutableLiveData<String>, port: MutableLiveData<String>) {
        val t = Thread(Runnable {
            try {
                println(port.value.toString().toInt())
                println(ip.value.toString())
                val serverAddr: InetAddress = InetAddress.getByName(ip.value.toString())
                println("Connecting...")
                this.socket = Socket()
                this.socket.connect(InetSocketAddress(serverAddr, port.value.toString().toInt()))
                println("Connected!")
                this.outStream = this.socket.getOutputStream()
                this.is_stream = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        t.start()
        t.join()
    }

    fun sendRudderNotification(progress: Float) {
        //println("model value $progress")
            val command = "set /controls/flight/rudder $progress\r\n"
            val t = Thread(Runnable {
                try {
                    outStream.write(command.toByteArray(Charsets.UTF_8))
                    outStream.flush()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            })
            t.start()
            t.join()
    }

    fun sendThrottleNotification(progress: Float) {
        val command =
            "set /controls/engines/current-engine/throttle $progress\r\n"
        val t = Thread(Runnable {
            try {
                outStream.write(command.toByteArray(Charsets.UTF_8))
                outStream.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        t.start()
        t.join()
    }

    fun sendAileronNotification(progress: Float) {
        val command =
            "set /controls/flight/aileron $progress\r\n"
        val t = Thread(Runnable {
            try {
                outStream.write(command.toByteArray(Charsets.UTF_8))
                outStream.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        t.start()
        t.join()
    }

    fun sendElevatorNotification(progress: Float) {
        val command =
            "set /controls/flight/elevator $progress\r\n"
        val t = Thread(Runnable {
            try {
                outStream.write(command.toByteArray(Charsets.UTF_8))
                outStream.flush()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        })
        t.start()
        t.join()
    }

    fun checkOutStream(): Boolean {
        return this.is_stream
    }
}
