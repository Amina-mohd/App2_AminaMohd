package com.example.healthrecord

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthrecord.model.UserBp
import com.example.healthrecord.view.UserBpAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class BloodPressure : AppCompatActivity() {
    private lateinit var addingBtn:FloatingActionButton
    private lateinit var recV:RecyclerView
    private lateinit var userList:ArrayList<UserBp>
    private lateinit var userBpAdapter:UserBpAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blood_pressure)

        userList = ArrayList()
        addingBtn = findViewById(R.id.addButton)
        recV = findViewById(R.id.bpRecycler)
        userBpAdapter = UserBpAdapter(this, userList)
        /**setting Recycler view Adapter*/
        recV.layoutManager = LinearLayoutManager(this)
        recV.adapter = userBpAdapter

        /**setting Dialog*/
        addingBtn.setOnClickListener{
            addInfo()
        }
    }

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    private fun addInfo() {
        val inflater = LayoutInflater.from(this)
        val v = inflater.inflate(R.layout.add_item, null)
        /**setting view*/
        val userBp = v.findViewById<EditText>(R.id.bloodPressure)
        val userDate = v.findViewById<EditText>(R.id.bpDate)

        val addDialog = AlertDialog.Builder(this)

        addDialog.setView(v)
        addDialog.setPositiveButton("Add") {
            dialog,_->
            val bps = userBp.text.toString()
            val dates = userDate.text.toString()
            userList.add(UserBp("Blood Pressure: $bps", "Date: $dates"))
            userBpAdapter.notifyDataSetChanged()
            Toast.makeText(this, "Added a new item", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }
        addDialog.setNegativeButton("Cancel") {
            dialog,_->
            dialog.dismiss()
            Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        addDialog.create()
        addDialog.show()
    }
}