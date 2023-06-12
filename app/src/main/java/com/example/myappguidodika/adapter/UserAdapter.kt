package com.example.myappguidodika.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myappguidodika.R
import com.example.myappguidodika.data.entity.User

class UserAdapter(var list: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog) {
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var codeMember: TextView
        var fullName: TextView
        var birthDate: TextView
        var address: TextView
        var gender: TextView
        var username: TextView
        var password: TextView


        init {
            codeMember = view.findViewById(R.id.code_member)
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
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.codeMember.text = "Kode Member : ${list[position].uid}"
        holder.fullName.text = "Nama Lengkap : ${list[position].fullName}"
        holder.birthDate.text = "Tanggal Lahir : ${list[position].birthDate}"
        holder.address.text = "Alamat : ${list[position].address}"
        holder.gender.text = "Jenis Kelamin : ${list[position].gender}"
        holder.username.text = "Username : ${list[position].username}"
        holder.password.text = "Password : ${list[position].password}"

    }
}