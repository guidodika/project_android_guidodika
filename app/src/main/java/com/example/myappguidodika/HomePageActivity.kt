package com.example.myappguidodika

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    private lateinit var adminButton: Button
    private lateinit var memberButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        adminButton = findViewById(R.id.buttonAdmin)
        memberButton = findViewById(R.id.buttonMember)

        adminButton.setOnClickListener {
            // Aksi ketika tombol "Login Sebagai Admin" ditekan
            val intent = Intent(this, LoginAdminActivity::class.java)
            intent.putExtra("role", "admin")
            startActivity(intent)
        }

        memberButton.setOnClickListener {
            // Aksi ketika tombol "Login Sebagai Member" ditekan
            val intent = Intent(this, LoginMemberActivity::class.java)
            intent.putExtra("role", "member")
            startActivity(intent)
        }
    }
}
