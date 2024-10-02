package com.example.ussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "dealer")
data class Dealer (
    @PrimaryKey(autoGenerate = true)
    val _id: Int = -1,
    val itemId: Int = -1,
    val company_name: String? = null,
    val user_name: String? = null,
    val user_phone: String? = null,
    val user_telegram: String? = null,
    val user_instagram: String? = null,
    val version: Int = -1,
    val created_at: String? = null,
    val updated_at: String? = null
)