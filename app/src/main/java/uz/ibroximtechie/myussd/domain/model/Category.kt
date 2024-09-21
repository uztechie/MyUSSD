package com.example.ussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = -1,
    val itemId: Int = -1,
    val cat_uz: String? = null,
    val cat_ru: String? = null,
    val cat_kr: String? = null,
    val company_id: Int = -1,
    val type_id: Int = -1,
    val created_at: String? = null,
    val updated_at: String? = null
)
