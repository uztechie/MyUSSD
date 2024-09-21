package uz.ibroximtechie.myussd.presentation.home

sealed interface HomeEvent {
    data class OnCompanyButtonClick(val companyId:Int): HomeEvent
}