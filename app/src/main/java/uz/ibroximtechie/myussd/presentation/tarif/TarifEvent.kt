package uz.ibroximtechie.myussd.presentation.tarif

import com.example.ussd.domain.model.Tarif

sealed interface TarifEvent {
    data class OnCategorySelected(val categoryPosition:Int):TarifEvent
    object OnTypeSelected:TarifEvent

}