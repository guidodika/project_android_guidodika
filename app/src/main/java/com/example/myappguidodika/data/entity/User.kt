package com.example.myappguidodika.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    @ColumnInfo(name = "full_name") var fullName: String?,
    @ColumnInfo(name = "birthDate") var birthDate: String?,
    @ColumnInfo(name = "address") var address: String?,
    @ColumnInfo(name = "gender") var gender: String?,
    @ColumnInfo(name = "username") var username: String?,
    @ColumnInfo(name = "password") var password: String?

)