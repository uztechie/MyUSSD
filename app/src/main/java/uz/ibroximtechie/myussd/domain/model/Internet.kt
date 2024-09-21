package uz.ibroximtechie.myussd.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("internet")
data class Internet(
    @PrimaryKey(autoGenerate = true)
    val id:Int
)
