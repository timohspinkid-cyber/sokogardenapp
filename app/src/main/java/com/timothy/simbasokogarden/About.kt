package com.timothy.simbasokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {

    //DECLARE A TTS VARIABLE
    lateinit var tts: TextToSpeech
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //find text view button
        val about=findViewById<EditText>(R.id.inputText)
        val textView = findViewById<TextView>(R.id.textview)
        val speakButton = findViewById<Button>(R.id.speakbutton)

        //create a tts object check if tts is available and set language

        tts= TextToSpeech(this){
            //CHECK IF THE SPEECH IS SUCCESSFUL
            if (it == TextToSpeech.SUCCESS){
                tts.language= Locale.US
            }
        }
        //SET BUTTON LISTEN
        speakButton.setOnClickListener {
            val textt=about.text.toString()
            tts.speak(textt, TextToSpeech.QUEUE_FLUSH,null,null)
        }
    }
    //stop the tts from speaking when app is closed/destroyed/killed
    override fun onDestroy() {
        tts.stop()
        tts.shutdown()
        super.onDestroy()
    }
}