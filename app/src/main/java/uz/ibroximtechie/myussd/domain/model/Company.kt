package uz.ibroximtechie.myussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "company")
data class Company(
    val u_minut:String?,
    val telegram:String?,
    val created_at:String?,
    val itemId:Int,
    val callcenter:String?,
    val updated_at:String?,
    val name:String?,
    val desc_uz:String?,
    val desc_ru:String?,
    @PrimaryKey(autoGenerate = true) val _id:Int,
    val cabinet:String?,
    val desc_kr:String?,
    val u_balans:String?,
    val u_sms:String?,
    val u_internet:String?,

)
