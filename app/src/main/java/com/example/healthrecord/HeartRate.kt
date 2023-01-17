package com.example.healthrecord

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthrecord.model.UserHr
import com.example.healthrecord.view.UserHrAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HeartRate : AppCompatActivity() {
    private lateinit var addingBtn2: FloatingActionButton
    private lateinit var recV2: RecyclerView
    private lateinit var userList2: ArrayList<UserHr>
    private lateinit var userHrAdapter: UserHrAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heart_rate)

        userList2 = ArrayList()
        addingBtn2 = findViewById(R.id.addButton2)
        recV2 = findViewById(R.id.hrRecycler)
        userHrAdapter = UserHrAdapter(this, userList2)

        /**setting Recycler view Adapter*/
        recV2.layoutManager = LinearLayoutManager(this)
        recV2.adapter = userHrAdapter

        /**setting Dialog*/
        addingBtn2.setOnClickListener{
            addInfo2()
        }
    }

    @SuppressLint("MissingInflatedId", "NotifyDataSetChanged")
    private fun addInfo2() {
        val inflater2 = LayoutInflater.from(this)
        val v2 = inflater2.inflate(R.layout.add_item_hr, null)
        /**setting view*/
        val userHr = v2.findViewById<EditText>(R.id.heartRate)
        val userDate = v2.findViewById<EditText>(R.id.hrDate)

        val addDialog2 = AlertDialog.Builder(this)

        addDialog2.setView(v2)
        addDialog2.setPositiveButton("Add") {
                dialog2,_->
                val hrs = userHr.text.toString()
                val dateshr = userDate.text.toString()
                userList2.add(UserHr("Heart Rate: $hrs", "Date: $dateshr"))
                userHrAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Added a new item", Toast.LENGTH_SHORT).show()
                dialog2.dismiss()
        }
        addDialog2.setNegativeButton("Cancel") {
                dialog2,_->
                dialog2.dismiss()
                Toast.makeText(this, "Cancelled", Toast.LENGTH_SHORT).show()
        }
        addDialog2.create()
        addDialog2.show()
    }
}