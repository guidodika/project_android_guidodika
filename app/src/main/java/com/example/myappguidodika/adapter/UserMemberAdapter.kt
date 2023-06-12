package com.example.myappguidodika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myappguidodika.R
import com.example.myappguidodika.data.entity.User

class UserMemberAdapter(private val user: User) : RecyclerView.Adapter<UserMemberAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var fullName: TextView
        var birthDate: TextView
        var address: TextView
        var gender: TextView
        var username: TextView
        var password: TextView


        init {
            fullName = view.findViewById(R.id.full_name)
            birthDate = view.findViewById(R.id.birth_date)
            address = view.findViewById(R.id.address)
            gender = view.findViewById(R.id.gender)
            username = view.findViewById(R.id.username)
            password = view.findViewById(R.id.password)


            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_user, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 1
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val userData = user
        holder.fullName.text = userData.fullName
        holder.birthDate.text = userData.birthDate
        holder.address.text = userData.address
        holder.gender.text = userData.gender
        holder.username.text = userData.username
        holder.password.text = userData.password

    }
}