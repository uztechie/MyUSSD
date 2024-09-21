package com.example.ussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "tarif")
data class Tarif(
    @PrimaryKey(autoGenerate = true)
    val _id: Int = -1,
    val itemId: Int = -1,
    val title_uz: String? = null,
    val title_ru: String? = null,
    val title_kr: String? = null,
    val desc_uz: String? = null,
    val desc_ru: String? = null,
    val desc_kr: String? = null,
    val full_desc_uz: String? = null,
    val full_desc_ru: String? = null,
    val full_desc_kr: String? = null,
    val image: String? = null,
    val cimage: String? = null,
    val kod: String? = null,
    val summa: Double,
    val status: Int = -1,
    val order: Int = 0,
    val company_id: Int = -1,
    val created_at: String? = null,
    val updated_at: String? = null,
    val catid: Int = -1
)
