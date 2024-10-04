package uz.ibroximtechie.myussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "company")
data class Company(
    val u_minut:String? = null,
    val telegram:String? = null,
    val created_at:String? = null,
    val itemId:Int = 0,
    val callcenter:String?  = null,
    val updated_at:String? = null,
    val name:String? = null,
    val desc_uz:String? = null,
    val desc_ru:String? = null,
    @PrimaryKey(autoGenerate = true) val _id:Int,
    val cabinet:String? = null,
    val desc_kr:String? = null,
    val u_balans:String? = null,
    val u_sms:String? = null,
    val u_internet:String? = null,

)
