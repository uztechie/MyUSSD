package uz.ibroximtechie.myussd.presentation.ussdservices

import com.example.ussd.domain.model.Category
import com.example.ussd.domain.model.Internet
import com.example.ussd.domain.model.Minute
import com.example.ussd.domain.model.Service
import com.example.ussd.domain.model.Sms

data class UssdServiceState(
    val categories:List<Category> = emptyList(),
    val internetList:List<Internet> = emptyList(),
    val minuteList:List<Minute> = emptyList(),
    val smsList:List<Sms> = emptyList(),
    val serviceList:List<Service> = emptyList(),
    val internet:Internet = Internet(),
    val minute:Minute = Minute(),
    val sms:Sms = Sms(),
    val service:Service = Service(),
    val showDetailsDialog:Boolean = false

)
