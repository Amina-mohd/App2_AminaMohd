package com.example.healthrecord.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.healthrecord.R
import com.example.healthrecord.model.UserHr

class UserHrAdapter(val c: Context, private val userList:ArrayList<UserHr>) :RecyclerView.Adapter<UserHrAdapter.UserHrViewHolder>()
{
    inner class UserHrViewHolder(v:View):RecyclerView.ViewHolder(v){
        var measurehr:TextView = v.findViewById(R.id.hrTitle)
        var datehr:TextView = v.findViewById(R.id.hrSubTitle)
        private var mmenus:ImageView = v.findViewById(R.id.menuDot)

        init {
            mmenus.setOnClickListener{
                popupMenu2(it)
            }
        }

        @SuppressLint("DiscouragedPrivateApi", "NotifyDataSetChanged")
        private fun popupMenu2(v: View?) {
            val position = userList[adapterPosition]
            val popupMenu2 = PopupMenu(c, v)
            popupMenu2.inflate(R.menu.show_menu2)
            popupMenu2.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editInfo2->{
                        val v2 = LayoutInflater.from(c).inflate(R.layout.add_item_hr, null)
                        val hr = v2.findViewById<EditText>(R.id.heartRate)
                        val date = v2.findViewById<EditText>(R.id.hrDate)
                            AlertDialog.Builder(c)
                                .setView(v2)
                                .setPositiveButton("Ok"){
                                    dialog2,_->
                                    position.hrMeasure = hr.text.toString()
                                    position.hrDate = date.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(c, "Heart-Rate Info Edited",Toast.LENGTH_SHORT).show()
                                    dialog2.dismiss()
                                }
                                .setNegativeButton("Cancel"){
                                    dialog2,_->
                                    dialog2.dismiss()
                                }
                                .create()
                                .show()
                        true
                    }
                    R.id.deleteInfo2->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes") {
                                dialog2,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Entry deleted!",Toast.LENGTH_SHORT).show()
                                dialog2.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog2,_->
                                dialog2.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }

                    else-> true
                }
            }
            popupMenu2.show()
            val popup2 = PopupMenu::class.java.getDeclaredField("mPopup")
            popup2.isAccessible = true
            val menu2 = popup2.get(popupMenu2)
            menu2.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu2, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHrViewHolder {
        val inflater2 = LayoutInflater.from(parent.context)
        val v2 = inflater2.inflate(R.layout.hr_list_item, parent, false)
        return UserHrViewHolder(v2)
    }

    override fun onBindViewHolder(holder: UserHrViewHolder, position: Int) {
        val newList2 = userList[position]
        holder.measurehr.text = newList2.hrMeasure
        holder.datehr.text = newList2.hrDate
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}