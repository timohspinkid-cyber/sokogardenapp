package com.timothy.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

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


        val signup=findViewById<Button>(R.id.signup)
        signup.setOnClickListener {
            val signupIntent= Intent(applicationContext,Signup::class.java)
            startActivity(signupIntent)
        }

        val signinbutton =findViewById<Button>(R.id.login)
        signinbutton.setOnClickListener {
            val signinIntent=Intent(applicationContext, signin::class.java)
            startActivity(signinIntent)
        }
//        fetch progress bar and recycler view by thier ids
        val progress=findViewById<ProgressBar>(R.id.progressbar)
        val recyclerView=findViewById<RecyclerView>(R.id.recyclerview)
        val api="http://timothy.alwaysdata.net/api/getproductdetails"
        val helper= ApiHelper(applicationContext)
        helper.loadProducts(api,recyclerView,progress)


        val aboutus= findViewById<Button>(R.id.about)
        aboutus.setOnClickListener {
            val intent = Intent(applicationContext, About::class.java)
            startActivity(intent)
        }
    }

}