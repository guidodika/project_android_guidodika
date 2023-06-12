package com.example.myappguidodika

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.myappguidodika.data.AppDatabase

class LoginMemberActivity : AppCompatActivity() {
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_member)

        usernameEditText = findViewById(R.id.editTextUsername)
        passwordEditText = findViewById(R.id.editTextPassword)
        loginButton = findViewById(R.id.buttonLogin)

        // Mendapatkan instance database SQLite
        database = AppDatabase.getInstance(applicationContext)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            val member = database.userDao().getMember(username, password)

            if (member != null) {
                // Login berhasil
                val intent = Intent(this, ViewDataMemberActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
                // Tindakan selanjutnya setelah login berhasil sebagai member
            } else {
                // Username atau password salah
                Toast.makeText(this, "Username atau password salah", Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Menutup koneksi database saat activity dihancurkan
        database.close()
    }
}
