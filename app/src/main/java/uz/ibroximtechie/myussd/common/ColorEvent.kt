package uz.ibroximtechie.myussd.common

sealed interface ColorEvent {
    data class OnCompanyButtonClick(val companyId:Int): ColorEvent
}