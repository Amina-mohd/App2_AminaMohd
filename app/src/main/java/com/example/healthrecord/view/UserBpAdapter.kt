package com.example.healthrecord.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthrecord.R
import com.example.healthrecord.model.UserBp

class UserBpAdapter(val c: Context, val userList: ArrayList<UserBp>):RecyclerView.Adapter<UserBpAdapter.UserBpViewHolder>()
{
    inner class UserBpViewHolder(v:View):RecyclerView.ViewHolder(v){
        var measurebp:TextView = v.findViewById(R.id.bpTitle)
        var dateBp:TextView = v.findViewById(R.id.bpSubTitle)
        private var menus:ImageView = v.findViewById(R.id.menu)

        init {
            menus.setOnClickListener{
                popupMenus(it)
            }
        }

        @SuppressLint("DiscouragedPrivateApi", "NotifyDataSetChanged")
        private fun popupMenus(v: View) {
            val position = userList[adapterPosition]
            val popupMenus = PopupMenu(c,v)
            popupMenus.inflate(R.menu.show_menu)
            popupMenus.setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.editInfo->{
                        val v = LayoutInflater.from(c).inflate(R.layout.add_item, null)
                        val bp = v.findViewById<EditText>(R.id.bloodPressure)
                        val date = v.findViewById<EditText>(R.id.bpDate)
                            AlertDialog.Builder(c)
                                .setView(v)
                                .setPositiveButton("Ok"){
                                    dialog,_->
                                    position.bpMeasure = bp.text.toString()
                                    position.bpDate = date.text.toString()
                                    notifyDataSetChanged()
                                    Toast.makeText(c, "Bp Information Edited",Toast.LENGTH_SHORT).show()
                                    dialog.dismiss()
                                }
                                .setNegativeButton("Cancel") {
                                    dialog,_->
                                    dialog.dismiss()
                                }
                                .create()
                                .show()
                        true
                    }
                    R.id.deleteInfo->{
                        AlertDialog.Builder(c)
                            .setTitle("Delete")
                            .setIcon(R.drawable.ic_baseline_warning_24)
                            .setMessage("Are you sure?")
                            .setPositiveButton("Yes") {
                                dialog,_->
                                userList.removeAt(adapterPosition)
                                notifyDataSetChanged()
                                Toast.makeText(c, "Entry deleted!",Toast.LENGTH_SHORT).show()
                                dialog.dismiss()
                            }
                            .setNegativeButton("No"){
                                dialog,_->
                                dialog.dismiss()
                            }
                            .create()
                            .show()
                        true
                    }
                    else-> true
                }
            }
            popupMenus.show()
            val popup = PopupMenu::class.java.getDeclaredField("mPopup")
            popup.isAccessible = true
            val menu = popup.get(popupMenus)
            menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(menu, true)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserBpViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.bp_list_item, parent, false)
        return UserBpViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserBpViewHolder, position: Int) {
        val newList = userList[position]
        holder.measurebp.text = newList.bpMeasure
        holder.dateBp.text = newList.bpDate
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}