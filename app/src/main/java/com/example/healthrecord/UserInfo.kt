package com.example.healthrecord

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.android.synthetic.main.activity_user_info.*

class UserInfo : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        //creating value for the clear button
        val clear = findViewById<Button>(R.id.clearBtn)

        clear.setOnClickListener {
            nameEntry.setText("")
            ageEntry.setText("")
            weightEntry.setText("")
            heightEntry.setText("")
            infoText.text = ""
        }

        val sharedPreferences = getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        save.setOnClickListener {
            val name = nameEntry.text.toString().trim()
            val age = Integer.parseInt(ageEntry.text.toString().trim())
            val weight = Integer.parseInt(weightEntry.text.toString().trim())
            val height = Integer.parseInt(heightEntry.text.toString().trim())

            val editor = sharedPreferences.edit()
            editor.putString("Name", name)
            editor.putInt("Age", age)
            editor.putInt("Weight", weight)
            editor.putInt("Height", height)
            editor.apply()
        }

        showInfo.setOnClickListener {
            //getting data from preferences
            val name = sharedPreferences.getString("Name", "")
            val age = sharedPreferences.getInt("Age", 0)
            val weight = sharedPreferences.getInt("Weight", 0)
            val height = sharedPreferences.getInt("Height", 0)

            //showing data in text view
            infoText.text = "Name: $name \nAge: $age \nWeight: $weight \nHeight: $height cm"
        }
    }
}