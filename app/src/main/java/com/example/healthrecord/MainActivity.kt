package com.example.healthrecord

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating values for buttons
        val b1 = findViewById<Button>(R.id.user_info) //user info button
        val b2 = findViewById<Button>(R.id.bp) //blood pressure button
        val b3 = findViewById<Button>(R.id.hr) //heart rate button
        val b4 = findViewById<Button>(R.id.allergy) //allergy button
        val b5 = findViewById<Button>(R.id.medication) //medicine button
        val b6 = findViewById<Button>(R.id.fact) //instructions button

        b1.setOnClickListener {
            val intent = Intent(this, UserInfo::class.java)
            startActivity(intent)
        }

        b2.setOnClickListener {
            val intent2 = Intent(this, BloodPressure::class.java)
            startActivity(intent2)
        }

        b3.setOnClickListener {
            val intent3 = Intent(this, HeartRate::class.java)
            startActivity(intent3)
        }

        b4.setOnClickListener {
            val intent4 = Intent(this, Allergies::class.java)
            startActivity(intent4)
        }

        b5.setOnClickListener {
            val intent5 = Intent(this, Medications::class.java)
            startActivity(intent5)
        }

        b6.setOnClickListener {
            val dialogBinding = layoutInflater.inflate(R.layout.custom_dialogue, null)

            val myDialog = Dialog(this)
            myDialog.setContentView(dialogBinding)

            myDialog.setCancelable(true)
            myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            myDialog.show()

            //calling button "okay" and storing it in "yes" value
            val yes = dialogBinding.findViewById<Button>(R.id.okay)
            yes.setOnClickListener {
                myDialog.cancel()
            }
        }
    }
}