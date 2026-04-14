package com.timothy.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams


class signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        http://timothy.alwaysdata.net/api/signup
//        http://timothy.alwaysdata.net/api/signin
//        http://timothy.alwaysdata.net/api/getproducts
//        http://timothy.alwaysdata.net/api/mpesa
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signin = findViewById<TextView>(R.id.signin_link)
        signin.setOnClickListener {
            val signInlink = Intent(applicationContext, Signup::class.java)
            startActivity(signInlink)
        }

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signinbutton = findViewById<Button>(R.id.signinbutton)

        signinbutton.setOnClickListener {
            val api = "http://bridgit.alwaysdata.net/api/signin"
            val data = RequestParams()
            data.put("email", email.text.toString())
            data.put("password", password.text.toString().trim())
//            api helper -it delivers our data to the api
            val helper= ApiHelper(applicationContext)
            helper.post_login(api,data)
        }
    }
}