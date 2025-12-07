package com.hackthebox.mykotlinnativeapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class TargetActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_target)

        val button = findViewById<Button>(R.id.button)
        val message = findViewById<TextView>(R.id.mainMessage)

        val firstString = intent.getStringExtra("key_firstword")
        val secondString = intent.getStringExtra("key_secondword")

        message.text = firstString + secondString

        button.setOnClickListener {
            // TO DO
            val resultIntent = Intent()
            resultIntent.putExtra("key_result", "Mission Completed!")
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }
}