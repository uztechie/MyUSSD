package uz.ibroximtechie.myussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "code")
data class USSDCode(
    @PrimaryKey(autoGenerate = true)
    val _id:Int = -1,
    val itemId:Int = -1,
    val title_uz:String? = null,
    val title_ru:String? = null,
    val title_kr:String? = null,
    val code:String? = null,
    val order:Int = 0,
    val company_id:Int = 0,
    val created_at:String? = null,
    val updated_at:String? = null
)