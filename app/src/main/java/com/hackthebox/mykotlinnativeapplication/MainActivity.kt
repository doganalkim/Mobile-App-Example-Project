package com.hackthebox.mykotlinnativeapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import com.hackthebox.mykotlinnativeapplication.databinding.ActivityMainBinding
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

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

    private val targetActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        // This callback replaces onActivityResult
        if (result.resultCode == RESULT_OK) {
            val returnedValue = result.data?.getStringExtra("key_result")
            val messageResult = findViewById<TextView>(R.id.messageResult)
            messageResult.text = getString(R.string.message_with_result, messageResult.text, returnedValue)
        }
    }

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
            val intent = Intent(this, TargetActivity::class.java)
            intent.putExtra("key_firstword","Offensive")
            intent.putExtra("key_secondword", "Security")
            targetActivityLauncher.launch(intent)
        }
    }

    /*
    // This is the old way. This does not work
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == RESULT_OK){
            val returnedValue = data?.getStringExtra("key_result")
            val messageResult = findViewById<TextView>(R.id.messageResult)
            messageResult.text = getString(R.string.message_with_result, messageResult.text, returnedValue)
        }
    }
    */

}