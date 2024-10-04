package uz.ibroximtechie.myussd.presentation.ussdservices

import com.example.ussd.domain.model.Internet
import com.example.ussd.domain.model.Minute
import com.example.ussd.domain.model.Service
import com.example.ussd.domain.model.Sms
import uz.ibroximtechie.myussd.common.CategoryType

sealed interface UssdServiceEvent {
    data class OnCategorySelected(val typeId: Int, val categoryPosition:Int): UssdServiceEvent
    data class OnCategoryTypeSelected(val typeId: Int):UssdServiceEvent
    data class OnInternetSelected(val internet: Internet):UssdServiceEvent
    data class OnMinuteSelected(val minute: Minute):UssdServiceEvent
    data class OnSmsSelected(val sms: Sms):UssdServiceEvent
    data class OnServiceSelected(val service: Service):UssdServiceEvent
    object DismissDetailsDialog: UssdServiceEvent

}