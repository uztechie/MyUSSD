package com.example.ussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class News(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = -1,
    val itemId: Int = -1,
    val title_uz: String? = null,
    val title_ru: String? = null,
    val title_kr: String? = null,
    val desc_uz: String? = null,
    val desc_ru: String? = null,
    val desc_kr: String? = null,
    val photo_uz: String? = null,
    val photo_ru: String? = null,
    val photo_kr: String? = null,
    val company_id: Int = -1,
    val url_uz: String? = null,
    val url_ru: String? = null,
    val url_kr: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)
