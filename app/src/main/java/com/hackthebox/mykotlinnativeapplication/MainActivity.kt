package com.hackthebox.mykotlinnativeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import com.hackthebox.mykotlinnativeapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val message = findViewById<TextView>(R.id.message)
        val button = findViewById<Button>(R.id.button)
        val switchButton = findViewById<Button>(R.id.switchButton)

        button.setOnClickListener {
            if(message.text.toString() == stringFromJNI()){
                message.text =  getString(R.string.message)
            }
            else{
                message.text = stringFromJNI()
            }
        }

        switchButton.setOnClickListener {
            // TO DO
        }
    }

    /**
     * A native method that is implemented by the 'mykotlinnativeapplication' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'mykotlinnativeapplication' library on application startup.
        init {
            System.loadLibrary("mykotlinnativeapplication")
        }
    }
}