package com.example.myappguidodika

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myappguidodika.adapter.UserAdapter
import com.example.myappguidodika.data.AppDatabase
import com.example.myappguidodika.data.entity.User

class ViewDataMemberActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var list = mutableListOf<User>()
    private lateinit var adapter: UserAdapter
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_data)
        val myView = findViewById<View>(R.id.fab)
        myView.visibility = View.GONE

        recyclerView = findViewById(R.id.recycler_view)


        database = AppDatabase.getInstance(applicationContext)
        adapter = UserAdapter(list)
        adapter.setDialog(object : UserAdapter.Dialog{
            override fun onClick(position: Int) {
                // membuat dialog view
                val dialog = AlertDialog.Builder(this@ViewDataMemberActivity)
                dialog.setTitle(list[position].fullName)
                dialog.setItems(R.array.items_option, DialogInterface.OnClickListener{dialog, which ->
                    if (which==0) {
                        //coding ubah
                        val intent = Intent(this@ViewDataMemberActivity, EditorActivity::class.java)
                        intent.putExtra("id", list[position].uid)
                        startActivity(intent)
                    } else if(which==1) {
                        // coding hapus
                        database.userDao().delete(list[position])
                        getData()
                    } else {
                        // coding batal
                        dialog.dismiss()
                    }
                })
                val dialogView = dialog.create()
                dialogView.show()
            }

        })

        recyclerView.adapter =adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext,
            RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(DividerItemDecoration(applicationContext,
            RecyclerView.VERTICAL
        ))

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun getData() {
        list.addAll(database.userDao().getAll())
        adapter.notifyDataSetChanged()
    }
}