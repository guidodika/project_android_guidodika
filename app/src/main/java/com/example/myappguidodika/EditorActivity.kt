package com.example.myappguidodika

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myappguidodika.data.AppDatabase
import com.example.myappguidodika.data.entity.User

class EditorActivity : AppCompatActivity() {
    private lateinit var fullName: EditText
    private lateinit var birthDate: EditText
    private lateinit var address: EditText
    private lateinit var gender: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var btnSave: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        fullName = findViewById(R.id.full_name)
        birthDate = findViewById(R.id.birth_date)
        address = findViewById(R.id.address)
        gender = findViewById(R.id.gender)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        btnSave = findViewById(R.id.btn_save)

        database = AppDatabase.getInstance(applicationContext)

        val intent = intent.extras

        if( intent!=null) {
            val id = intent.getInt("id", 0)
            var user = database.userDao().get(id)

            fullName.setText(user.fullName)
            birthDate.setText(user.birthDate)
            address.setText(user.address)
            gender.setText(user.gender)
            username.setText(user.username)
            password.setText(user.password)

        }

        btnSave.setOnClickListener {
            if (fullName.text.isNotEmpty() && birthDate.text.isNotEmpty() && address.text.isNotEmpty()
                && gender.text.isNotEmpty() && username.text.isNotEmpty() && password.text.isNotEmpty()) {
                if( intent != null) {
                    //coding edit data
                    database.userDao().update(
                        User(
                            intent.getInt("id", 0),
                            fullName.text.toString(),
                            birthDate.text.toString(),
                            address.text.toString(),
                            gender.text.toString(),
                            username.text.toString(),
                            password.text.toString(),
                        )
                    )
                } else {
                    // coding tambah data
                    database.userDao().insertAll(
                        User(
                            null,
                            fullName.text.toString(),
                            birthDate.text.toString(),
                            address.text.toString(),
                            gender.text.toString(),
                            username.text.toString(),
                            password.text.toString(),
                        )
                    )
                }

                finish()
            } else {
                Toast.makeText(
                    applicationContext,
                    "Silahkan isi semua data dengan valid",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}