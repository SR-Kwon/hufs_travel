package com.mobileProgramming.travelaccountbook.Member
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0, // 자동 증가 ID
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val age: Int
)
