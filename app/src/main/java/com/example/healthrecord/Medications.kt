package com.example.healthrecord

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_allergies.*
import kotlinx.android.synthetic.main.activity_medications.*

class Medications : AppCompatActivity() {

    private lateinit var medicineView: ListView
    private lateinit var medicineAdd: Button
    private lateinit var medicineClear: Button
    private lateinit var medicineEdit: EditText
    private lateinit var medList: ArrayList<String>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medications)

        medicineView = findViewById(R.id.medicineList)
        medicineAdd = findViewById(R.id.medicineBtn)
        medicineClear = findViewById(R.id.clearMedBtn)
        medicineEdit = findViewById(R.id.editItem2)
        medList = ArrayList()

        val adapter2: ArrayAdapter<String?> = ArrayAdapter<String?>(this@Medications,
            android.R.layout.simple_list_item_1,
            medList as List<String>)

        medicineView.adapter = adapter2

        /**adding item to the list*/
        medicineAdd.setOnClickListener {
            val item2 = medicineEdit.text.toString()

            editItem2.setText("")

            if(item2.isNotEmpty()) {
                medList.add(item2)
                adapter2.notifyDataSetChanged()
            }
        }

        /**clear button*/
        medicineClear.setOnClickListener {
            editItem2.setText("")
            adapter2.clear()
        }
    }
}