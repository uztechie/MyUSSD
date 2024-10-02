package com.example.ussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "minute")
data class Minute(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = -1,
    val itemId: Int = -1,
    val title_uz: String? = null,
    val title_ru: String? = null,
    val title_kr: String? = null,
    val desc_uz: String? = null,
    val desc_ru: String? = null,
    val desc_kr: String? = null,
    val price: String? = null,
    val type: Int = -1,
    val catid: Int = -1,
    val kod: String? = null,
    val kod2: String? = null,
    val status: Int = -1,
    val cat_uz: String? = null,
    val cat_ru: String? = null,
    val cat_kr: String? = null,
    val amount_uz: String? = null,
    val amount_ru: String? = null,
    val amount_kr: String? = null,
    val company_id: Int = -1,
    val created_at: String? = null,
    val updated_at: String? = null
)
