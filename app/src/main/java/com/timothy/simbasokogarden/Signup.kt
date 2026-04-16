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

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val sigup=findViewById<TextView>(R.id.signup_link)
        sigup.setOnClickListener {
            val signUplink = Intent(applicationContext, signin::class.java)
            startActivity(signUplink)
        }
        val email =findViewById<EditText>(R.id.address)
        val username=findViewById<EditText>(R.id.user)
        val password=findViewById<EditText>(R.id.pass)
        val phone=findViewById<EditText>(R.id.phone)
        val siginupbutton=findViewById<Button>(R.id.signupbutton)
        siginupbutton.setOnClickListener {
            val api = "http://bridgit.alwaysdata.net/api/signup"
            val data = RequestParams()
            data.put("username", username.text.toString())
            data.put("email", email.text.toString())
            data.put("phone", phone.text.toString())
            data.put("password", password.text.toString().trim())
//            api helper -it delivers our data to the api
            val helper= ApiHelper(applicationContext)
            helper.post(api,data)
        }

        val productname=intent.getStringExtra("product_name")
        val productcost=intent.getIntExtra("product_cost",0)
        val productphoto=intent.getStringExtra("product_photo")





    }
}