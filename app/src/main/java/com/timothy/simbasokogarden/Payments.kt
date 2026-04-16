package com.timothy.simbasokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.loopj.android.http.RequestParams

class Payments : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payments)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //retrieve entire product from the main activity
        //This data is passed via Intent

        val productname=intent.getStringExtra("product_name")
        val productcost=intent.getIntExtra("product_cost",0)
        val productphoto=intent.getStringExtra("product_photo")
        val productdescription=intent.getStringExtra("product_description")



        val photo=findViewById<ImageView>(R.id.product_photo)
        val name=findViewById<TextView>(R.id.product_name)
        val cost=findViewById<TextView>(R.id.product_cost)
        val phone=findViewById<EditText>(R.id.phone)
        val button=findViewById<Button>(R.id.pay)
        val description=findViewById<TextView>(R.id.product_description)

        //update textviews with values passed via intent

        name.text=productname
        description.text=productdescription
        cost.text="Ksh $productcost"

        Glide.with(this )
            .load(productphoto)
            .circleCrop()  //makes photo to be circle
            .into(photo)



        button.setOnClickListener {
            //set APi endpoint
            val api="http://timothy.alwaysdata.net/api/mpesa_payment"


            // create data  using requestParams,put phone and cost as keyvalue pairs
            val data= RequestParams()
            data.put("amount",productcost) //passed via intent
            data.put("phone",phone.text.toString()) //entered by user in phone edit

            //access api helper
            val helper= ApiHelper(applicationContext)

            //post data to api endpoint
            helper.post(api,data)



        }

    }
}