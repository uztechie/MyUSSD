package uz.ibroximtechie.myussd.presentation.ussdservices

import uz.ibroximtechie.myussd.common.CategoryType

sealed interface UssdServiceEvent {
    data class OnCategorySelected(val typeId: Int, val categoryPosition:Int): UssdServiceEvent
    data class OnCategoryTypeSelected(val typeId: Int):UssdServiceEvent

}