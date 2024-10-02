package uz.ibroximtechie.myussd.presentation.ussdservices

import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Internet
import com.example.ussd.domain.model.Minute

data class UssdServiceState(
    val categories:List<Category> = emptyList(),
    val internetList:List<Internet> = emptyList(),
    val minuteList:List<Minute> = emptyList()

)
