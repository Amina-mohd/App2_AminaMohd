package com.example.healthrecord

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_allergies.*

class Allergies : AppCompatActivity() {

    private lateinit var allergyView: ListView
    private lateinit var allergyAdd: Button
    private lateinit var allergyClear: Button
    private lateinit var allergyEdit: EditText
    private lateinit var allergiesList: ArrayList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_allergies)

        allergyView = findViewById(R.id.allergyList)
        allergyAdd = findViewById(R.id.allergyBtn)
        allergyClear = findViewById(R.id.clearAllergyBtn)
        allergyEdit = findViewById(R.id.editItem)
        allergiesList = ArrayList()

        val adapter: ArrayAdapter<String?> = ArrayAdapter<String?>(this@Allergies,
            android.R.layout.simple_list_item_1,
            allergiesList as List<String>)

        allergyView.adapter = adapter

        /**adding item to the list*/
        allergyAdd.setOnClickListener {
            val item = allergyEdit.text.toString()

            editItem.setText("")

            if(item.isNotEmpty()) {
                allergiesList.add(item)
                adapter.notifyDataSetChanged()
            }
        }

        /**clear button*/
        allergyClear.setOnClickListener {
            editItem.setText("")
            adapter.clear()
            }
        }
    }