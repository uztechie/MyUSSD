package uz.ibroximtechie.myussd.common

import com.example.ussd.domain.model.Tarif

sealed interface CategoryEvent {
    data class OnCategorySelected(val catId:Int):CategoryEvent
}